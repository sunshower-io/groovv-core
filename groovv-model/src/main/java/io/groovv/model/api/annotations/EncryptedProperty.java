package io.groovv.model.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
public @interface EncryptedProperty {
  String value() default "..<default>..";
}
