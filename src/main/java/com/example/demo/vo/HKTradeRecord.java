package com.example.demo.vo;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 港股逐笔成交
 *
 * @author zhouyuan
 * @date 2019/10/22
 */
@Data
public class HKTradeRecord {

    /**
     * 股票代码
     */
    private String code;
    /**
     * 股票名称
     */
    private String name;
    /**
     * 行情时间
     */
    private String time;
    /**
     * 序号
     */
    private String tickerid;
    /**
     * 成交价
     */
    private BigDecimal price;
    /**
     * 成交量
     */
    private BigDecimal qty;
    /**
     * 成交类型
     */
    private String type;
    /**
     * 撤单标识
     */
    private String cancelflag;
    
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HKTradeRecord other = (HKTradeRecord) obj;
		if (cancelflag == null) {
			if (other.cancelflag != null)
				return false;
		} else if (!cancelflag.equals(other.cancelflag))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (qty == null) {
			if (other.qty != null)
				return false;
		} else if (!qty.equals(other.qty))
			return false;
		if (tickerid == null) {
			if (other.tickerid != null)
				return false;
		} else if (!tickerid.equals(other.tickerid))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cancelflag == null) ? 0 : cancelflag.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((qty == null) ? 0 : qty.hashCode());
		result = prime * result + ((tickerid == null) ? 0 : tickerid.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
    
    
    

}
