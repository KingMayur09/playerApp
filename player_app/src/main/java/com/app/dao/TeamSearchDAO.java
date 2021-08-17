package com.app.dao;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Team;

public interface TeamSearchDAO {
	
	public List<Team> getAllTeams() throws BusinessException;
	public Team createNewTeam(Team team) throws BusinessException;

}
