package com.hoaxify.backend.common.web.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimestampSerializer extends JsonSerializer<LocalDateTime> {
  @Override
  public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    long epochMilli = localDateTime
      .atZone(ZoneId.of("Europe/Istanbul"))
      .toInstant()
      .toEpochMilli();
    jsonGenerator.writeNumber(epochMilli);
  }
}
