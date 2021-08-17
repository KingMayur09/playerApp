package com.app.search.service;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Player;

public interface PlayerSearchService {
	
	public int createPlayer(Player player) throws BusinessException;
	public int updatePlayerContact(int id,long contact) throws BusinessException;
	public int deletePlayer(int id) throws BusinessException;
	public List<Player> getAllPlayers() throws BusinessException;
	public Player getPlayerById(int id) throws BusinessException;
	public Player getPlayerByContact(long contact) throws BusinessException;
	public List<Player> getPlayersByName(String name) throws BusinessException;
	public List<Player> getPlayersByTeamName(String teamname) throws BusinessException;
	public List<Player> getPlayersByAge(int age) throws BusinessException;
	public List<Player> getPlayersBySportsName(String sportsname) throws BusinessException;
	public List<Player> getPlayerByCity(String city) throws BusinessException;
	public List<Player> getPlayerByGender(String gender) throws BusinessException;
}
