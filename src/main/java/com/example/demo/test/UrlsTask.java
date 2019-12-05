package com.example.demo.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class UrlsTask extends RecursiveTask<List<String>>{

	private static final long serialVersionUID = 1L;
	private List<String> urls;
	
    public UrlsTask(List<String> urls) {
        this.urls = urls;
    }
    
	
	@Override
	protected List<String> compute() {
		List<String> rtn = new ArrayList<>();
		if(urls.size() == 1){
			// http url 请求业务操作
			
			//请求url 后的返回
			rtn.add(urls.get(0)+"&");
		}else {
			List<UrlsTask> subTasks = new ArrayList<UrlsTask>();
			for(String url : urls){
                UrlsTask subTask = new UrlsTask(Arrays.asList(url));
                subTasks.add(subTask);
                subTask.fork();
            }
			for(UrlsTask t : subTasks){
				rtn.addAll(t.join());
			}
		}
		return rtn;
	}
	
	

}
