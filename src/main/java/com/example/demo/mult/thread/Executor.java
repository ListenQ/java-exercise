package com.example.demo.mult.thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;

public class Executor implements Runnable{
	
	private AsyncContext ctx = null;
    public Executor(AsyncContext ctx){
        this.ctx = ctx;
    }

	@Override
	public void run() {
		 try {
			Thread.sleep(10000);
			PrintWriter out = ctx.getResponse().getWriter();
			out.println("业务处理完毕的时间：" + new Date() + ".");
			out.flush();
			ctx.complete();
		} catch (InterruptedException|IOException e) {
			e.printStackTrace();
		}
	}

}
