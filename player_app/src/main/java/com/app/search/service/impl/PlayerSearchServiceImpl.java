package com.app.search.service.impl;

import java.util.List;

import com.app.dao.PlayerSearchDAO;
import com.app.dao.impl.PlayerSearchDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Player;
import com.app.search.service.PlayerSearchService;

public class PlayerSearchServiceImpl implements PlayerSearchService {
	private PlayerSearchDAO playerSearchDAO =new PlayerSearchDAOImpl();
	@Override
	public Player getPlayerById(int id) throws BusinessException {
		Player player=null;
		if(id<100 || id>1000) {
			throw new BusinessException("Invalid Player Id "+id);
		}else {
			player=playerSearchDAO.getPlayerById(id);
		}
		return player;
	}

	@Override
	public Player getPlayerByContact(long contact) throws BusinessException {
		Player player=null;
		String contactString=Long.toString(contact);
		if(contactString.matches("[1-9][0-9]{9}")) {
			player=playerSearchDAO.getPlayerByContact(contact);
		}else {
			throw new BusinessException("Invalid Player contact "+contact);
		}
		return player;
	}

	@Override
	public List<Player> getPlayersByName(String name) throws BusinessException {
		List<Player> playerList=null;
		if(name.matches("[a-zA-Z]{2,15}")) {
			playerList=playerSearchDAO.getPlayersByName(name);
		}else {
			
			throw new BusinessException("Invalid Player Name :"+name);
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayersByTeamName(String teamname) throws BusinessException {
		List<Player> playerList=null;
		if(teamname.matches("[a-zA-Z]{2,10}")) {
			//code here to DAO
			playerList=playerSearchDAO.getPlayersByTeamName(teamname);
		}else {
			throw new BusinessException("Invalid team name : "+teamname);
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayersByAge(int age) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> getPlayersBySportsName(String sportsname) throws BusinessException {
		List<Player> playerList=null;
		if(sportsname.matches("[a-zA-Z]{2,15}")) {
			playerList=playerSearchDAO.getPlayersBySportsName(sportsname);
		}else {
			
			throw new BusinessException("Invalid sport Name :"+sportsname);
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayerByCity(String city) throws BusinessException {
		List<Player> playerList=null;
		if(city.matches("[a-zA-Z]{2,15}")) {
			playerList=playerSearchDAO.getPlayerByCity(city);
		}else {
			
			throw new BusinessException("Invalid Player Name :"+city);
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayerByGender(String gender) throws BusinessException {
		List<Player> playerList=null;
		if(gender.matches("[m+M+f+F]")) {
			playerList=playerSearchDAO.getPlayerByGender(gender);
		}else {
			
			throw new BusinessException("Invalid Input");
		}
		return playerList;
	}

	@Override
	public int createPlayer(Player player) throws BusinessException {
		
	
		int c=playerSearchDAO.createPlayer(player);
		return c;
	}

	@Override
	public int updatePlayerContact(int id, long contact) throws BusinessException {
		int c=0;
		String contactString=Long.toString(contact);
		
		if(id<100 || id>1000) {
			throw new BusinessException("Invalid Player Id "+id);
		}else if(!contactString.matches("[1-9][0-9]{9}")) {
			throw new BusinessException("Invalid Player contact "+contact);
		}else {
			c=playerSearchDAO.updatePlayerContact(id, contact);
		}
		return c;
	}

	@Override
	public int deletePlayer(int id) throws BusinessException {
		int c=0;
		if(id<100 || id>1000) {
			throw new BusinessException("Invalid Player Id "+id);
		}else {
			c=playerSearchDAO.deletePlayer(id);
		}
		return c;
	}

	@Override
	public List<Player> getAllPlayers() throws BusinessException {
		List<Player> playerList=null;
		
		playerList=playerSearchDAO.getAllPlayers();
		return playerList;
	}

}
