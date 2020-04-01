package com.example.demo.test;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Test8 {
	
	public static void main(String[] args) throws Exception {
		List<User> lists = new ArrayList<Test8.User>();
		User user = new User();
		user.setName("aaa");
		user.setAmount(new BigDecimal("0.6"));
		lists.add(user);
		user = new User();
		user.setName("bbbb");
		user.setAmount(new BigDecimal("1210.00"));
		lists.add(user);
		
		user = new User();
		user.setName("cccccc");
		user.setAmount(new BigDecimal("45.014"));
		lists.add(user);
		
//		lists.sort(Comparator.comparing(Test8.User::getAmount,Comparator.nullsFirst(BigDecimal::compareTo)).reversed());
		
		lists.parallelStream().forEach(l->{
			List<User> newList = lists;
			newList = newList.stream().sorted(Comparator.comparing(User::getAmount).reversed()).limit(1*3).collect(Collectors.toList());
			System.out.println("lists.sort:"+newList);
		});
		
		
		
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
