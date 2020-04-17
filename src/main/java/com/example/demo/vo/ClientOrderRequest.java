package com.example.demo.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 客户订单查询
 * ClientOrderRequest
 * @author zhangqi 
 * @date 2020年4月7日-下午4:06:52
 * 
 */
@Data
@EqualsAndHashCode(callSuper=false)
@XmlRootElement(name="message")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientOrderRequest extends AbstractGTSRequest{
	
	private String password;
	
	private String client_acc_code;
	
	private String user_code;
	
	private String order_status;
	
	private String from_trade_date;
	
	private String to_trade_date;
	
	
	

}
