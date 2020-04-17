package com.example.demo.test;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.XmlUtils;
import com.example.demo.vo.AddOrderRequest;
import com.example.demo.vo.CancelOrderRequest;
import com.example.demo.vo.ClientCashDWRequest;
import com.example.demo.vo.ClientOrderRequest;
import com.example.demo.vo.ClientTransactionHistoryRequest;
import com.example.demo.vo.OrderEnqRequest;
import com.example.demo.vo.ProtfolioRequest;
import com.example.demo.vo.TradesOrderRequest;
import com.example.demo.vo.UpdateOrderRequest;
import com.example.demo.vo.UserAuthRequest;

/**
 * @author benky
 */
public class TestLoginZQ {

	private static int hostPort = 18243;
	private static String hostIp = "175.45.33.54";
	private static Socket socket;
	private static OutputStream oos;
	private static DataInputStream ois;
	private static byte[] header = null;
	private static int currChar = -1;

	private static int msglen = 0;
	private static String msgheader = "";
	private static String newmsgheader = "";
	private static String responsemsg = "";
	private static boolean flag = true;
	private static int sleepTime = 200;
	
	static{
		try {
			socket = new Socket(hostIp, hostPort);
			oos = socket.getOutputStream();
			ois = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		try {
			/*--------------------------------------------------BEGIN  Login AUTHENTICATION ------------------------------------------------------------------*/
			System.err.println("-----------------------------------------Begin to Login---------------------------");
			UserAuthRequest userAuthRequest = new UserAuthRequest();
			
			userAuthRequest.setRootType("login");
			userAuthRequest.setRootMsgnum("00000001");
			userAuthRequest.setSite("ZHUOR");
			userAuthRequest.setStation("ZHUOR-API");
			userAuthRequest.setType("");
			userAuthRequest.setUser("ZHUORAPI");
			userAuthRequest.setPassword("88888888");
			userAuthRequest.setOrderRecovery("N");
			userAuthRequest.setDisableNotification("N");
			
			JSONObject json = new JSONObject();
			json.put("site", "ZHUOR");
			json.put("station", "ZHUOR-API");
			json.put("type", "");
			json.put("user", "ZHUORAPI");
			json.put("password", "88888888");
			json.put("order_recovery", "N");
			json.put("disable_notification", "N");
			
			UserAuthRequest javaObject = JSONObject.toJavaObject(json, UserAuthRequest.class);
			
			System.out.println(XmlUtils.toXml(javaObject));
			
			// TODO 对象转xml
			String xml = XmlUtils.toXml(userAuthRequest);
			
			String command = "<message type=\"login\" msgnum=\"00000001\">\n" +
					"  <site>ZHUOR</site>\n" +
					"  <station>ZHUOR-API</station>\n" +
					"  <type></type>\n" +
					"  <user>ZHUORAPI</user>\n" +
					"  <password>88888888</password>\n" +
					"  <order_recovery>N</order_recovery>\n" +
					"  <disable_notification>N</disable_notification>\n" +
					"</message>";

			command = xml;
			
			print(command);
			// TODO xml转bean
//			System.out.println(XmlUtils.fromXml(responsemsg, UserAuthResponse.class));
			Thread.currentThread().sleep(sleepTime);
			System.err.println("-----------------------------------------end to Login---------------------------");

			/*--------------------------------------------------BEGIN  CLIEN AUTHENTICATION ------------------------------------------------------------------*/
			System.err.println("----------------------------------Begin to Client Auth-----------------------------------------");

			command = "<message type=\"client_auth\" msgnum=\"121212121\" encoding=\"UTF-8\">\n" +
					"<type>INTERNET</type>\n" +
					"<client_acc_code>ZQ01ZQ01</client_acc_code>\n" +
					"<pwd>ustvcxrqs8b</pwd>\n" +
					"<log_login>Y</log_login>\n" +
					"<log_login_remark>MiProgram</log_login_remark>\n" +
					"<require_trading_group>N</require_trading_group>\n" +
					"<require_price_entitlement>N</require_price_entitlement>\n" +
					"<master_user>Y</master_user>\n" +
					"<ip_address>192.168.2.53</ip_address>\n" +
					"<acc_type>C</acc_type>\n" +
					"</message>";
			
			print(command);
			Thread.currentThread().sleep(sleepTime);
			System.err.println("----------------------------------end to Client Auth-----------------------------------------");

			System.err.println("--------------------------------查询客户存款记录开始----------------------------------------------------");
			ClientCashDWRequest cashRequest = new ClientCashDWRequest();
			cashRequest.setRootType("cash_io");
			cashRequest.setRootMsgnum("571114");
			cashRequest.setClientAccCode("ZQ01ZQ01"); //客户户口
			cashRequest.setUserCode("ZHUORAPI");  //大user 即是登录的user资料
			cashRequest.setPassword("ustvcxrqs8b");
			cashRequest.setFromTradeDate("2020-03-30");
			cashRequest.setToTradeDate("2020-04-31");
			cashRequest.setNewTimeFormat("YYYY-MM-DD HH:mm:ss");
			
			xml = XmlUtils.toXml(cashRequest);
			
			command = xml;
			print(command);
			Thread.currentThread().sleep(sleepTime);
			System.err.println("----------------------------------查询客户存款记录结束-----------------------------------------");
			
			System.err.println("--------------------------------查询客户余额开始----------------------------------------------------");
//			ClientBalanceRequest balanceRequest = new ClientBalanceRequest();
//			balanceRequest.setRootType("client_balance");
//			balanceRequest.setRootMsgnum("0124457");
//			balanceRequest.setClientAccCode("ZQ01ZQ01");
//			balanceRequest.setUserCode("ZHUORAPI");
//			balanceRequest.setPassword("ustvcxrqs8b");
			
//			xml = XmlUtils.toXml(balanceRequest);
			
			command = xml;
			print(command);
			Thread.currentThread().sleep(sleepTime);
			System.err.println("--------------------------------查询客户余额结束-----------------------------------------");

			
			System.err.println("--------------------------------下订单买开始------------------------------------------------------");
			AddOrderRequest buyRequest = new AddOrderRequest();
			buyRequest.setRootType("order_action:Add");
			buyRequest.setRootMsgnum("0000000561");
			buyRequest.setBs_flag("B"); // B 买
			buyRequest.setClientAccCode("ZQ01ZQ01");
			buyRequest.setUserCode("ZHUORAPI");
			buyRequest.setPassword("ustvcxrqs8b");
			buyRequest.setExchange_code("HKEX");
			buyRequest.setProduct_code("00005");
			buyRequest.setOrder_type("I"); // 拍卖
			buyRequest.setPrice("40.0");
			buyRequest.setQty("10");
			buyRequest.setReference(System.currentTimeMillis()+"");
			buyRequest.setIp_address("192.168.2.53");
			
			xml = XmlUtils.toXml(buyRequest);
			
			command = xml;
			print(command);
			Thread.currentThread().sleep(sleepTime);
			System.err.println("----------------------------------下订单买结束-----------------------------------------");
			
			System.err.println("--------------------------------客户订单查询请求开始------------------------------------------------------");
			ClientOrderRequest clientOrderReq = new ClientOrderRequest();
			clientOrderReq.setRootType("client_order");
			clientOrderReq.setRootMsgnum("00000005622");
			clientOrderReq.setClient_acc_code("ZQ01ZQ01");
			clientOrderReq.setUser_code("ZHUORAPI");
			clientOrderReq.setPassword("ustvcxrqs8b");
			clientOrderReq.setFrom_trade_date("2020-04-01");
			clientOrderReq.setTo_trade_date("2020-04-30");
			
			xml = XmlUtils.toXml(clientOrderReq);
			command = xml;
			print(command);
			Thread.currentThread().sleep(sleepTime);
			System.err.println("----------------------------------客户订单查询请求结束-----------------------------------------");
			
			System.err.println("--------------------------------更改订单开始------------------------------------------------------");
			UpdateOrderRequest updateRequest = new UpdateOrderRequest();
			updateRequest.setRootType("order_action:Update");
			updateRequest.setRootMsgnum("0000000562");
			updateRequest.setClient_acc_code("ZQ01ZQ01");
			updateRequest.setUser_code("ZHUORAPI");
			updateRequest.setPassword("ustvcxrqs8b");
			updateRequest.setReference("1586332304509"); //
			updateRequest.setOrder_no("10");
			updateRequest.setPrice("41");
			updateRequest.setQty("450");
			
			xml = XmlUtils.toXml(updateRequest);
			
			command = xml;
			print(command);
			Thread.currentThread().sleep(sleepTime);
			System.err.println("----------------------------------更改订单结束-----------------------------------------");
			
			
			System.err.println("--------------------------------下订单卖开始------------------------------------------------------");
			AddOrderRequest sellRequest = new AddOrderRequest();
			sellRequest.setRootType("order_action:Add");
			sellRequest.setRootMsgnum("0000000561");
			sellRequest.setBs_flag("S"); // B 买
			sellRequest.setClientAccCode("ZQ02ZQ02");
			sellRequest.setUserCode("ZHUORAPI");
			sellRequest.setPassword("jaqykvapdg7");
			sellRequest.setExchange_code("HKEX");
			sellRequest.setProduct_code("00005");
			sellRequest.setOrder_type("I"); // 拍卖
			sellRequest.setPrice("41.0");
			sellRequest.setQty("450");
			sellRequest.setReference(System.currentTimeMillis()+"");
			sellRequest.setIp_address("192.168.2.53");
			
			xml = XmlUtils.toXml(sellRequest);
			
			command = xml;
			print(command);
			Thread.currentThread().sleep(sleepTime);
			System.err.println("----------------------------------下订单卖结束-----------------------------------------");
			
			
			System.err.println("--------------------------------订单交易查询请求开始------------------------------------------------------");
			TradesOrderRequest tradesRequest = new TradesOrderRequest();
			tradesRequest.setRootType("order_trades_enq");
			tradesRequest.setRootMsgnum("0000000561");
			tradesRequest.setUser_code("ZHUORAPI");
			tradesRequest.setPassword("jaqykvapdg7");
			tradesRequest.setOrder_no("10");
			
			xml = XmlUtils.toXml(tradesRequest);
			
			command = xml;
			print(command);
			Thread.currentThread().sleep(sleepTime);
			System.err.println("----------------------------------订单交易查询请求开始-----------------------------------------");
			
			
			System.err.println("--------------------------------单一的订单查询请求开始------------------------------------------------------");
			OrderEnqRequest enqRequest = new OrderEnqRequest();
			enqRequest.setRootType("order_enq");
			enqRequest.setRootMsgnum("00000005614");
			enqRequest.setUser_code("ZHUORAPI");
			enqRequest.setPassword("jaqykvapdg7");
			enqRequest.setOrder_no("10");
			enqRequest.setReference("");
			
			xml = XmlUtils.toXml(enqRequest);
			
			command = xml;
			print(command);
			Thread.currentThread().sleep(sleepTime);
			System.err.println("----------------------------------单一的订单查询请求开始-----------------------------------------");
			
			
			System.err.println("--------------------------------关闭订单开始------------------------------------------------------");
			CancelOrderRequest cancelRequest = new CancelOrderRequest();
			cancelRequest.setRootType("order_action:Cancel");
			cancelRequest.setRootMsgnum("0000000563");
			cancelRequest.setClient_acc_code("ZQ02ZQ02");
			cancelRequest.setUser_code("ZHUORAPI");
			cancelRequest.setPassword("jaqykvapdg7");
			cancelRequest.setReference("1586418882666");
			cancelRequest.setOrder_no("19");
			
			xml = XmlUtils.toXml(cancelRequest);
			
			command = xml;
			print(command);
			Thread.currentThread().sleep(sleepTime);
			System.err.println("----------------------------------关闭订单结束-----------------------------------------");
			
			System.err.println("--------------------------------客户交易历史查询请求开始------------------------------------------------------");
			ClientTransactionHistoryRequest trHistoryReq = new ClientTransactionHistoryRequest();
			trHistoryReq.setRootType("client_tran_hist");
			trHistoryReq.setRootMsgnum("00000005631");
			trHistoryReq.setClient_acc_code("ZQ01ZQ01");
			trHistoryReq.setUser_code("ZHUORAPI");
			trHistoryReq.setPassword("ustvcxrqs8b");
			trHistoryReq.setFrom_trade_date("2020-03-01");
			trHistoryReq.setTo_trade_date("2020-04-30");
			
			xml = XmlUtils.toXml(trHistoryReq);
			
			command = xml;
			print(command);
			Thread.currentThread().sleep(sleepTime);
			System.err.println("----------------------------------客户交易历史查询请求结束-----------------------------------------");
			
			
			System.err.println("--------------------------------查询持仓开始------------------------------------------------------");
			ProtfolioRequest proRequest = new ProtfolioRequest();
			proRequest.setRootType("portfolio");
			proRequest.setRootMsgnum("0000000561");
			proRequest.setClientAccCode("ZQ01ZQ01");
			proRequest.setUserCode("ZHUORAPI");
			proRequest.setPassword("ustvcxrqs8b");
			
			xml = XmlUtils.toXml(proRequest);
			
			command = xml;
			print(command);
			Thread.currentThread().sleep(sleepTime);
			System.err.println("----------------------------------查询持仓结束-----------------------------------------");
			
			
			System.out.println("end socket");
			oos.close();
			ois.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	private static int ConvertFromEndianByte(String msgheader) throws Exception {
		String newmsgheader = "";
		for (int k = 8; k >= 2; k -= 2) {
			newmsgheader = newmsgheader + msgheader.substring(k - 2, k);
		}
		newmsgheader = newmsgheader.toUpperCase();
		return Integer.parseInt(newmsgheader, 16);
	}

	private static byte[] CovertToEndianByte(String command) throws Exception {
		int strLen = command.length();
		int j = 0;
		byte[] header = new byte[4];
		String strLenHex = "00000000" + Integer.toHexString(strLen);
		strLenHex = strLenHex.toUpperCase();
		strLenHex = strLenHex.substring(strLenHex.length() - 8);
		for (int i = strLenHex.length(); i >= 2; i -= 2) {
			header[j] = (byte) Integer.parseInt(strLenHex.substring(i - 2, i), 16);
			j += 1;
		}
		return header;
	}
	
	private static void print(String command) throws Exception {
		System.out.println("write complete message: \r\n" + command);
		if(flag) {
			return;
		}
		header = CovertToEndianByte(command);
		oos.write(header);
		oos.write(command.getBytes()); // need encryption for this command string
		oos.flush();
		System.out.println("write complete, msg len = " + command.length());
		System.out.println("write response message: ");
		currChar = ois.readInt();
		msgheader = Integer.toHexString(currChar);
		msglen = ConvertFromEndianByte(msgheader);
		responsemsg = "";
		for (int j = 0; j < msglen; j += 1) {
			currChar = ois.read();
			responsemsg = responsemsg + String.valueOf(Character.toChars(currChar));
		}
		System.out.println("response message length: " + Integer.toString(msglen));
		System.out.println("response message content: ");
		System.out.println(responsemsg);
	}

}

