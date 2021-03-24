package com.example.demo.algorithm;

/**
 * 添加两数之和
 * Ctest_Add_Two_Num
 * @author zhangqi 
 * @date 2021年1月11日-下午7:19:55
 * 
 */
public class Ctest_Add_Two_Num {
	
	public static void main(String[] args) {
		System.out.println(0010 & 0001);
	}
	
	/**
	 * 计算两数之和
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode l = new ListNode();
		
		return l;
    }
	
	
	private static class ListNode {
		private int val;
		private ListNode next;
		
		public ListNode() {}
		
		public ListNode(int val) {
			super();
			this.val = val;
		}

		public ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}

		public int getVal() {
			return val;
		}

		public void setVal(int val) {
			this.val = val;
		}

		public ListNode getNext() {
			return next;
		}

		public void setNext(ListNode next) {
			this.next = next;
		}
		
		
	}

}
