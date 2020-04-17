package com.example.demo.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 客户现金存/取款查询请求
 * ClientCashDWRequest
 * @author zhangqi 
 * @date 2020年3月31日-下午3:14:58
 * 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="message")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder= {"clientAccCode","userCode","password","fromTradeDate","toTradeDate","newTimeFormat"})
public class ClientCashDWRequest{
	
	@XmlAttribute(name="type")
	private String rootType;
	@XmlAttribute(name="msgnum")
	private String rootMsgnum;
	
	
	private String password;
	
	@XmlElement(name="client_acc_code")
	private String clientAccCode;
	
	@XmlElement(name="user_code")
	private String userCode;
	
	@XmlElement(name="from_trade_date")
	private String fromTradeDate;
	
	@XmlElement(name="to_trade_date")
	private String toTradeDate;
	
	@XmlElement(name="new_time_format")
	private String newTimeFormat;

}
