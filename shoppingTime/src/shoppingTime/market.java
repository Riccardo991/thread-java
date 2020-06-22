package shoppingTime;

import java.util.concurrent.Semaphore;

public class market {
	private double cassa;
	private int[] fruits;
	private Semaphore[] mutex;
	private Semaphore sellers;
	private Semaphore clients;

	/*     
	 market  is the objet that  reppresent the  share memory.  I manage the market  whit semaphores.
	in the market there are 4 box  one each for type of fruit.   for each  box there is a mutex that lok the 
	memory.  there are also 2 queue  semaphore one for farmer  and one for client.
	 */
	
	public market(double d) {
		cassa = d;
		fruits = new int[4];
		for (int y = 0; y < 4; y++) {
			fruits[y] = 0;
		}
		mutex = new Semaphore[4];
		for (int i = 0; i < 4; i++) {
			mutex[i] = new Semaphore(1);
		}
		sellers = new Semaphore(4);
		clients = new Semaphore(0);
	}

	public double putInMarket(String n, String t) {
		int k = 0;
		double x = 0;
		try {
			sellers.acquire();
			switch (t) {
			case "apple":
				k = 0;
				;
				break;
			case "banana":
				k = 1;
				break;
			case "orange":
				k = 2;
				break;
			case "cherry":
				k = 3;
				break;
			}
			if (fruits[k] < 2 && cassa >= 2) {
				mutex[k].acquire();
				switch (t) {
				case "apple":
					x = 1.2;
					cassa = cassa - 1.2;
					break;
				case "banana":
					x = 1.8;
					cassa = cassa - 1.8;
					break;
				case "orange":
					x = 1.5;
					cassa = cassa - 1.5;
					break;
				case "cherry":
					x = 2;
					cassa = cassa - 2;
					break;
				}
				fruits[k]++;
				System.out.print("the farmer " + n + "sells " + t + " has got " + x + "money \n");
				mutex[k].release();
			}
			clients.release();
		} catch (Exception e) {
		}
		return x;
	}

	public double getToMarket(String n, double m, String t) {
		double x = 0;
		int k = 0;
		try {
			clients.acquire();
			switch (t) {
			case "apple":
				k = 0;
				break;
			case "banana":
				k = 1;
				break;
			case "orange":
				k = 2;
				break;
			case "cherry":
				k = 3;
				break;
			}
			if (fruits[k] > 1 && m >= 2.5) {
				mutex[k].acquire();
				switch (t) {
				case "apple":
					x = m - 1.5;
					cassa = cassa + 1.5;
					break;
				case "banana":
					x = m - 2;
					cassa = cassa + 2;
					break;
				case "orange":
					x = m - 1.8;
					cassa = cassa + 1.8;
					break;
				case "cherry":
					x = m - 2.5;
					cassa = cassa + 2.5;
					break;
				}
				fruits[k]--;
				System.out.print("the client " + n + " has bought one " + t + "\n");
				mutex[k].release();
			}
			sellers.release();
		} catch (Exception e) {
		}
		return x;
	}

	public double getEarn() {
		return cassa;
	}

}
