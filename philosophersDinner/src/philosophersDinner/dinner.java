package philosophersDinner;

import java.util.concurrent.locks.*;

public class dinner {
	private int[] forks;
	private Lock lk;
	private Condition[] qPhilosophers;

	/*
	 * the condition to haven't deadlock is this. a Philosopher control if the forks
	 * in his left and in his right are free he locks the resources and eat, else he
	 * waits.
	 */

	public dinner() {
		lk = new ReentrantLock();
		forks = new int[5];
		qPhilosophers = new Condition[5];
		for (int i = 0; i < 5; i++) {
			qPhilosophers[i] = lk.newCondition();
			forks[i] = 2;
		}
	}

	public void takeForks(int y) throws InterruptedException {
		lk.lock();
		try {
			while (forks[y] != 2) {
				qPhilosophers[y].await();
			}
			forks[left(y)]--;
			forks[right(y)]--;
		} finally {
			lk.unlock();
		}
	}

	public void leaveForks(int y) throws InterruptedException {
		lk.lock();
		try {
			forks[left(y)]++;
			forks[right(y)]++;
			if (forks[left(y)] == 2) {
				qPhilosophers[left(y)].signal();
			}
			if (forks[right(y)] == 2) {
				qPhilosophers[right(y)].signal();
			}
		} finally {
			lk.unlock();
		}
	}

	public int right(int y) {
		int z = 0;
		if (y == 4) {
			z = 0;
		} else {
			z = y + 1;
		}
		return z;
	}

	public int left(int y) {
		int z = 0;
		if (y == 0) {
			z = 4;
		} else {
			z = y - 1;
		}
		return z;
	}

}
