package readAndWrite;

public class Reader implements Runnable {
	private String name;
	private Book lb;
	private Thread read;
	private String lain;

	// reader thread
	public Reader(String n, Book l) {
		name = n;
		this.lb = l;
		lain = "";
		read = new Thread(this);
		read.start();
	}

	public void run() {
		try {
			while (true) {
				String s = lb.readOn();
				System.out.print("the Reader " + name + " read : " + s + "\n");
				if (s.equals(lain)) {
					read.stop();
				} else {
					lain = s;
				}
			}
		} catch (Exception e) {
		}
	}

	public void endRead() {
		read.stop();
	}

}
