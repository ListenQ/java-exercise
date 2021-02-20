package com.example.demo.encry.sm2;

import java.math.BigInteger;

import org.bouncycastle.math.ec.ECPoint;

/**
 * SM2
 */
public class SM2Result {
	public SM2Result() {
	}
	// 签名r
	public BigInteger r;
	public BigInteger s;
	//验签R
	public BigInteger R;

	// 密钥交换
	public byte[] sa;
	public byte[] sb;
	public byte[] s1;
	public byte[] s2;

	public ECPoint keyra;
	public ECPoint keyrb;
	
}
