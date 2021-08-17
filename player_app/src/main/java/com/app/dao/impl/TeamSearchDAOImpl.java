package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.dao.TeamSearchDAO;
import com.app.dao.dbutil.MysqlDbConnection;
import com.app.exception.BusinessException;
import com.app.model.Team;

public class TeamSearchDAOImpl implements TeamSearchDAO{
	private static Logger log = Logger.getLogger(PlayerSearchDAOImpl.class);

	@Override
	public List<Team> getAllTeams() throws BusinessException {
		List<Team> teamList=new ArrayList<>();
		try(Connection connection=MysqlDbConnection.getConnection()){
			String sql="select id,teamname from team";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {

				Team team = new Team();
				team.setId(resultSet.getInt("id"));
				team.setTeamname(resultSet.getString("teamname"));
				teamList.add(team);
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException("Internal error occured");
		}
		
		return teamList;
	}

	@Override
	public Team createNewTeam(Team team) throws BusinessException {
		try (Connection connection = MysqlDbConnection.getConnection()) {
			String sql = "insert into team(teamname) values(?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, team.getTeamname());

			int c = preparedStatement.executeUpdate();
			if (c == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					team.setId(resultSet.getInt(1));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal Error occured contact sysadmin");
		}
		return team;
		
	}


}
