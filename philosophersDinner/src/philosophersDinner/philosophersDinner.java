package philosophersDinner;

import java.util.concurrent.locks.*;

public class philosophersDinner {

	/* 
	monitor exercise: implement the problem of  philosophers   dinner using a monitor.
	we have 5 philosophers   and 5  forks. the philosopher  has 2 states    "think" and "eat".   the philosopher  must use 2 forks to eat.    create a monitor    to manage the share memory   to avoid  deadlock. 
	 */
	
	public static void main(String[] arg) {
		Thread tc = Thread.currentThread();

		System.out.print(" start dinner \n");
		;
		dinner t = new dinner();
		philosopher[] f = new philosopher[5];
		for (int i = 0; i < 5; i++) {
			f[i] = new philosopher(t, i);
		}
		for (int i = 0; i < 5; i++) {
			f[i].start();
		}

		try {
			tc.sleep(10000);
		} catch (Exception e) {
		}
		for (int i = 0; i < 5; i++) {
			f[i].stop();
		}
		System.out.print(" end dinner ");
	}

}
