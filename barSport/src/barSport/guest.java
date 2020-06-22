package barSport;

public class guest extends Thread {
	private String name;
	private bar b;

	// create a thread for guest
	public guest(String s, bar x) {
		name = s;
		this.b = x;
	}

	public void run() {
		try {
			System.out.print(" the guest  " + name + "  is in a queue outside barSport \n");
			b.drinkGuest();
			System.out.print("guest " + name + "is  waiting  the drink  \n");
			Thread.sleep(200);
			System.out.print("the guest " + name + " has got the drink \n");
			b.finishGuest();
			System.out.print("the guest " + name + " has finished to drink \n");
		} catch (Exception e) {
		}
	}

}
