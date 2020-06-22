package queueOffice;

import java.util.concurrent.locks.*;

/*  
the monitor  that manage the queue   in the post office has 2  waiting queue one for man and one for woman.  the  man queue has  his condition to unlock the waiting  thread.   that is  different to woman queue because the woman has the priority. 
*/

public class postOffice {
	private int dim;
	private int lMan;
	private int lWoman;
	private int sWoman;
	private int sMan;
	private Lock lk;
	private Condition qWoman;
	private Condition qMan;

	public postOffice() {
		dim = 4;
		lMan = 0;
		lWoman = 0;
		sMan = 0;
		sWoman = 0;
		lk = new ReentrantLock();
		qMan = lk.newCondition();
		qWoman = lk.newCondition();
	}

	public void goIntoWoman() throws InterruptedException {
		lk.lock();
		try {
			while ((lMan > 0) || (lWoman == dim)) {
				sWoman++;
				qWoman.await();
				sWoman--;
			}
			lWoman++;
		} finally {
			lk.unlock();
		}
	}

	public void goIntoMan() throws InterruptedException {
		lk.lock();
		try {
			while (lWoman > 0 || lMan == dim || sWoman > 0) {
				sMan++;
				qMan.await();
				sMan--;
			}
			lMan++;
		} finally {
			lk.unlock();
		}
	}

	public void goOutWoman() {
		lk.lock();
		lWoman--;
		if (sWoman > 0) {
			qWoman.signal();
		} else if (sMan > 0 && lWoman == 0) {
			qMan.signalAll();
		}
		lk.unlock();
	}

	public void goOutMan() {
		lk.lock();
		lMan--;
		if (sWoman > 0 && lMan == 0) {
			qWoman.signalAll();
		} else if (sMan > 0 && sWoman == 0) {
			qMan.signal();
		}
		lk.unlock();
	}

}
