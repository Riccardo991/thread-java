package readAndWrite;

import java.util.concurrent.Semaphore;

public class Book {
	private String theme;
	private int numReaders;
	private Semaphore blockRead; // è un mutex per il controllo della variabile numLettori;
	private Semaphore blockWrite;

	 /* 
	we use 2 semaphore to manage the share memory. 
	- blockWriter is a semaphore to  implements   the mutual exclusion between writers, because  the memory can be use by only one writer at a time. 
- blockRead is a mutex that lock the memory  when first reader  takes mutual exclusion  so all reader  can read the memory. 
when first reader loses mutual  exclusion  all readers   leave  memory .
	*/
	
	public Book(String n) {
		theme = n;
		numReaders = 0;
		blockRead = new Semaphore(1);
		blockWrite = new Semaphore(1);
	}

	public void writeOn(char c) {
		try {
			blockWrite.acquire();
			String sc = "" + c;
			theme = theme + c;
			System.out.print("the theme is now:" + theme + "\n");
			blockWrite.release();
		} catch (Exception e) {
		}
	}

	public String readOn() {
		String s = new String();
		try {
			blockRead.acquire();
			numReaders++;
			if (numReaders > 0) {
				blockWrite.acquire();
			}
			blockRead.release();
			s = theme;

			blockRead.acquire();
			numReaders--;
			if (numReaders == 0) {
				blockWrite.release();
			}
			blockRead.release();
		} catch (Exception e) {
		}
		return s;
	}

}
