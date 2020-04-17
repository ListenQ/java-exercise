package com.example.demo.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name="message")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserAuthRequest extends AbstractGTSRequest{
	
	private String site;
	private String station;
	private String type;
	private String user;
	private String password;
	
	@XmlElement(name="order_recovery")
	private String orderRecovery;
	
	@XmlElement(name="disable_notification")
	private String disableNotification;

}
