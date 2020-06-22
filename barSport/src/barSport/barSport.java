package barSport;

import java.util.concurrent.locks.*;

public class barSport {

	/* 
	monitor exercise: in a stadium there is a bar. the barman manages the queue of people in  follow way. 
	- there are  the locals and the  guests people.  
	- the bar has  a max capacity. 
	- the guests people have the priority .  
	- in the bar can't stay     locals and guests   together.  so for example, if inside the bar  there is a guest,  the locals waiting outside.  
	    - the barman   can decide to clean the  bar  when  he wants.  if he decides to clean , he waits  that the people inside   finish  to drink and  after  closes  the bar and cleans.
	implements the concurrent threads  for locals, guests and barmand and    create the monitor to synchronize the threads.     
	*/
	
	public static void main(String[] arg) {
		int funs = 5;
		bar b = new bar();
		locals[] l = new locals[funs];
		guest[] g = new guest[funs];
		barman bm = new barman(b);
		for (int i = 0; i < funs; i++) {
			String s = String.valueOf(i);
			l[i] = new locals(s, b);
			g[i] = new guest(s, b);
		}
		for (int i = 0; i < funs; i++) {
			l[i].start();
			g[i].start();
		}
		bm.start();
	}

}
