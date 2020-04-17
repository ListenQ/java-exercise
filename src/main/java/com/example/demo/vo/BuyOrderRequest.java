package com.example.demo.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 下订单
 * BuyOrderRequest
 * @author zhangqi 
 * @date 2020年4月7日-下午4:06:52
 * 
 */
@Data
@EqualsAndHashCode(callSuper=false)
@XmlRootElement(name="message")
@XmlAccessorType(XmlAccessType.FIELD)
public class BuyOrderRequest extends AbstractGTSRequest{
	
	private String bs_flag;
	private String password;
	
	@XmlElement(name="client_acc_code")
	private String clientAccCode;
	
	@XmlElement(name="user_code")
	private String userCode;
	
	private String exchange_code;
	
	private String product_code;
	
	private String order_type;
	
	private String price;
	
	private String qty;
	
	private String reference;
	
	private String ip_address;
	

}
