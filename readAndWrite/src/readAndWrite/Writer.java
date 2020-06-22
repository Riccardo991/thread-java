package readAndWrite;

public class Writer implements Runnable {
	private String name;
	private Book lb;
	private Thread write;
	private char[] theme;
	private int dim;
	private int k;

	// writer thread
	public Writer(String n, String t, Book b) {
		name = n;
		dim = t.length();
		theme = new char[dim];
		theme = t.toCharArray();
		k = 0;
		this.lb = b;
		write = new Thread(this);
		write.start();
	}

	public void run() {
		try {
			while (k < dim) {				
				char c = theme[k];
				System.out.print("the  Writer" + name + " has wwrote : " + c + "\n");
				lb.writeOn(c);
				Thread.sleep(100);
				k++;
			}
		} catch (Exception e) {
		}
	}

	public void endWrite() {
		write.stop();
	}
	
}
