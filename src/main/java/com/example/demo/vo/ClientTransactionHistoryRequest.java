package com.example.demo.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 客户交易历史查询请求
 * ClientTransactionHistoryRequest
 * @author zhangqi 
 * @date 2020年4月7日-下午7:18:59
 * 
 */
@Data
@EqualsAndHashCode(callSuper=false)
@XmlRootElement(name="message")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientTransactionHistoryRequest extends AbstractGTSRequest{
	
	private String password;
	
	private String client_acc_code;
	
	private String user_code;
	
	private String from_trade_date;
	
	private String to_trade_date;
	
	private String jp_client;
	

}
