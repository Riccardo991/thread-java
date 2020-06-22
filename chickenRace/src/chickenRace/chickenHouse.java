package chickenRace;

import java.util.concurrent.*;

public class chickenHouse {
	private ArrayBlockingQueue<Integer> q;
	private int dim;

	public chickenHouse(int n) {
		q = new ArrayBlockingQueue<Integer>(n);
		dim = n;
	}

	public synchronized boolean isEmpty() {
		return q.isEmpty();
	}

	public synchronized void putIn() {
		try {
			for (int i = 0; i < dim; i++) {
				q.put(1);
			}
		} catch (InterruptedException e) {
		}
	}

	public synchronized int takeOne() {
		int x = 0;
		try {
			x = q.take();
		} catch (InterruptedException e) {
		}
		return x;
	}

	public synchronized void printQ() {
		System.out.print("in manger: " + q + "\n");
		;
	}

	public int getDim() {
		return dim;
	}

}
