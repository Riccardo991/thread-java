package chickenRace;

public class farmer implements Runnable {
	private String name;
	private chickenHouse k;
	private Thread putFood;

	// producer thread
	public farmer(String s, chickenHouse h) {
		name = s;
		this.k = h;
		putFood = new Thread(this);
		putFood.start();
	}

	public void run() {
		int i = 20;
		while (i > 0) {
			if (k.isEmpty()) {
				k.putIn();
				System.out.print("farmer  " + name + " puts food \n");
				k.printQ();
				i--;
			}
		}
	}

	public void stopFarmer() {
		putFood.stop();
	}
}
