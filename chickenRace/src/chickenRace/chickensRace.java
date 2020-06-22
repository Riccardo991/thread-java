package chickenRace;

import java.util.concurrent.*;
import java.util.*;

public class chickensRace {

	/* 
	exercise race thread: we have 1 producer (farmer)   and n consumer  (chickens).   the  consumer  threads have different priority.  we use the blokingqueue and the command wait and notify to manage the threads. 
	at the end of the race we can see  what   chicken eat more.
	*/
	
	public static void main(String[] arg) {
		Thread tc = Thread.currentThread();

		chickenHouse k = new chickenHouse(10);
		farmer f = new farmer("bob", k);

		int n = 10;
		chicken[] c = new chicken[n];
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			String s = String.valueOf(i);
			int p = rand.nextInt(8) + 1;
			c[i] = new chicken(s, k, p);
		}
		try {
			tc.sleep(5000);
		} catch (Exception e) {
		}

		f.stopFarmer();
		for (int i = 0; i < n; i++) {
			c[i].stopChicken();
		}
		for (int i = 0; i < n; i++) {
			c[i].getResult();
		}
	}

}
