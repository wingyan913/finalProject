package com.hkjava.demo.demofinnhub.infra;

import java.io.IOException;
import java.time.LocalDate;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

  public LocalDateDeserializer() {
    this(null);
  }

  protected LocalDateDeserializer(Class<LocalDate> t) {
    super(t);
  }
  
  @Override
  public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    return LocalDate.parse(p.getValueAsString()); // Customize the parsing as needed
  }
}
