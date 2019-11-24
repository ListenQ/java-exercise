package com.example.demo.config;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class SerializerBigDecimal extends JsonSerializer<BigDecimal>{

	@Override
	public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		if(Objects.isNull(value)) {
            gen.writeNull();
        } else {
            // 这里取floor
            gen.writeNumber(value.setScale(2, RoundingMode.FLOOR));
        }
	}

}
