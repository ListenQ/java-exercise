package com.example.demo.queue;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeListen implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("sdfsfs"+LocalDateTime.now());
		Toolkit.getDefaultToolkit().beep();
	}
	

}
