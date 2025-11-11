package com.kokk.domain.converter;

import com.kokk.domain.enums.OutboxStatus;
import com.kokk.domain.enums.ReservationStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ReservationStatusConverter implements AttributeConverter<ReservationStatus, Integer> {


  @Override
  public Integer convertToDatabaseColumn(ReservationStatus attribute) {
    assert attribute != null : "[ERROR] OutboxStatus is null.";
    return attribute.getStatusCode();
  }

  @Override
  public ReservationStatus convertToEntityAttribute(Integer dbData) {
    assert dbData != null : "[ERROR] OutboxStatus from DB value is null.";
    return ReservationStatus.fromDbValue(dbData);
  }
}
