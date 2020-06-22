package queueOffice;

public class queueOffice {

	/*
	  monitor exercise: manage a queue in a post office using concurrent thread.
	   in the post office there are 4 offices. the women threads have  the priority in the
	  queue. 
	  */
		
	public static void main(String[] arg) {
		System.out.print("open office \n");

		int nt = 10;
		postOffice p = new postOffice();
		man[] u = new man[nt];
		woman[] d = new woman[nt];
		for (int i = 0; i < nt; i++) {
			String s = String.valueOf(i);
			u[i] = new man(s, p);
			d[i] = new woman(s, p);
		}
		for (int i = 0; i < nt; i++) {
			u[i].start();
			d[i].start();
		}
		// System.out.print(" close office");;
	}

}
