package com.kokk.infrastructure.queue.db.redis;

import com.kokk.domain.model.exception.CoreException;
import com.kokk.domain.model.exception.queue.WaitingQueueErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static com.kokk.infrastructure.queue.db.redis.WaitingQueueRepositoryImpl.*;

@Slf4j
@RequiredArgsConstructor
@Repository
public class WaitingQueueSchedulerRepositoryImpl implements WaitingQueueSchedulerRepository {
  private final RedisTemplate<String, Object> redisTemplate;

  // LUA 스크립트를 활용하여 일괄적으로 대기열 토큰을 제거하고 활성화 토큰으로 변경한다.
  private static final String SCRIPT_BATCH_ACTIVATE = """
              local waiting_key = KEYS[1]
              local active_key = KEYS[2]
              local meta_prefix = ARGV[1]
              local ttl = tonumber(ARGV[2])
              local limit = tonumber(ARGV[3])
              
              local tokens = redis.call("ZRANGE", waiting_key, 0, limit - 1)
              if #tokens == 0 then
                  return 0
              end
              
              for _, token in ipairs(tokens) do
                redis.call("ZREM", waiting_key, token)
                redis.call("SADD", active_key, token)
                redis.call("EXPIRE", meta_prefix .. token, ttl)
              end
              
              return #tokens
          """;

  public int batchActivate(int limit) {
    try {
      RedisScript<Long> script = RedisScript.of(SCRIPT_BATCH_ACTIVATE, Long.class);
      Long result = redisTemplate.execute(
              script,
              List.of(WAITING_QUEUE_KEY, ACTIVE_QUEUE_KEY),
              TOKEN_META_KEY_PREFIX,
              String.valueOf(ACTIVE_QUEUE_TTL_SECONDS), // Lua 스크립트에서 ARGV 인자 타입이 String 이기 때문에 long 타입을 String으로 변환
              String.valueOf(limit)
      );
      return result != null ? result.intValue() : 0;
    } catch (Exception e) {
      log.error("대기열 활성화 Lua 스크립트 실행 실패: {}", e.getMessage(), e);

      return 0;
    }

  }

/*
  // Lua 스크립트 대신에 Java를 사용할 경우
  @Override
  public int batchActivate(int limit) {
    Set<Object> tokenSet = redisTemplate.opsForZSet().range(WAITING_QUEUE_KEY, 0, limit - 1);
    if (tokenSet == null || tokenSet.isEmpty()) return 0;

    redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
      for (Object tokenObj : tokenSet) {
        String token = tokenObj.toString();
        connection.zRem(WAITING_QUEUE_KEY.getBytes(), token.getBytes());
        connection.sAdd(ACTIVE_QUEUE_KEY.getBytes(), token.getBytes());
        connection.expire((TOKEN_META_KEY_PREFIX + token).getBytes(), ACTIVE_QUEUE_TTL_SECONDS);
      }
      return null;
    });
    return tokenSet.size();
  }*/
}
