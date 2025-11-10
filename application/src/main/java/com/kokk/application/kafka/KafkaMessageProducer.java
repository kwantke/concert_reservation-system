package com.kokk.application.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kokk.domain.event.ConcertReservedEvent;
import com.kokk.domain.model.entity.Reservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageProducer{
  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final ObjectMapper objectMapper;

  @Value("${spring.kafka.topic.reserve}")
  private String TOPIC;

  public void publish(ConcertReservedEvent event) {
    send(TOPIC, event.getReservationId(), event);
  }



  public <T> void send(String topic, String key, T message) {
    try {
      String json = objectMapper.writeValueAsString(message);
      kafkaTemplate.send(topic, String.valueOf(key), message);
      log.info("Message sent to topic: {}, key: {}, message: {}", topic, key, json);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to serialize message", e);
    }
  }

}
