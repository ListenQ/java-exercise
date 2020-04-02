package com.example.demo.cp;

import java.io.Serializable;
import java.sql.Date;

public class Lucky implements Serializable{
	private static final long serialVersionUID = 3631540682512068534L;
	
	private Integer id;
	private Integer lotteryCode;
	private String issue;
	private String openNumber;
	private String openTime;
	private String dragonTiger;
	private String oddEven;
	private String bigSmall;
	private Integer tenantCode;
	private Date createTime;
	private String type;
	
	public Lucky() {}
	
	public Lucky(Integer id, Integer lotteryCode, String issue, String openNumber, String openTime, String dragonTiger,
			String oddEven, String bigSmall, Integer tenantCode, Date createTime) {
		super();
		this.id = id;
		this.lotteryCode = lotteryCode;
		this.issue = issue;
		this.openNumber = openNumber;
		this.openTime = openTime;
		this.dragonTiger = dragonTiger;
		this.oddEven = oddEven;
		this.bigSmall = bigSmall;
		this.tenantCode = tenantCode;
		this.createTime = createTime;
	}
	
	
	public Lucky(String type) {
		super();
		this.type = type;
	}

	public Integer getLotteryCode() {
		return lotteryCode;
	}
	public void setLotteryCode(Integer lotteryCode) {
		this.lotteryCode = lotteryCode;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getOpenNumber() {
		return openNumber;
	}
	public void setOpenNumber(String openNumber) {
		this.openNumber = openNumber;
	}
	public String getOpenTime() {
		return openTime;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public Integer getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(Integer tenantCode) {
		this.tenantCode = tenantCode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDragonTiger() {
		return dragonTiger;
	}
	public void setDragonTiger(String dragonTiger) {
		this.dragonTiger = dragonTiger;
	}
	public String getOddEven() {
		return oddEven;
	}
	public void setOddEven(String oddEven) {
		this.oddEven = oddEven;
	}
	public String getBigSmall() {
		return bigSmall;
	}
	public void setBigSmall(String bigSmall) {
		this.bigSmall = bigSmall;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Lucky [id=" + id + ", lotteryCode=" + lotteryCode + ", issue=" + issue + ", openNumber=" + openNumber
				+ ", openTime=" + openTime + ", dragonTiger=" + dragonTiger + ", oddEven=" + oddEven + ", bigSmall="
				+ bigSmall + ", tenantCode=" + tenantCode + ", createTime=" + createTime + "]";
	}
	
}
