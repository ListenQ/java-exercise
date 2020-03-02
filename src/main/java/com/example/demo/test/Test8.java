package com.example.demo.test;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test8 {
	
	public static void main(String[] args) throws Exception {
		List<String> list= new ArrayList<>();
		list.add(null);
		list.add("asdf");
		list.add("sadfa");
//		list.stream().map(ll ->{return "";}).collect(Collectors.toList());
		List<String> l = new ArrayList<String>();
		
		List<String> collect = IntStream.range(0, list.size()).mapToObj(ll ->{
			return list.get(ll);
		}).collect(Collectors.toList());

		BigDecimal d1 = null;
		BigDecimal d2  = new BigDecimal("10.2");
//		System.out.println(d2.add(d1==null?BigDecimal.ZERO:d1));
//		System.out.println((d1==null?BigDecimal.ZERO:d1).add(d2));
		
		List<User> lists = new ArrayList<Test8.User>();
		User user = new User();
		user.setName("aaa");
		user.setAmount(new BigDecimal("2110.00"));
		lists.add(user);
		user = new User();
		user.setName("aaa");
		user.setAmount(new BigDecimal("2110.00"));
		lists.add(user);
		
		Map<User, User> map = lists.parallelStream().collect(Collectors.toMap(u ->u, u->u));
		map.forEach((k,v)->{
			System.out.println(v);
		});
		Set<User> s = new HashSet<User>(lists);
		for (User user2 : s) {
			System.out.println(user2.hashCode());
		}
	}
	
	static class User{
		private String name;
		private BigDecimal amount;
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
			return "User [name=" + name + ", amount=" + amount + "]";
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
