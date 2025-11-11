package com.kokk.infrastructure.config;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;


@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {

  final static int POOL_SIZE = 5;

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(POOL_SIZE);
    taskScheduler.initialize();
    taskRegistrar.setTaskScheduler(taskScheduler);
  }
}
