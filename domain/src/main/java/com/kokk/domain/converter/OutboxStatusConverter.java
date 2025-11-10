package com.kokk.domain.converter;

import com.kokk.domain.model.enums.OutboxStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OutboxStatusConverter implements AttributeConverter<OutboxStatus, Integer> {


  @Override
  public Integer convertToDatabaseColumn(OutboxStatus attribute) {
    assert attribute != null : "[ERROR] OutboxStatus is null.";
    return attribute.getStatusCode();
  }

  @Override
  public OutboxStatus convertToEntityAttribute(Integer dbData) {
    assert dbData != null : "[ERROR] OutboxStatus from DB value is null.";
    return OutboxStatus.fromDbValue(dbData);
  }
}
