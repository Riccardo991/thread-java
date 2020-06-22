package chickenRace;

public class chicken implements Runnable {
	private String name;
	private chickenHouse k;
	private Thread eat;
	private int belly;
	private int pr;

	// consumer threads
	public chicken(String s, chickenHouse h, int p) {
		name = s;
		this.k = h;
		belly = 0;
		pr = p;
		eat = new Thread(this);
		eat.setPriority(p);
		;
		eat.start();
	}

	public void run() {
		while (!k.isEmpty()) {
			int x = k.takeOne();
			belly = belly + x;
			System.out.print("chicken " + name + " eat\n");
			;
			k.printQ();
		}
	}

	public void getResult() {
		System.out.print("chicken " + name + "with priority " + pr + "  eats " + belly + "\n");
		;
	}

	public void stopChicken() {
		eat.stop();
	}

}
