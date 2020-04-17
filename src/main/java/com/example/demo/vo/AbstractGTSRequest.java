package com.example.demo.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractGTSRequest {
	
	@XmlAttribute(name="type")
	protected String rootType;
	
	@XmlAttribute(name="msgnum")
	protected String rootMsgnum;

}
