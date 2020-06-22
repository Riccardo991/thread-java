package barSport;

public class locals extends Thread {
	private String name;
	private bar b;

	// create a thread  for local  
	public locals(String s, bar x) {
		name = s;
		this.b = x;
	}

	public void run() {
		try {
			System.out.print("the local " + name + " is in the queue  outside barSport \n");
			b.drinkLocals();
			System.out.print("local " + name + "is inside and he is  waiting   the drink \n");
			Thread.sleep(200);
			System.out.print(" the local " + name + " has got the drink \n");
			b.finishLocals();
			System.out.print("the local "+name+" has finished  to drink. \n"); 
		} catch (Exception e) {
		}
	}

}
