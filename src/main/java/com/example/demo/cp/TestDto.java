package com.example.demo.cp;

import java.math.BigDecimal;
import java.util.Date;

import com.example.demo.config.DataDeserializerBigDecimal;
import com.example.demo.config.SerializerBigDecimal;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class TestDto {
	
	private String name;
	
	private Date date;
	
	private BigDecimal number;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonDeserialize(using = DataDeserializerBigDecimal.class)
	public BigDecimal getNumber() {
		return number;
	}

	
    @JsonSerialize(using = SerializerBigDecimal.class)
	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
