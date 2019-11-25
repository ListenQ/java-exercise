package com.example.demo.config;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DataDeserializerBigDecimal extends JsonDeserializer<BigDecimal>{

	@Override
	public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		if (Objects.isNull(jsonParser.getDecimalValue())) {
            return null;
        } else {
            // 这里取floor
            return jsonParser.getDecimalValue().setScale(2, RoundingMode.FLOOR);
        }
	}

}
