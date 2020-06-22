package queueOffice;

import java.util.*;

public class man extends Thread {
	private String name;
	private int n; // num random
	private postOffice p;

	// create a thread for man
	public man(String s, postOffice w) {
		name = s;
		this.p = w;
		n = 0;
	}

	public void run() {
		Random rand = new Random();
		try {
			n = rand.nextInt(5) + 1;
			Thread.sleep(n * 100);
			p.goIntoMan();
			System.out.print("man " + name + " arrives at office \n");
			n = rand.nextInt(9) + 1;
			Thread.sleep(n * 100);
			p.goOutMan();
			System.out.print("man " + name + " leaves office\n");
		} catch (Exception e) {
		}
	}

}
