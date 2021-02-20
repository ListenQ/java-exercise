package com.example.demo.test;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bouncycastle.asn1.dvcs.Data;

public class Test8 {
	
	public static void main(String[] args) throws Exception {
		List<User> lists = new ArrayList<Test8.User>();
		
		User user = new User();
		user.setName("AAA");
		user.setAmount(new BigDecimal("0.6"));
		user.setpHoneId("FGFG");
		user.setpHoneName("iphone6");
		lists.add(user);
		
		user = new User();
		user.setName("AAA");
		user.setAmount(new BigDecimal("0.8"));
		user.setpHoneId("KSDKFJSKDFS");
		user.setpHoneName("iphone7");
		lists.add(user);
		
		user = new User();
		user.setName("BBBB");
		user.setAmount(new BigDecimal("1210.00"));
		lists.add(user);
		
		user = new User();
		user.setName("CCCCCC");
		user.setAmount(new BigDecimal("45.014"));
		lists.add(user);
		
		
		Map<String,User> m = new HashMap<>();
		//lists.sort(Comparator.comparing(User::getAmount).reversed());
		lists.forEach(l ->{
			// 一级
			if ("AAA".equals(l.getName())) {
				m.clear();
				m.put(l.getName(), l);
				return;
			}
			//二级
			if ("BBB".equals(l.getName())) {
				m.clear();
				m.put(l.getName(), l);
			}
			//三级
			if ("CCC".equals(l.getName())) {
				if (!m.containsKey("BBB")) {
					m.put(l.getName(), l);
				}
			}
			//四级
			if ("DDD".equals(l.getName())) {
				if (!m.containsKey("CCC") && !m.containsKey("BBB")) {
					m.put(l.getName(), l);
				}
			}
			//五级
			if ("EEE".equals(l.getName())) {
				if (!m.containsKey("CCC") && !m.containsKey("BBB") && !m.containsKey("DDD")) {
					m.put(l.getName(), l);
				}
			}
		});
		
		if ( null != m) {
			
		}
		
		
		
		Map<String,User> map = new HashMap<>();
		for (User u : lists) {
			User data = map.get(u.getName());
			if (data == null) {
				data = new User();
				data.setLists(new ArrayList<>());
			}
			data.setName(u.getName());
			data.setAmount(u.getAmount());
			List<Phone> phones = data.getLists();
			if (u.getpHoneId() != null && u.getpHoneName() != null) {
				phones.add(new Phone(u.getpHoneId(), u.getpHoneName()));
			}
			data.setLists(phones);
			map.put(u.getName(),data);
		}
		
		
		for(Map.Entry<String,User>entry:  map.entrySet()) {
			System.out.println(entry.getValue());
		}
		
		
//		lists.sort(Comparator.comparing(Test8.User::getAmount,Comparator.nullsFirst(BigDecimal::compareTo)).reversed());
		
//		lists.parallelStream().forEach(l->{
//			List<User> newList = lists;
//			newList = newList.stream().sorted(Comparator.comparing(User::getAmount).reversed()).limit(1*3).collect(Collectors.toList());
//			System.out.println("lists.sort:"+newList);
//		});
		
		
		
//		List<User> collect = lists.stream().sorted(Comparator.comparing(User::getAmount).reversed()).limit(1*2).collect(Collectors.toList());
//		System.out.println(collect);
		/*BigDecimal total = lists.stream().filter(lll ->{
			if(lll.getAmount() == null) {
				System.out.printf("数据为空:%s", lll);
			}return lll.getAmount()!=null;
		}).map(ll ->ll.getAmount().add(BigDecimal.TEN)).reduce(BigDecimal.ZERO,BigDecimal::add);*/
		
//		Set<User> s = new HashSet<User>(lists);
//		for (User user2 : s) {
//			System.out.println(user2);
//		}
		
		
	}
	
	static class Phone{
		private String id;
		private String name;
		public Phone() {
		}
		
		
		
		@Override
		public String toString() {
			return "Phone [id=" + id + ", name=" + name + "]";
		}



		public Phone(String id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	
	static class User{
		private String name;
		private BigDecimal amount;
		//Phone对象里的
		private String pHoneId;
		private String pHoneName;
		
		private List<Phone> lists;
		
		
		
		public String getpHoneId() {
			return pHoneId;
		}
		public void setpHoneId(String pHoneId) {
			this.pHoneId = pHoneId;
		}
		public String getpHoneName() {
			return pHoneName;
		}
		public void setpHoneName(String pHoneName) {
			this.pHoneName = pHoneName;
		}
		public List<Phone> getLists() {
			return lists;
		}
		public void setLists(List<Phone> lists) {
			this.lists = lists;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		
		
		
		
		@Override
		public String toString() {
			return "User [name=" + name + ", lists=" + lists + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((amount == null) ? 0 : amount.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			if (amount == null) {
				if (other.amount != null)
					return false;
			} else if (!amount.equals(other.amount))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		
	}

}
