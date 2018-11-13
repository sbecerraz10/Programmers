package model;

public class Programmer implements Comparable<Programmer>{
		
	protected String id;
	protected String first_name;
	protected String last_name;
	protected String email;
	protected String  gender;
	protected String avatar;
	
	private Programmer left;
	private Programmer right;
	
	
	public Programmer(String id, String first_name, String last_name, String email, String gender, String avatar) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.gender = gender;
		this.avatar = avatar;
	}


	public Programmer getLeft() {
		return left;
	}


	public void setLeft(Programmer left) {
		this.left = left;
	}


	public Programmer getRight() {
		return right;
	}


	public void setRight(Programmer right) {
		this.right = right;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	@Override
	public int compareTo(Programmer programmer2) {
		int compare = 0;
		
		if(this.id.compareToIgnoreCase(programmer2.getId()) < 0) {
			compare = -1;
		}else {
			compare = 1;
		}
		
		return compare;
	}
	
	
	
	
	
	
}
