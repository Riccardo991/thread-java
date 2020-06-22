package shoppingTime;

import java.util.concurrent.Semaphore;
import java.util.*;

public class shoppingTime {

	/*
	  this exercise solve the problem of n producer vs n consumer using the
	  semaphores. there are n thread farmer that produces fruits and n thread
	  client that consumer the fruits. the market has 4 fruit box, one for each
	  type of fruit, that are managed by semaphore. in the end we know what farmer
	   has solt more fruit, what client has bought more and how may money the	 
	 market has earned.
	 */

	public static void main(String[] arg) {
		Thread tc = Thread.currentThread();

		market m = new market(30);
		System.out.print("the market start with  " + m.getEarn() + " money \n");
		farmer[] fr = new farmer[8];
		client[] cl = new client[16];
		Random rand = new Random();

		for (int i = 0; i < 8; i++) {
			String s1 = String.valueOf(i);
			int r1 = rand.nextInt(4);
			String t1 = "";
			if (r1 == 0) {
				t1 = "apple";
			}
			if (r1 == 1) {
				t1 = "banana";
			}
			if (r1 == 2) {
				t1 = "orange";
			}
			if (r1 == 3) {
				t1 = "cherry";
			}
			int q = rand.nextInt(9) + 5;
			fr[i] = new farmer(s1, t1, q, m);
			System.out.print("ready farmer: " + s1 + "he sells: " + q + t1 + "\n");
		}
		for (int j = 0; j < 16; j++) {
			String s2 = String.valueOf(j);
			int r2 = rand.nextInt(4);
			String t2 = "";
			if (r2 == 0) {
				t2 = "apple";
			}
			if (r2 == 1) {
				t2 = "banana";
			}
			if (r2 == 2) {
				t2 = "orange";
			}
			if (r2 == 3) {
				t2 = "cherry";
			}
			double d = (double) rand.nextInt(15) + 9;
			cl[j] = new client(s2, t2, d, m);
			System.out.print(" ready client " + s2 + " he has " + d + " and wants " + t2 + "\n");
		}

		for (int i = 0; i < 8; i++) {
			fr[i].goToSell();
		}
		for (int j = 0; j < 16; j++) {
			cl[j].goToBuy();
		}

		try {
			tc.sleep(20000);
		} catch (Exception e) {
		}

		for (int i = 0; i < 8; i++) {
			fr[i].endSell();
		}
		for (int j = 0; j < 16; j++) {
			cl[j].endBuy();
		}
		for (int i = 0; i < 8; i++) {
			double d = fr[i].getMoney();
			String f = fr[i].getFruit();
			System.out.print(" the farmer " + i + " sell " + f + " has got " + d + " money\n");
		}
		for (int j = 0; j < 16; j++) {
			int q = cl[j].getQuantity();
			String t = cl[j].getFruit();
			System.out.print("the client " + j + " has bought " + q + " " + t + "\n");
		}
		System.out.print(" the market has earn " + m.getEarn());
	}

}
