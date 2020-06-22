package barSport;

import java.util.concurrent.locks.*;

public class bar {
	private int max; // the bar capacity
	private int[] customers; // array to organize the clients.in 0 put locals and in 1 put guests
	private boolean close;
	private Lock lk;
	private Condition qLocals;
	private Condition qGuest;
	private Condition qBarman;
	private int[] sospend; // array to memorize the clients that they are waiting the drinks

	/*
	 * bar is the share memory, i manage it with a monitor as follow . there are 3
	 * conditions. - locals: if the bar is open and is free or there are locals
	 * inside he takes the lock. - guests: if the bar is open he takes the lock. -
	 * barman: when the timer finish, he takes priority, wait that the people finish
	 * drink and takes lock
	 */

	public bar() {
		max = 20;
		customers = new int[2];
		close = false;
		lk = new ReentrantLock();
		qLocals = lk.newCondition();
		qGuest = lk.newCondition();
		qBarman = lk.newCondition();
		sospend = new int[2];
	}

	public void drinkLocals() throws InterruptedException {
		lk.lock();
		try {
			while (customers[1] != 0 || customers[0] == max || sospend[1] > 0 || close) { 
				sospend[0]++;
				qLocals.await();
				sospend[0]--;
			}
			customers[0]++;
		} finally {
			lk.unlock();
		}
	}

	public void drinkGuest() throws InterruptedException {
		lk.lock();
		try {
			while (customers[0] != 0 || customers[1] == max || close) {
				sospend[1]++;
				qGuest.await();
				sospend[1]++;
			}
			customers[1]++;
		} finally {
			lk.unlock();
		}
	}

	public void finishGuest() throws InterruptedException {
		lk.lock();
		try {
			customers[1]--;
			if (close && customers[1] == 0) {
				qBarman.signal();
			} else if (sospend[1] > 0) {
				qGuest.signal();
			} else if (customers[1] == 0 && sospend[0] > 0) {
				qLocals.signalAll();
			}
		} finally {
			lk.unlock();
		}
	}

	public void finishLocals() throws InterruptedException {
		lk.lock();
		try {
			customers[0]--;
			if (close && customers[0] == 0) {
				qBarman.signal();
			} else if (sospend[1] > 0 && customers[0] == 0) {
				qGuest.signalAll();
			} else if (sospend[1] == 0 && sospend[0] > 0) {
				qLocals.signal();
			}
		} finally {
			lk.unlock();
		}
	}

	public void startClean() throws InterruptedException {
		lk.lock();
		try {
			close = true;
			while (customers[0] > 0 || customers[1] > 0) {
				qBarman.await();
			}
		} finally {
			lk.unlock();
		}
	}

	public void endClean() throws InterruptedException {
		lk.lock();
		try {
			close = false;
			if (sospend[0] > 0) {
				qLocals.signalAll();
			}
			if (sospend[1] > 0) {
				qGuest.signalAll();
			} 
			
		} finally {
			lk.unlock();
		}
	}

}