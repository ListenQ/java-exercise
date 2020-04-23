package com.example.demo.vo;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@XmlRootElement(name="message")
@XmlAccessorType(XmlAccessType.FIELD)
public class TranHistoryResponseVo extends AbstractGTSRequest{
	
	private String client_acc_code;
	
	private String server_time; 
	
	private TransResponseVo trans;
	
	

}
