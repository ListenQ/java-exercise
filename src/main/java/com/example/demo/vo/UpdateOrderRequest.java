package com.example.demo.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 改订单
 * UpdateOrderRequest
 * @author zhangqi 
 * @date 2020年4月7日-下午4:06:52
 * 
 */
@Data
@EqualsAndHashCode(callSuper=false)
@XmlRootElement(name="message")
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdateOrderRequest extends AbstractGTSRequest{
	
	private String password;
	
	private String client_acc_code;
	
	private String user_code;
	
	private String price;
	
	private String qty;
	
	private String reference;
	
	private String order_no;
	
	
	

}
