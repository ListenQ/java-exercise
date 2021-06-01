package com.example.demo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.mult.thread.Executor;

/**
 * servlet3.0异步处理
 * !!需要在Annotation中注明 asyncSupported=true;
 * AsyncDemoServlet<BR>
 * 创建人：zhangqi <BR>
 * 时间：2019年2月1日-上午10:41:15 <BR>
 * @version 1.0.0
 * 
 */
@WebServlet(urlPatterns="/demo",asyncSupported=true,loadOnStartup = -1,name="DemoServlet")
public class AsyncDemoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        System.out.println("121212");
        out.println("进入Servlet的时间：" + new Date() + ".");
        out.flush();
        
        AsyncContext ctx = req.getAsyncContext();
        req.getPart("img");
        new Thread(new Executor(ctx)).start();

        out.println("结束Servlet的时间：" + new Date() + ".");
        out.flush();
	}

	
}
