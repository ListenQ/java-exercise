package com.example.demo.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class USSnapshotData{
	
	
    private String symbol;

    private String timeUs;
    
    private String timeZn;
    
    private BigDecimal preclose;
    
    private BigDecimal lastprice;
    
    private BigDecimal open;
    
    private BigDecimal high;
    
    private BigDecimal low;
    
    private BigDecimal close;
    
    private BigDecimal allmarketvol;
    
    private BigDecimal lasttradeprice;
    private BigDecimal lasttradeqty;
    private Integer regsho;

    private String state;
    
    private String sessionid;
    
    private BigDecimal totalvol;
    
    private BigDecimal totalamount;
    
    private String market;
    
    private String tradetype;
    
    private Integer direction;
    
    

    

}