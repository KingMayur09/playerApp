package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.Main;
import com.app.dao.PlayerSearchDAO;
import com.app.dao.dbutil.MysqlDbConnection;
import com.app.exception.BusinessException;
import com.app.model.Player;
import com.app.model.Team;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

public class PlayerSearchDAOImpl implements PlayerSearchDAO {
	private static Logger log = Logger.getLogger(PlayerSearchDAOImpl.class);

	@Override
	public Player getPlayerById(int id) throws BusinessException {
		Player player = null;
		try (Connection connection = MysqlDbConnection.getConnection()) {
			String sql = "select p.id,p.name,age,city,gender,sportsname,contact ,teamname from player p join team t on p.teamid=t.id where p.id=?";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				player = new Player();
				player.setId(id);
				player.setName(resultSet.getString("name"));
				player.setAge(resultSet.getInt("age"));
				player.setGender(resultSet.getString("gender"));
				player.setCity(resultSet.getString("city"));
				player.setSportsname(resultSet.getString("sportsname"));
				player.setContact(resultSet.getLong("contact"));
				Team team = new Team();
				team.setId(resultSet.getInt("id"));
				team.setTeamname(resultSet.getString("teamname"));
				player.setTeam(team);
			} else {

				throw new BusinessException("Entered Player Id " + id + " doesn't exist");
			}

		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured here");
		}
		return player;
	}

	@Override
	public Player getPlayerByContact(long contact) throws BusinessException {
		Player player = null;
		try (Connection connection = MysqlDbConnection.getConnection()) {
			String sql = "select p.id,p.name,age,city,gender,sportsname,contact ,teamname from player p join team t on p.teamid=t.id where contact=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, contact);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				player = new Player();

				player.setId(resultSet.getInt("id"));
				player.setName(resultSet.getString("name"));
				player.setAge(resultSet.getInt("age"));
				player.setGender(resultSet.getString("gender"));
				player.setCity(resultSet.getString("city"));
				player.setSportsname(resultSet.getString("sportsname"));
				player.setContact(contact);
				Team team = new Team();
				team.setId(resultSet.getInt("id"));
				team.setTeamname(resultSet.getString("teamname"));
				player.setTeam(team);
			} else {
				throw new BusinessException("Entered contact doesn't match with any player contact");
			}

		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
		}
		return player;
	}

	@Override
	public List<Player> getPlayersByName(String name) throws BusinessException {
		List<Player> playerList = new ArrayList<>();
		try (Connection connection = MysqlDbConnection.getConnection()) {
			String sql = "select p.id,p.name,age,city,gender,sportsname,contact ,teamname from player p join team t on p.teamid=t.id where name=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Player player = new Player();
				player.setId(resultSet.getInt("id"));
				player.setName(name);
				player.setAge(resultSet.getInt("age"));
				player.setCity(resultSet.getString("city"));
				player.setGender(resultSet.getString("gender"));
				player.setContact(resultSet.getLong("contact"));
				player.setSportsname(resultSet.getString("sportsname"));
				Team team = new Team();
				team.setId(resultSet.getInt("id"));
				team.setTeamname(resultSet.getString("teamname"));
				player.setTeam(team);
				playerList.add(player);
			}
			if (playerList.size() == 0) {
				throw new BusinessException("No player of Name:" + name);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured");
		} catch (NullPointerException e) {
			log.error(e);
			throw new BusinessException("NullPointerException  here ---");
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayersByTeamName(String teamname) throws BusinessException {
		List<Player> playerList = new ArrayList<>();
		try (Connection connection = MysqlDbConnection.getConnection()) {
			String sql = "select p.id,name,age,city,gender,sportsname,contact,teamname,teamid from player p JOIN team t on p.teamid=t.id where t.teamname=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, teamname);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Player player = new Player();
				player.setId(resultSet.getInt("id"));
				player.setName(resultSet.getString("name"));
				player.setAge(resultSet.getInt("age"));
				player.setCity(resultSet.getString("city"));
				player.setGender(resultSet.getString("gender"));
				player.setSportsname(resultSet.getString("sportsname"));
				player.setContact(resultSet.getLong("contact"));
				Team team = new Team();
				team.setId(resultSet.getInt("teamid"));
				team.setTeamname(resultSet.getString("teamname"));
				player.setTeam(team);
				playerList.add(player);
			}

			if (playerList.size() == 0) {
				throw new BusinessException("No Players for team " + teamname);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
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
		List<Player> playerList = new ArrayList<>();
		try (Connection connection = MysqlDbConnection.getConnection()) {
			String sql = "select p.id,name,age,city,gender,sportsname,contact,teamname,teamid from player p JOIN team t on p.teamid=t.id where p.sportsname=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,sportsname );
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Player player = new Player();
				player.setId(resultSet.getInt("id"));
				player.setName(resultSet.getString("name"));
				player.setAge(resultSet.getInt("age"));
				player.setCity(resultSet.getNString("city"));
				player.setGender(resultSet.getString("gender"));
				player.setSportsname(sportsname);
				player.setContact(resultSet.getLong("contact"));
				Team team = new Team();
				team.setId(resultSet.getInt("teamid"));
				team.setTeamname(resultSet.getString("teamname"));
				player.setTeam(team);
				playerList.add(player);
			}

		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayerByCity(String city) throws BusinessException {
		List<Player> playerList = new ArrayList<>();
		try (Connection connection = MysqlDbConnection.getConnection()) {
			String sql = "select p.id,name,age,city,gender,sportsname,contact,teamname,teamid from player p JOIN team t on p.teamid=t.id where p.city=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, city);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Player player = new Player();
				player.setId(resultSet.getInt("id"));
				player.setName(resultSet.getString("name"));
				player.setAge(resultSet.getInt("age"));
				player.setCity(city);
				player.setGender(resultSet.getString("gender"));
				player.setSportsname(resultSet.getString("sportsname"));
				player.setContact(resultSet.getLong("contact"));
				Team team = new Team();
				team.setId(resultSet.getInt("teamid"));
				team.setTeamname(resultSet.getString("teamname"));
				player.setTeam(team);
				playerList.add(player);
			}
			if (playerList.size() == 0) {
				log.info("No player from the city :" + city);
			}

		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return playerList;
	}

	@Override
	public List<Player> getPlayerByGender(String gender) throws BusinessException {
		List<Player> playerList = new ArrayList<>();
		try (Connection connection = MysqlDbConnection.getConnection()) {
			String sql = "select p.id,name,age,city,gender,sportsname,contact,teamname,teamid from player p JOIN team t on p.teamid=t.id where p.gender=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, gender);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Player player = new Player();
				player.setId(resultSet.getInt("id"));
				player.setName(resultSet.getString("name"));
				player.setAge(resultSet.getInt("age"));
				player.setCity(resultSet.getString("city"));
				player.setGender(gender);
				player.setSportsname(resultSet.getString("sportsname"));
				player.setContact(resultSet.getLong("contact"));
				Team team = new Team();
				team.setId(resultSet.getInt("teamid"));
				team.setTeamname(resultSet.getString("teamname"));
				player.setTeam(team);
				playerList.add(player);
			}

		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return playerList;
	}

	@Override
	public int createPlayer(Player player) throws BusinessException {
		int c=0;
		
		try(Connection connection=MysqlDbConnection.getConnection()){
			
			String sql="insert into player(id,name,age,gender,city,sportsname,contact,teamid) values(?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, player.getId());
			preparedStatement.setString(2, player.getName());
			preparedStatement.setInt(3, player.getAge());
			preparedStatement.setString(4, player.getGender());
			preparedStatement.setString(5, player.getCity());
			preparedStatement.setString(6, player.getSportsname());
			preparedStatement.setLong(7, player.getContact());
			preparedStatement.setInt(8, player.getTeam().getId());
			
			c=preparedStatement.executeUpdate();
			
			
		}catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error occured,Please contact support");
		}
		
		return c;
	}
	@Override
	public int updatePlayerContact(int id, long contact) throws BusinessException {
		int c=0;
		try(Connection connection=MysqlDbConnection.getConnection()){
			String sql=" update player set contact=? where id=?";
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setLong(1, contact);
			preparedStatement.setInt(2, id);
			c=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured,Please contact support");
		}
		return c;
	}

	@Override
	public int deletePlayer(int id) throws BusinessException {
		int c=0;
		
		try(Connection connection =MysqlDbConnection.getConnection()){
			
			String sql="delete from player where id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			c=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured,Please contact support");
		}
		return c;
	}

	@Override
	public List<Player> getAllPlayers() throws BusinessException {
		List<Player> playerList=new ArrayList<>();
		try(Connection connection=MysqlDbConnection.getConnection()){
			String sql="select p.id,p.name,age,gender,city,sportsname,contact,teamid,teamname from player p join team t on p.teamid=t.id";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultset=preparedStatement.executeQuery();
			while(resultset.next()) {
				Player player=new Player();
				player.setId(resultset.getInt("id"));
				player.setName(resultset.getString("name"));
				player.setAge(resultset.getInt("age"));
				player.setGender(resultset.getString("gender"));
				player.setCity(resultset.getString("city"));
				player.setSportsname(resultset.getString("sportsname"));
				player.setContact(resultset.getLong("contact"));
				Team team=new Team();
				team.setId(resultset.getInt("teamid"));
				team.setTeamname(resultset.getString("teamname"));
				player.setTeam(team);
				playerList.add(player);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured");
		}
		return playerList;
	}

}
