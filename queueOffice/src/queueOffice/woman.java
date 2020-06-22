package queueOffice;

import java.util.*;

// create a thread for woman 
public class woman extends Thread {
	private String name;
	private int n;
	private postOffice p;

	public woman(String s, postOffice w) {
		name = s;
		n = 0;
		this.p = w;
	}

	public void run() {
		Random rand = new Random();
		try {
			n = rand.nextInt(5) + 1;
			Thread.sleep(n * 100);
			p.goIntoWoman();
			System.out.print("woman " + name + " arrives at office \n");
			n = rand.nextInt(9) + 1;
			Thread.sleep(n * 100);
			p.goOutWoman();
			System.out.print("woman " + name + " leaves office\n");
		} catch (Exception e) {
		}
	}

}
