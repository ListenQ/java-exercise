package com.example.demo.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import lombok.Data;

@Data
@XmlRootElement(name="message")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(value= {ClientBalanceRequest.class,CancelOrderRequest.class})
public class GTSRequestVo<T> {
	
	@XmlAttribute(name="type",required = true)
	protected String rootType;
	
	
	@XmlAttribute(name="msgnum",required = true)
	protected String rootMsgnum; 
	
	
	@XmlAnyElement(lax=true)
	private T body;

	
	
	
	
	
	
	
	

}
