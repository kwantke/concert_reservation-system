package com.kokk.infrastructure.support.interceptor;

import com.kokk.application.queue.port.in.WaitingQueueServicePort;
import com.kokk.domain.exception.CoreException;
import com.kokk.domain.exception.queue.WaitingQueueErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class QueueTokenInterceptor implements HandlerInterceptor {
  private final WaitingQueueServicePort waitingQueueServicePort;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
          throws Exception {
    final String token = request.getHeader("QUEUE-TOKEN");
    if (Objects.isNull(token) || token.isEmpty()) {
      throw new CoreException(WaitingQueueErrorCode.INVALID_WAITING_QUEUE);
    }

    waitingQueueServicePort.checkActivatedQueue(token);
    return true;
  }

}
