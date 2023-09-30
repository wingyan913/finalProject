package com.hkjava.demo.demofinnhub.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SymbolValidator.class)
public @interface SymbolCheck {
  // error msg
  public String message()

  default "Invalid symbol. Please refer to ...";

  public Class<?>[] groups() default {};

  public Class<? extends Payload>[] payload() default {};

}
