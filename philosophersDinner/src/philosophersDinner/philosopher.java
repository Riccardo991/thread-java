package philosophersDinner;

public class philosopher extends Thread {
	private int id;
	private dinner t;

	// create a thread for philosopher
	public philosopher(dinner x, int y) {
		this.t = x;
		id = y;
	}

	public void run() {
		try {
			while (true) {
				System.out.print("philosopher " + id + " is thinking  \n");
				Thread.sleep(200);
				t.takeForks(id);
				System.out.print(" philosopher " + id + " begins to  eat \n");
				Thread.sleep(400);
				t.leaveForks(id);
				System.out.print(" philosopher " + id + " finishs  eat \n");
			}
		} catch (InterruptedException e) {
		}
	}

}
