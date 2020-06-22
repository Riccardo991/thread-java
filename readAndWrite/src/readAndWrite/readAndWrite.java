package readAndWrite;

import java.util.concurrent.Semaphore;

public class readAndWrite {

	/*
	  semaphore exercise: implement the problem of n writers and n readers using
	  semaphores. the readers thread can read the memory together. a writer thread
	  must write alone on the memory .
	 */

	public static void main(String[] arg) {
		Thread tc = Thread.currentThread();

		Book b = new Book("hello ");
		Writer[] W = new Writer[3];
		Reader[] R = new Reader[5];

		for (int i = 0; i < 3; i++) {
			String sw = String.valueOf(i);
			char c = (char) (char) (97 + i);
			String st = "" + c + c + c + c;
			W[i] = new Writer(sw, st, b);
		}
		for (int j = 0; j < 5; j++) {
			String sr = String.valueOf(j);
			R[j] = new Reader(sr, b);
		}

		try {
			tc.sleep(15000);
		} catch (Exception e) {
		}

		for (int i = 0; i < 3; i++) {
			W[i].endWrite();
		}
		for (int i = 0; i < 5; i++) {
			R[i].endRead();
		}

	}

}
