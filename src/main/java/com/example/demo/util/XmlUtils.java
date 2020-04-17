package com.example.demo.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.stream.XMLStreamException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.thoughtworks.xstream.XStream;

public class XmlUtils {
	
	
	/**
	 * 把xml转为Map 注意如果xml中包含有xml格式的内容 那么需要用<![CDATA[ xml格式的内容 ]]>包含起来,并且不能有空格
	 * 
	 * @param rxml
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xml2map(String rxml) throws Exception {
		SAXBuilder builder = new SAXBuilder();
		Reader in = new StringReader(StringUtils.trimToEmpty(rxml));
		Document doc = null;
		try {
			doc = builder.build(in);
			Element ele = doc.getRootElement();
			List<Element> l = ele.getChildren();
			if (l != null) {
				Map<String, String> map = new HashMap<String, String>();
				List<?> attributes = ele.getAttributes();
				for(int i=0,j = attributes.size();i<j;i++) {
					map.put("root_"+((Attribute)attributes.get(i)).getName(), ((Attribute)attributes.get(i)).getValue());
				}
				for (Element e : l) {
					map.put(StringUtils.trimToEmpty(e.getName()), StringUtils.trimToEmpty(e.getText()));
				}
				return map;
			}
		} finally {
			try {
				if (in != null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	public static JSONObject xml2Json(String rxml) throws Exception{
		Map<String, String> xml2map = xml2map(rxml);
		return (JSONObject) JSON.toJSON(xml2map);
	}

	/**
	 * 把2层结构的xml转化为list 子节点用map存储
	 * 
	 * @param rxml
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, String>> xml2list(String rxml) throws Exception {
		SAXBuilder builder = new SAXBuilder();
		Reader in = new StringReader(StringUtils.trimToEmpty(rxml));
		Document doc = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			doc = builder.build(in);
			Element ele = doc.getRootElement();
			List<Element> l = ele.getChildren();
			if (l != null) {
				for (Element e : l) {
					List<Element> l2 = e.getChildren();
					Map<String, String> map = new HashMap<String, String>();
					for (Element e2 : l2) {
						map.put(StringUtils.trimToEmpty(e2.getName()), StringUtils.trimToEmpty(e2.getText()));
					}
					list.add(map);
				}
				return list;
			}
		} finally {
			try {
				if (in != null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static String map2xml(Map<String, String> map) {
		return null;
	}

	public static String xmlnode(String node, Object o) {
		if (o == null) {
			return "<" + node + "></" + node + ">";
		} else {
			if (o instanceof String) {
				String s = StringUtils.trimToEmpty((String) o);
				s = VeStr.toXmlFormat(s);
				s = VeStr.clearToXml(s);
				return "<" + node + ">" + s + "</" + node + ">";
			} else if (o instanceof Double) {// double类型最多6位小数
				Double d = (Double) o;
				DecimalFormat df = new DecimalFormat("#.######");
				String num = df.format(d);
				return "<" + node + ">" + num + "</" + node + ">";
			} else {
				return "<" + node + ">" + o + "</" + node + ">";
			}
		}
	}

	/**
	 * 根据对象生成XML
	 * 
	 * @param o
	 * @return
	 */
	public static String objectToXml(Object o) {
		StringBuffer xml = new StringBuffer();
		Class c = o.getClass();
		String parentNode = c.getSimpleName().toUpperCase();
		xml.append("<").append(parentNode).append(">");
		Field[] f = c.getDeclaredFields();
		for (Field one : f) {
			Class type = one.getType();
			if ("String".equals(type.getSimpleName())) {
				String name = one.getName();
				String value = null;
				try {
					value = BeanUtils.getProperty(o, name);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
				if (value != null) {
					//xml.append(XmlUtils.xmlnode(name.toUpperCase(), VeStr.formatHtml(value)));
				}
			}
		}
		xml.append("</").append(parentNode).append(">");
		return xml.toString();
	}

	/**
	 * List<Object>生成XML
	 * 
	 * @param l
	 * @return
	 */
	public static String listToXml(List<Object> l) {
		StringBuffer xml = new StringBuffer();
		if (l != null) {
			Iterator<Object> itr = l.iterator();
			while (itr.hasNext()) {
				Object o = itr.next();
				xml.append(objectToXml(o));
			}
		}

		return xml.toString();
	}

	/**
	 * 把多层xml转为Map
	 * 
	 * @param rxml
	 *            xml字符串
	 * @param:schar 传入的特殊需要过滤的字符，如格式为<a><b><c>aa</c>...，此时需传入"b",才可往下读取c
	 * @return 属性名：属性值
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(String rxml, String schar) throws Exception {
		Map xmlMap = new HashMap();
		// 读xml文件
		SAXBuilder builder = new SAXBuilder();
		Reader in = new StringReader(StringUtils.trimToEmpty(rxml));
		Document doc = null;
		List<Element> slist = null;
		try {
			doc = builder.build(in);
			Element ele = doc.getRootElement();
			slist = ele.getChildren();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (slist != null) {
			Map map = new HashMap();
			// 读取xml，返回一个map对象
			xmlMap = readXml(slist, map, schar);
		}
		return xmlMap;
	}

	/**
	 * 将Element型的list转换为map
	 * 
	 * @param slist
	 *            Element集合
	 * @param map1
	 *            用于存放的map
	 * @param schar
	 *            特殊过滤的字符 如传入list格式：<ROOT><PARENT><E1>1</E1><E2><E22><E222>2</E222></E22></E2></PARENT></ROOT>
	 *            则返回e1:1 e222:2 此个数需传入参数schar=e22,否则e222无法读取出来
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Map readXml(List<Element> slist, Map map1, String schar) {
		Map map = map1;
		for (Element e : slist) {
			boolean isflag = false; // 标识是否继续读取节点
			List<Element> slist1 = e.getContent();// 取节点里的内容
			// schar为空时，判断其元素大于1，则继续读下级节点里的内容
			// 如<E1><E11>1</E11><E12>2</E12></E1>
			// schar不为空时，如schar=e22,针对格式<E2><E22><E222>2</E222></E22></E2>
			if (StringUtils.isNotBlank(schar)) {
				if (slist1.size() > 1 || schar.equals(e.getName())) {
					isflag = true;
				}
			} else {
				if (slist1.size() > 1) {
					isflag = true;
				}
			}
			if (isflag) {
				readXml(slist1, map, schar);
			} else {
				map.put(e.getName(), e.getValue());
			}
		}
		return map;
	}

	/**
	 * XML转BEAN 需在传入CLASS对象中注解“@XmlRootElement(name = "ROOT节点名称")” （如XML节点和BEAN属性不同，可以在BEAN属性get方法上注解“@XmlElement(name =
	 * "XXX")”）
	 * 
	 * @param xml
	 * @param rootClass
	 * @return [参数说明]
	 * 
	 * @return Object [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static Object fromXml(String xml, Class<?> rootClass) {
		Object obj = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(rootClass);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			obj = unmarshaller.unmarshal(new StringReader(xml));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 将对象转换成XML 需在传入CLASS对象中注解“@XmlRootElement(name = "ROOT节点名称")”
	 * （如生成XML节点和BEAN属性不同，可以在BEAN属性get方法上注解“@XmlElement(name = "XXX")”）
	 * 
	 * @param bean
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @throws XMLStreamException
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String toXml(Object bean) {
		if (null == bean) {
			return null;
		}
		StringWriter writer = new StringWriter();
		try {
			//2013-05-17 判断有没有@XmlRootElement注解，没有采用其它的转换
			XmlRootElement x = bean.getClass().getAnnotation(XmlRootElement.class);
			if(x == null){
				XStream xs=new XStream();
				return xs.toXML(bean);
			}else{
				JAXBContext jaxbContext = JAXBContext.newInstance(bean.getClass());
				Marshaller marshaller = jaxbContext.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
				marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
				marshaller.marshal(bean, writer);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		} finally {
			IOUtils.close(writer);
		}
		return writer.getBuffer().toString();
	}
	
	
	public static String toXml2(Object bean) {
		if (null == bean) {
			return null;
		}
		XStream xs=new XStream();
		return xs.toXML(bean);
	}

	/**
	 * 将对象转换成XML 带<?xml version="1.0" encoding="UTF-8" standalone="yes"?>格式 需在传入CLASS对象中注解“@XmlRootElement(name =
	 * "ROOT节点名称")” （如生成XML节点和BEAN属性不同，可以在BEAN属性get方法上注解“@XmlElement(name = "XXX")”）
	 * 
	 * @param bean
	 * @param encoding
	 *            编码 默认UTF-8
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @throws XMLStreamException
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String toXmlWithHead(Object bean, String encoding) {
		if (bean == null) {
			return null;
		}
		if (StringUtils.isBlank(encoding)) {
			encoding = "UTF-8";
		}
		StringWriter writer = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(bean.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			marshaller.marshal(bean, writer);
		} catch (JAXBException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return writer.getBuffer().toString();
	}
	/**
	 * 把2层结构的xml转化为list 子节点用map存储 <field name="aa">bb </field>  取出 "aa" "bb"
	 * 
	 * @param rxml
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, String>> xmlTolist(String rxml) throws Exception {
		SAXBuilder builder = new SAXBuilder();
		Reader in = new StringReader(StringUtils.trimToEmpty(rxml));
		Document doc = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			doc = builder.build(in);
			Element ele = doc.getRootElement();
			List<Element> l = ele.getChildren();
			if (l != null) {
				for (Element e : l) {
					List<Element> l2 = e.getChildren();
					Map<String, String> map = new HashMap<String, String>();
					for (Element e2 : l2) {
						map.put(StringUtils.trimToEmpty(e2.getAttributeValue("name")), StringUtils.trimToEmpty(e2.getText()));
					}
					list.add(map);
				}
				return list;
			}
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		String rxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><HBS><ROOT></ROOT><IBEHEAD>2013-05-29</IBEHEAD><HB><XH>1</XH><HBH>CA155</HBH><DS></DS><CW>C8 D2 ZS JS RS YA BQ MQ HQ KQ LQ QQ GQ SA NA VQ UA TQ EQ</CW><SUBCW>M1Q V1Q</SUBCW><CW2></CW2><HC>PEKPVG</HC><CFSJ>0735</CFSJ><DDSJ>0940</DDSJ><JX>738</JX><TLCY>0 1</TLCY><E>E</E><FH></FH><GXHBH></GXHBH><OTHER> 2:05</OTHER></HB><HB><XH>2</XH><HBH>CA1883</HBH><DS></DS><CW>FA AS YA BS MS HS KS LS QS GS S5 NS VS UA TS ES</CW><SUBCW>M1S V1S</SUBCW><CW2></CW2><HC>PEKPVG</HC><CFSJ>0800</CFSJ><DDSJ>1015</DDSJ><JX>32A</JX><TLCY>0 1</TLCY><E>E</E><FH></FH><GXHBH></GXHBH><OTHER> 2:15</OTHER></HB><HB><XH>3</XH><HBH>CA177</HBH><DS></DS><CW>CA DS ZS JS RS YA BQ MQ HQ KQ LQ QQ GQ SQ NS VQ UA TQ EQ</CW><SUBCW>M1Q V1Q</SUBCW><CW2></CW2><HC>PEKPVG</HC><CFSJ>1525</CFSJ><DDSJ>1735</DDSJ><JX>330</JX><TLCY>0 1</TLCY><E>E</E><FH></FH><GXHBH></GXHBH><OTHER> 2:10</OTHER></HB><HB><XH>4</XH><HBH>CA986</HBH><DS></DS><CW>FA AS YA BS MS HS KS LS QS GS SS NS VS UA TS ES</CW><SUBCW>M1S V1S</SUBCW><CW2></CW2><HC>PEKPVG</HC><CFSJ>2000</CFSJ><DDSJ>2215</DDSJ><JX>321</JX><TLCY>0 1</TLCY><E>E</E><FH></FH><GXHBH></GXHBH><OTHER> 2:15</OTHER></HB><HB><XH>5</XH><HBH>CA1861</HBH><DS></DS><CW>FA AA YA BA MA HA KA LA QA GA SA NS VS UA TA ES</CW><SUBCW>M1A V1S</SUBCW><CW2></CW2><HC>PEKPVG</HC><CFSJ>2150</CFSJ><DDSJ>0005</DDSJ><JX>330</JX><TLCY>0 1</TLCY><E>E</E><FH></FH><GXHBH></GXHBH><OTHER> 2:15</OTHER></HB></HBS>";
     List<Map<String, String>> list = XmlUtils.xml2list(rxml);
     Map<String, String> mapTemp = new HashMap<String, String>();
     StringBuffer str = new StringBuffer("{");
     for (Map<String, String> map : list) {
		if(map.size()>0){
			//String cw = map.get("CW");
			String flightNo = map.get("HBH");
			String flightTime = map.get("CFSJ");
			//str.append("\"cw\"").append(":"+cw+",");
			str.append("\"flightNo\"").append(":"+flightNo+",");
			str.append("\"flightTime\"").append(":"+flightTime+",");
		}
	}
     System.out.println(str);
//     Map<String, String> map = XmlUtils.xml2map(rxml);
     
	}
}
