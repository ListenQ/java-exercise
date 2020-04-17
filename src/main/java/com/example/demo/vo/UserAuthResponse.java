package com.example.demo.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name="message")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserAuthResponse{
	
	@XmlAttribute(name="type")
	private String rootType;
	@XmlAttribute(name="msgnum")
	private String rootMsgnum;
	
	private Integer status;
	private String alert_change_pwd;
	private String force_change_pwd;
	private String pwd_expiry_date;
	private String last_login_time;
	private String require_activation;
	
	

}
