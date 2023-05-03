package com.abm.persona.api.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateFormatSerializer extends JsonSerializer<Instant> {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC);

    @Override
    public void serialize(Instant value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        String formatted = dateTimeFormatter.format(value);
        jsonGenerator.writeString(formatted);
    }
}
