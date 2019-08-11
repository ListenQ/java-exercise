package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Test3 {
	
	public static void main(String[] args) {
		String [][] names = {
				{"tag_lc","flag_batch","batch_status"},
				{"tag_lc","flag_not_batch","batch_status"},
				{"tag_cgi","flag_batch","batch_status"},
				{"tag_cgi","flag_not_batch","batch_status"},
				{"tag_dl","flag_batch","batch_status"},
				{"tag_dl","flag_not_batch","batch_status"},
				{"tag_other","flag_batch","batch_status"},
				{"tag_other","flag_not_batch","batch_status"}
				};
	
		List<Detail> list = new ArrayList<>();
		for(int i=0;i<names.length;i++) {
			list.add(new Detail(names[i][0], names[i][1], names[i][2]));
		}
		System.out.println(list);
	}
	
	static class Detail{
		
		private String name;
		private String pwd;
		private String ads;
		public Detail() {}
		
		public Detail(String name, String pwd, String ads) {
			super();
			this.name = name;
			this.pwd = pwd;
			this.ads = ads;
		}
		

		@Override
		public String toString() {
			return "Detail [name=" + name + ", pwd=" + pwd + ", ads=" + ads + "]";
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		public String getAds() {
			return ads;
		}
		public void setAds(String ads) {
			this.ads = ads;
		}
		
		
	}

}
