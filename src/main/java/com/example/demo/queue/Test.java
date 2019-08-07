package com.example.demo.queue;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Test {

	
	 public static void main(String[] args) {
		  ActionListener listen = new TimeListen();
		  javax.swing.Timer t = new javax.swing.Timer(1000, listen);
		  t.start();
		  
		  JOptionPane.showMessageDialog(null, "要停止么？");
		  System.exit(0);
	}
}
