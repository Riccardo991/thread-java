package barSport;

//  
public class barman extends Thread {
	private bar b;

	// create the thread for barman
	public barman(bar x) {
		this.b = x;
	}

	public void run() {
		int k = 4;
		try {
			while (k > 0) {
				b.startClean();
				System.out.print("barman is  cleaning \n");
				Thread.sleep(1000);
				b.endClean();
				System.out.print("barman stops to clean \n");
				Thread.sleep(400);
				k--;
			}
		} catch (Exception e) {
		}
	}

}
