package com.example.demo.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 单一订单差查询请求
 * OrderEnqRequest
 * @author zhangqi 
 * @date 2020年4月7日-下午4:06:52
 * 
 */
@Data
@EqualsAndHashCode(callSuper=false)
@XmlRootElement(name="message")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderEnqRequest extends AbstractGTSRequest{
	
	private String password;
	
	private String user_code;
	
	private String order_no;
	
	private String order_info_only;
	
	private String reference;
	
	
	

}
