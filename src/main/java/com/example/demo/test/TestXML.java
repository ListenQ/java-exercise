package com.example.demo.test;

import java.util.regex.Pattern;

import com.example.demo.util.XmlUtils;
import com.example.demo.vo.ClientBalanceRequest;
import com.example.demo.vo.GTSRequestVo;
import com.example.demo.vo.TranHistoryResponseVo;

/**
 *
 * @author benky
 */
public class TestXML {

	private static final Pattern pattern = Pattern.compile("</>|<>|\r|\\s+/g");
	private static final Pattern pattern_2 = Pattern.compile("\\s*|\t|\r|\n"); 
	
	public static void main(String[] args) throws Exception {
		
//		ClientBalanceRequest request = new ClientBalanceRequest();
//		request.setUserCode("ZHANGSAN");
//		request.setClientAccCode("ZQ01ZQ01");
//		request.setPassword("123456");
//		
//		GTSRequestVo<ClientBalanceRequest> gts = new GTSRequestVo<>();
//		gts.setBody(request);
//		gts.setRootType("client_balance");
//		gts.setRootMsgnum(""+System.currentTimeMillis());
//		
//		String xml = XmlUtils.toXml(gts);
//		System.out.println(pattern.matcher(xml).replaceAll(""));
		
		

		String xmlStr = "<message type=\"client_tran_hist_response\" msgnum=\"00000005631\"><trans><tran><tran_type>ProductIn</tran_type><date>2020-04-22 15:19:20</date><sett_date>2020-04-22 15:04:23</sett_date><ccy>HKD</ccy><exchange_code>HKEX</exchange_code><product_code>01810</product_code><price></price><qty>500</qty><amt></amt><desc><eng></eng><big5></big5><gb></gb></desc><tran_detail></tran_detail></tran><tran><tran_type>ProductIn</tran_type><date>2020-04-22</date><sett_date>2020-04-22</sett_date><ccy>HKD</ccy><exchange_code>HKEX</exchange_code><product_code>01810</product_code><price></price><qty>1000</qty><amt></amt><desc><eng></eng><big5></big5><gb></gb></desc><tran_detail></tran_detail></tran><tran><tran_type>ProductIn</tran_type><date>2020-04-22</date><sett_date>2020-04-22</sett_date><ccy>HKD</ccy><exchange_code>HKEX</exchange_code><product_code>00700</product_code><price></price><qty>100</qty><amt></amt><desc><eng></eng><big5></big5><gb></gb></desc><tran_detail>PHY</tran_detail></tran><tran><tran_type>TradeSell</tran_type><date>2020-04-14</date><sett_date>2020-04-16</sett_date><ccy>HKD</ccy><exchange_code>HKEX</exchange_code><product_code>00700</product_code><price>387.7</price><qty>800</qty><amt>309034.21</amt><desc><eng></eng><big5></big5><gb></gb></desc><tran_detail></tran_detail></tran><tran><tran_type>CashIn</tran_type><date>2020-04-13</date><sett_date>2020-04-13</sett_date><ccy>USD</ccy><exchange_code></exchange_code><product_code></product_code><price></price><qty></qty><amt>100000</amt><desc><eng></eng><big5></big5><gb></gb></desc><tran_detail>IN_TR</tran_detail></tran><tran><tran_type>ProductIn</tran_type><date>2020-04-10</date><sett_date>2020-04-10</sett_date><ccy>HKD</ccy><exchange_code>HKEX</exchange_code><product_code>00700</product_code><price></price><qty>50</qty><amt></amt><desc><eng></eng><big5></big5><gb></gb></desc><tran_detail></tran_detail></tran><tran><tran_type>CashIn</tran_type><date>2020-04-09</date><sett_date>2020-04-09</sett_date><ccy>HKD</ccy><exchange_code></exchange_code><product_code></product_code><price></price><qty></qty><amt>150000</amt><desc><eng></eng><big5></big5><gb></gb></desc><tran_detail>IN_TR</tran_detail></tran><tran><tran_type>ProductIn</tran_type><date>2020-04-09</date><sett_date>2020-04-09</sett_date><ccy>HKD</ccy><exchange_code>HKEX</exchange_code><product_code>00700</product_code><price></price><qty>2000</qty><amt></amt><desc><eng></eng><big5></big5><gb></gb></desc><tran_detail></tran_detail></tran><tran><tran_type>CashIn</tran_type><date>2020-04-09</date><sett_date>2020-04-09</sett_date><ccy>HKD</ccy><exchange_code></exchange_code><product_code></product_code><price></price><qty></qty><amt>1000000</amt><desc><eng></eng><big5></big5><gb></gb></desc><tran_detail>IN_TR</tran_detail></tran></trans><client_acc_code>CJL-02</client_acc_code><server_time>2020-04-23 11:41:47</server_time></message>";
		
		TranHistoryResponseVo fromXml = XmlUtils.fromXml(xmlStr, TranHistoryResponseVo.class);
		System.out.println(fromXml);
		
		String xmls = XmlUtils.toXml(fromXml);
		
		System.out.println("转换xml:");
		System.out.println();
		System.out.println(xmls);
		
		
	}

}
