package com.example.demo.vo;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class TranResponseVo {
	
	private String tran_type;
	
	private String date;
	
	private String sett_date;
	
	private String ccy;
	
	private String exchange_code;
	
	private String product_code;
	
	private BigDecimal price;
	
	private Integer qty;
	
	private Integer amt;
	
	private String tran_detail;
	
	

}
