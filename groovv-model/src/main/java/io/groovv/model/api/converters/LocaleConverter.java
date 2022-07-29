package io.groovv.model.api.converters;

import java.util.Locale;
import javax.annotation.Nullable;
import javax.persistence.AttributeConverter;

public class LocaleConverter implements AttributeConverter<Locale, String> {

  @Override
  @Nullable
  public String convertToDatabaseColumn(@Nullable Locale attribute) {
    if(attribute != null) {
      return attribute.toLanguageTag();
    }
    return null;
  }

  @Override
  @Nullable
  public Locale convertToEntityAttribute(@Nullable String dbData) {
    if(!(dbData == null || dbData.isBlank())) {
      return Locale.forLanguageTag(dbData);
    }
    return null;
  }
}
