package com.example.demo.mult.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * fork/join 测试
 * EncryptTask<BR>
 * 创建人：zhangqi <BR>
 * 时间：2020年2月19日-下午4:38:27 <BR>
 * @version 1.0.0
 * 
 */
public class EncryptTask extends RecursiveTask<List<User>>{
	
	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

	private static final long serialVersionUID = 1L;
	private static final int threshold = 50; //不在将任务分解成子任务的阀值大小
	 //子任务处理的数组开始和终止的位置
	private int start;
	private int end;
	private List<User> data;
	
	public EncryptTask(int start,int end,List<User> data) {
		this.start = start;
		this.end = end;
		this.data = data;
	}
	
	public EncryptTask(List<User> data) {
		this(0, data.size(), data);
	}
	
	@Override
	protected List<User> compute() {
		List<User> list = new ArrayList<>();
		if ((end - start) <= threshold) {//判断是否达到了阀值,否则继续进行任务分解
			//计算加密
			return encryptNumber();
		}else {
			// 如果任务大于阈值 ，就会分解成两个小任务计算
			int middle = (start + end) / 2;
			EncryptTask left = new EncryptTask(start, middle,data);
			EncryptTask right = new EncryptTask(middle, end,data);
			// 执行子任务
			//left.fork();
			//List<User> rightResult = right.compute();
			
			EncryptTask.invokeAll(left,right);
			// 等待子任务执行结束,并得到子任务结果
			list.addAll(left.join());
			list.addAll(right.join());
		}
		return list;
	}
	
	private List<User> encryptNumber(){
		List<User> list = new ArrayList<>();
		for (int i = start; i < end; i++) {
			User user = data.get(i);
			user.setPwd(ENCODER.encode(user.getPwd()));
			list.add(user);
		}
		return list;
	}

}
