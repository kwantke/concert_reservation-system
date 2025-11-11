package com.kokk.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfig {
  @Bean
  public NewTopic reserveTopic(
          @Value("${spring.kafka.topic.reserve:concert-reserve}") String topic) {
    return TopicBuilder.name(topic).partitions(3).replicas(1).build();
    //partitions(3) → 병렬 처리 성능에 직접 영향
    //파티션 수 = 그룹 내 Consumer 인스턴스가 동시에 처리할 수 있는 최대 개수
  }
  // @RetryableTopic 사용 시 retry/DLT 토픽은 Spring이 자동 생성(카프카 AdminClient 활성 시)
}