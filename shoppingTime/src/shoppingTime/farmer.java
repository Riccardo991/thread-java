package shoppingTime;

public class farmer implements Runnable {
	private String name;
	private String fruit;
	private int quantity;
	private double money;
	private market m;
	private Thread sell;

	// in this class implement a farmer. farmer is a producer thread that sells a
	// type of fruit to market.
	public farmer(String s, String t, int q, market k) {
		name = s;
		fruit = t;
		quantity = q;
		money = 0;
		this.m = k;
		sell = new Thread(this, s);
	}

	public void goToSell() {
		sell.start();
	}

	public void run() {
		double x = 0;
		try {
			while (quantity > 0) {
				x = m.putInMarket(name, fruit);
				money = money + x;
				quantity--;
			}
		} catch (Exception e) {
		}
	}

	public void endSell() {
		sell.stop();
	}

	public double getMoney() {
		return money;
	}

	public String getFruit() {
		return fruit;
	}

}
