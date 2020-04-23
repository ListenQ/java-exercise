package com.example.demo.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class TransResponseVo {
	
	
	@XmlElement(name="tran")
	private List<TranResponseVo> tran;

}
