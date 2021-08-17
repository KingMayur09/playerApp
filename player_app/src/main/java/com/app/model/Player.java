package com.app.model;

public class Player {
	private int id;
	private String name;
	private int age;
	private String city;
	private String sportsname;
	private String gender;
	private long contact;
	private Team team;

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Player() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSportsname() {
		return sportsname;
	}

	public void setSportsname(String sportsname) {
		this.sportsname = sportsname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public Player(int id, String name, int age, String city, String sportsname, String gender, long contact) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.city = city;
		this.sportsname = sportsname;
		this.gender = gender;
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", age=" + age + ", city=" + city + ", sportsname=" + sportsname
				+ ", gender=" + gender + ", contact=" + contact + ", team=" + team + "]";
	}


	

}