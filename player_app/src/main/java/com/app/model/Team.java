package com.app.model;

public class Team {
	private int id;
	private String teamname;
	public Team() {
		// TODO Auto-generated constructor stub
	}
	public Team(String teamname) {
		super();
		this.teamname = teamname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTeamname() {
		return teamname;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	@Override
	public String toString() {
		return "Team [id=" + id + ", teamname=" + teamname + "]";
	}
	
	
	
}
