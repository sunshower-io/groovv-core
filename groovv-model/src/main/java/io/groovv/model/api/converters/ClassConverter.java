package io.groovv.model.api.converters;

import javax.persistence.AttributeConverter;

public class ClassConverter implements AttributeConverter<Class<?>, String> {

  @Override
  public String convertToDatabaseColumn(Class<?> attribute) {
    if (attribute != null) {
      return attribute.getName();
    }
    return null;
  }

  @Override
  public Class<?> convertToEntityAttribute(String dbData) {
    if (dbData == null) {
      return null;
    }
    try {
      return Class.forName(dbData, false, Thread.currentThread().getContextClassLoader());
    } catch (Exception ex) {
      return null;
    }
  }
}
