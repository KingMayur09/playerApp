package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;

import com.app.dao.PlayerSearchDAO;
import com.app.dao.TeamSearchDAO;
import com.app.dao.impl.PlayerSearchDAOImpl;
import com.app.dao.impl.TeamSearchDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Player;
import com.app.model.Team;
import com.app.search.service.PlayerSearchService;
import com.app.search.service.impl.PlayerSearchServiceImpl;
import com.mysql.cj.log.Log;

public class Main {
	private static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		log.info("Welcome Player App v1.0");
		log.info("================================");
		int ch = 0;
		do {

			log.info("\nWhat you wish to do today?");
			log.info("1)create A Player");
			log.info("2)Update A Player");
			log.info("3)Delete A Player");
			log.info("4)List All Players");
			log.info("5)Search Player with various filters");
			log.info("6)Exit");
			log.info("Please enter the choice(1-6)");

			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}

			switch (ch) {
			case 1:
				PlayerSearchDAO playerSearchDAO=new PlayerSearchDAOImpl();
				Player player1 = new Player();
				log.info("\nEnter the Player Details");
				try {

					log.info("Enter the Id for the player");
					player1.setId(Integer.parseInt(scanner.nextLine()));

				} catch (NumberFormatException e) {
					log.warn("Player Id should should be in digit only");
				}
				
				log.info("Enter the player Name");
				player1.setName(scanner.nextLine());
				try {

					log.info("Enter the Age of the player");
					player1.setAge(Integer.parseInt(scanner.nextLine()));

				} catch (NumberFormatException e) {
					log.warn("Player Age should should be in digit only");
				}
				log.info("Enter the city");
				player1.setCity(scanner.nextLine());
				log.info("Enter the gender");
				player1.setGender(scanner.nextLine());
				log.info("Enter the sports name");
				player1.setSportsname(scanner.nextLine());
				log.info("Enter the contact number");
				player1.setContact(Long.parseLong(scanner.nextLine()));
				
				TeamSearchDAO teamSearchDAO= new TeamSearchDAOImpl();
				try {
					Team team =new Team();
					List<Team> teamList=teamSearchDAO.getAllTeams();
					log.info("choose id of player team from below or type new for other team");
					for(Team t1:teamList) {
						log.info(t1);
					}
					log.info("Enter 0 for other team name");
		
					int setTeamId=Integer.parseInt(scanner.nextLine());
					if(setTeamId!=0) {
						team.setId(setTeamId);
						player1.setTeam(team);
						
					}else {
						log.info("Enter the country name");
						String newTeam=scanner.nextLine();
						team.setTeamname(newTeam);
						player1.setTeam(teamSearchDAO.createNewTeam(team));
					}
					
				} catch (BusinessException e1) {
				
					log.warn(e1.getMessage());
				}
				try {
					int c=playerSearchDAO.createPlayer(player1);
					
						if(c>0) {
							log.info("Player Added successfully");
						}
						
				} catch (BusinessException e1) {
					log.warn(e1.getMessage());
				}
			
				break;
			case 2:
				log.info("Enter Id and Contact Number");
				
				try {
					PlayerSearchService playerSearchService=new PlayerSearchServiceImpl();
					int id=Integer.parseInt(scanner.nextLine());
					long contact=Long.parseLong(scanner.nextLine());
					int c=playerSearchService.updatePlayerContact(id, contact);
					if(c==1) {
						log.info("player updatd successfully");
					}
					
				}catch(BusinessException e) {
					log.warn(e.getMessage());
					
				}
				

				break;
			case 3:
				log.info("Enter the id for delete player");
				
				try {
					PlayerSearchService playerSearchService=new PlayerSearchServiceImpl();
					int id=Integer.parseInt(scanner.nextLine());
					int c=playerSearchService.deletePlayer(id);
					if(c==1) {
						log.info("player details deleted successfully");
					}
					else {
						log.info("Player id doesnt exist");
					}
				}catch(BusinessException e) {
					log.warn(e.getMessage());
				}catch(NumberFormatException e) {
					log.warn("id should be digit only");
				}

				break;
			case 4:
				log.info("All player details");
				try {
					PlayerSearchService playerSearchService=new PlayerSearchServiceImpl();
					List<Player> playerList=playerSearchService.getAllPlayers();
					for(Player player:playerList) {
						log.info(player);
					}
				}catch(BusinessException e){
					log.warn(e.getMessage());
				}

				break;
			case 5:
				int optional = 0;
				PlayerSearchService playerSearchService = new PlayerSearchServiceImpl();
				do {
					log.info("\n\n Welocme to Player Search Menu..... Search Your Player On Different Filters");
					log.info("1.By Player ID");
					log.info("2.By Player Name");
					log.info("3.By Player Age");
					log.info("4.By Player Gender");
					log.info("5.By Player City");
					log.info("6.By Player TeamName");
					log.info("7.By Player Sportsname");
					log.info("8.By Player contact");
					log.info("9.Go Back To Main Menu");
					log.info("Please Enter Your Choice(1-9)");
					try {
						optional = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
						System.out.println(e.getMessage());
					}

					switch (optional) {

					case 1:
						log.info("Enter the Id to get the Player details..");
						try {
							int id = Integer.parseInt(scanner.nextLine());

							Player player = playerSearchService.getPlayerById(id);
							if (player != null) {
								log.info("Player with id :" + id + " found successfully");
								log.info(player);
							}

						} catch (NumberFormatException e) {
							log.warn("Player Id should should be in digit only");
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}

						break;
					case 2:
						log.info("Enter the player Name to get Player details..");
						try {
							String name = scanner.nextLine();

							List<Player> playerList = playerSearchService.getPlayersByName(name);
							if (playerList != null && playerList.size() > 0) {
								log.info("Total there are " + playerList.size() + " number of players of the name "
										+ name.toUpperCase());
								for (Player player : playerList) {
									log.info(player);
								}
							}

						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}

						break;
					case 3:

						break;
					case 4:
						log.info("Get Player By gender Type M or F");
						try {
							String gender = scanner.nextLine();

							List<Player> playerList = playerSearchService.getPlayerByGender(gender);
							if (playerList != null && playerList.size() > 0) {
								log.info("Total there are " + playerList.size() + " number of players of the gender : "
										+ gender.toUpperCase());
								for (Player player : playerList) {
									log.info(player);
								}
							}

						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}

						break;
					case 5:
						log.info("Enter the city to find a player of that city");
						try {
							String city = scanner.nextLine();

							List<Player> playerList = playerSearchService.getPlayerByCity(city);
							if (playerList != null && playerList.size() > 0) {
								log.info("Total there are " + playerList.size() + " number of playeres of the  : "
										+ city.toUpperCase());
								for (Player player : playerList) {
									log.info(player);
								}
							}

						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}

						break;
					case 6:
						log.info("Enter the city to find a player of that teamname");
						String teamname = scanner.nextLine();
						try {
							List<Player> playerList = playerSearchService.getPlayersByTeamName(teamname);
							if (playerList != null && playerList.size() > 0) {
								log.info("Total there are " + playerList.size()
										+ " number of players iplaying for the team " + teamname.toUpperCase()
										+ " printing the players");
								for (Player player : playerList) {
									log.info(player);
								}
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}

						break;
					case 7:
						log.info("Enter the sportsname to get all player");

						String sportsname = scanner.nextLine();
						try {
							List<Player> playerList = playerSearchService.getPlayersBySportsName(sportsname);
							if (playerList != null && playerList.size() > 0) {
								log.info("Total there are " + playerList.size()
										+ " number of players iplaying for the team " + sportsname.toUpperCase()
										+ " printing the players");
								for (Player player : playerList) {
									log.info(player);
								}
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}

						break;
					case 8:
						log.info("Enter the player number to get Player details..");
						try {
							long contact = Long.parseLong(scanner.nextLine());

							Player player = playerSearchService.getPlayerByContact(contact);
							if (player != null) {
								log.info("Player with Contact number " + contact + " found ");
								log.info(player);
							}

						} catch (NumberFormatException e) {
							log.warn("Player no should be 10 digit number");
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}

						break;
					case 9:

						break;

					default:
						log.warn("Invalid choice.......Choice should be only number and betweeb 1-9 only");
						break;
					}

				} while (optional != 9);

				break;
			case 6:
				log.info("Thanks for using the APP we will see you soon:)");

				break;

			default:
				log.warn("Invalid choice.......Choice should be only number and betweeb 1-6 only");
				break;
			}
		} while (ch != 6);

	}

}
