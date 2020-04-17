package com.example.demo.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class HKSnapshotData {
	
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
	 * 昨收
	 */
	private BigDecimal preClose;
	/**
	 * 今开
	 */
	private BigDecimal open;
	/**
	 * 最高
	 */
	private BigDecimal high;
	/**
	 * 最低
	 */
	private BigDecimal low;
	/**
	 * 收盘价
	 */
	private BigDecimal close;
	/**
	 * 最新价
	 */
	private BigDecimal last;
	/**
	 * 平均价
	 */
	private BigDecimal vwap;
	/**
	 * 成交量
	 */
	private BigDecimal sharestraded;
	/**
	 * 成交额
	 */
	private BigDecimal turnover;
	/**
	 * 沽空量
	 */
	private BigDecimal shortshares;
	/**
	 * 沽空额
	 */
	private BigDecimal shortturnover;
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HKSnapshotData other = (HKSnapshotData) obj;
		if (close == null) {
			if (other.close != null)
				return false;
		} else if (!close.equals(other.close))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (high == null) {
			if (other.high != null)
				return false;
		} else if (!high.equals(other.high))
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		if (low == null) {
			if (other.low != null)
				return false;
		} else if (!low.equals(other.low))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (open == null) {
			if (other.open != null)
				return false;
		} else if (!open.equals(other.open))
			return false;
		if (preClose == null) {
			if (other.preClose != null)
				return false;
		} else if (!preClose.equals(other.preClose))
			return false;
		if (sharestraded == null) {
			if (other.sharestraded != null)
				return false;
		} else if (!sharestraded.equals(other.sharestraded))
			return false;
		if (shortshares == null) {
			if (other.shortshares != null)
				return false;
		} else if (!shortshares.equals(other.shortshares))
			return false;
		if (shortturnover == null) {
			if (other.shortturnover != null)
				return false;
		} else if (!shortturnover.equals(other.shortturnover))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (turnover == null) {
			if (other.turnover != null)
				return false;
		} else if (!turnover.equals(other.turnover))
			return false;
		if (vwap == null) {
			if (other.vwap != null)
				return false;
		} else if (!vwap.equals(other.vwap))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((close == null) ? 0 : close.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((high == null) ? 0 : high.hashCode());
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((low == null) ? 0 : low.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((open == null) ? 0 : open.hashCode());
		result = prime * result + ((preClose == null) ? 0 : preClose.hashCode());
		result = prime * result + ((sharestraded == null) ? 0 : sharestraded.hashCode());
		result = prime * result + ((shortshares == null) ? 0 : shortshares.hashCode());
		result = prime * result + ((shortturnover == null) ? 0 : shortturnover.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((turnover == null) ? 0 : turnover.hashCode());
		result = prime * result + ((vwap == null) ? 0 : vwap.hashCode());
		return result;
	}
	
	

}
