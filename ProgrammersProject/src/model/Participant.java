package model;

public class Participant extends Programmer {

	private Participant next;

	
	public Participant(String id, String first_name, String last_name, String email, String gender, String avatar, int index) {
		super(id, first_name, last_name, email, gender,avatar,index);
	}


	public Participant getNext() {
		return next;
	}


	public void setNext(Participant next) {
		this.next = next;
	}

	

	
	
	

}
