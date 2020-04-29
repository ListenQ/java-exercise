package com.example.demo.vo;


import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.example.demo.util.JaxbDateTimeSerializer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@XmlRootElement(name="message")
@XmlAccessorType(XmlAccessType.FIELD)
public class TranHistoryResponseVo extends AbstractGTSRequest{
	
	private String client_acc_code;
	
	@XmlJavaTypeAdapter(JaxbDateTimeSerializer.class)
	private Date server_time; 
	
	private TransResponseVo trans;
	
	

}
