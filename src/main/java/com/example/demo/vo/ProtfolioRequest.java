package com.example.demo.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 持仓查询请求
 * ProtfolioRequest
 * @author zhangqi 
 * @date 2020年4月7日-下午7:18:59
 * 
 */
@Data
@EqualsAndHashCode(callSuper=false)
@XmlRootElement(name="message")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProtfolioRequest extends AbstractGTSRequest{
	
	private String password;
	
	@XmlElement(name="client_acc_code")
	private String clientAccCode;
	
	@XmlElement(name="user_code")
	private String userCode;
	
	private String show_zero_qty;
	
	private String show_margin_percent;
	
	private String hide_limit;
	
	private String mv_by_avail_sell;
	

}
