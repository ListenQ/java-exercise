package com.example.demo.vo;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 港股指数行情快照
 *
 * @author zhouyuan
 * @date 2019/10/22
 */
@Data
public class HKIndexRecord{

    /**
     * 指数代码
     */
    private String code;
    /**
     * 行情时间
     */
    private String time;
    /**
     * 指数状态
     */
    private String indexstatus;

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
     * 成交量
     */
    private BigDecimal totalvol;
    /**
     * 成交额
     */
    private BigDecimal turnover;
    /**
     * 涨跌
     */
    private BigDecimal netchgpreday;
    /**
     * 涨跌%
     */
    private BigDecimal netchgpredaypct;

    /**
     * 预估结算值
     */
    private BigDecimal easvalue;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HKIndexRecord other = (HKIndexRecord) obj;
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
		if (easvalue == null) {
			if (other.easvalue != null)
				return false;
		} else if (!easvalue.equals(other.easvalue))
			return false;
		if (high == null) {
			if (other.high != null)
				return false;
		} else if (!high.equals(other.high))
			return false;
		if (indexstatus == null) {
			if (other.indexstatus != null)
				return false;
		} else if (!indexstatus.equals(other.indexstatus))
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
		if (netchgpreday == null) {
			if (other.netchgpreday != null)
				return false;
		} else if (!netchgpreday.equals(other.netchgpreday))
			return false;
		if (netchgpredaypct == null) {
			if (other.netchgpredaypct != null)
				return false;
		} else if (!netchgpredaypct.equals(other.netchgpredaypct))
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
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (totalvol == null) {
			if (other.totalvol != null)
				return false;
		} else if (!totalvol.equals(other.totalvol))
			return false;
		if (turnover == null) {
			if (other.turnover != null)
				return false;
		} else if (!turnover.equals(other.turnover))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((close == null) ? 0 : close.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((easvalue == null) ? 0 : easvalue.hashCode());
		result = prime * result + ((high == null) ? 0 : high.hashCode());
		result = prime * result + ((indexstatus == null) ? 0 : indexstatus.hashCode());
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((low == null) ? 0 : low.hashCode());
		result = prime * result + ((netchgpreday == null) ? 0 : netchgpreday.hashCode());
		result = prime * result + ((netchgpredaypct == null) ? 0 : netchgpredaypct.hashCode());
		result = prime * result + ((open == null) ? 0 : open.hashCode());
		result = prime * result + ((preClose == null) ? 0 : preClose.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((totalvol == null) ? 0 : totalvol.hashCode());
		result = prime * result + ((turnover == null) ? 0 : turnover.hashCode());
		return result;
	}
    
    
    


}
