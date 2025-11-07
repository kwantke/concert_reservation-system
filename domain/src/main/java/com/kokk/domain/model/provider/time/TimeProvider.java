package com.kokk.domain.model.provider.time;

import java.time.LocalDateTime;

public interface TimeProvider {
  LocalDateTime now();
}
