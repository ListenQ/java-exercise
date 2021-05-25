package com.example.demo.vo;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 新股日历中可认购对象
 * @author shenyangkun
 * @date 2019/11/21
 **/
@Data
public class SubscribedDto  {
	
	
	private String ts;
	
	private String code;
	private Integer type;
    /**
     * 证券名称
     */
    private String name;
    /**
     * 每手股数
     */
    private Integer volunit;
    /**
     * 最低发售价
     */
    private BigDecimal minPrice;
    /**
     * 最高发售价
     */
    private BigDecimal maxPrice;
    /**
     * 截至认购日期int格式：20191120
     */
    private Long endTime;
    /**
     * 入场费
     */
    private BigDecimal entranceFee;
    
    /**
     * 手续费
     */
    private BigDecimal handlingFee;

    /**
     * 是否是优选
     */
    private Boolean favor;
    /**
     * 分析的url
     */
    private String analyzeUrl;
}
