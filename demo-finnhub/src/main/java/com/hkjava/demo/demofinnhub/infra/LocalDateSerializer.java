package com.hkjava.demo.demofinnhub.infra;

import java.io.IOException;
import java.time.LocalDate;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateSerializer extends StdSerializer<LocalDate> {

  public LocalDateSerializer() {
    this(null);
  }

  protected LocalDateSerializer(Class<LocalDate> t) {
    super(t);
  }

  @Override
  public void serialize(LocalDate value, JsonGenerator gen,
      SerializerProvider provider) throws IOException {
    gen.writeString(value.toString()); // Customize the serialization format if needed
  }
}
