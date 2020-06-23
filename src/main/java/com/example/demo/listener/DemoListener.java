package com.example.demo.listener;

import java.io.IOException;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DemoListener {

//	@EventListener
	@EventListener(classes= {DemoEvent.class})
	public void listenerEvent(DemoEvent event) throws IOException {
		log.info("event监听来了{}",event.getMessage());
		listenerHandler(1);
	}
	
	@EventListener(classes= {Demo2Event.class})
	public void listener2Event(Demo2Event event2) throws IOException {
		log.info("event2监听来了{}",event2.getMessage());
		listenerHandler(2);
	}
	
	@EventListener(classes= {Demo3Event.class})
	public void listener3Event(Demo2Event event3) throws IOException {
		log.info("event3监听来了{}",event3.getMessage());
		listenerHandler(3);
	}
	
	
	public void listenerHandler(int number) throws IOException {
		
		System.err.println("事情做完了");
	}
}
