package com.example.demo.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * 客户余额查询请求
 * ClientCashDWRequest
 * @author zhangqi 
 * @date 2020年3月31日-下午3:14:58
 * 
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="")
public class ClientBalanceRequest{
	
	private String password;
	
	@XmlElement(name="client_acc_code")
	private String clientAccCode;
	
	@XmlElement(name="user_code")
	private String userCode;
	
	@XmlElement(name="mv_by_avail_sell")
	private String mvByAvailSell;

}
