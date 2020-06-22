package shoppingTime;

public class client implements Runnable {
	private String name;
	private String fruit;
	private int quantity;
	private double money;
	private market m;
	private Thread buy;

	// in this class implements a client using a thread. client is a consumer thread
	// that takes a type of fruit to the market
	public client(String s, String t, double d, market k) {
		name = s;
		fruit = t;
		money = d;
		quantity = 0;
		this.m = k;
		buy = new Thread(this, s);
	}

	public void goToBuy() {
		buy.start();
	}

	public void run() {
		try {
			while (money > 0) {
				money = m.getToMarket(name, money, fruit);
				quantity++;

			}
		} catch (Exception e) {
		}
	}

	public void endBuy() {
		buy.stop();
	}

	public int getQuantity() {
		return quantity;
	}

	public String getFruit() {
		return fruit;
	}

}
