package de.cofinpro.controller.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import de.cofinpro.controller.GlobalVariables;
import de.cofinpro.modul.Film;

public class DbConnect {

	Connection connection;

	public DbConnect() {

	}

	public boolean connectToMysql() {

		try {
			System.out.println("Datenbankverbindung wird aufgebaut...");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionCommand = "jdbc:mysql://" + GlobalVariables.HOST + "/" + GlobalVariables.DATABASE
					+ "?user=" + GlobalVariables.USER + "&password=" + GlobalVariables.PASSWORD;
			connection = DriverManager.getConnection(connectionCommand);
			System.out.println("Datenbank verbunden");
			return true;

		} catch (Exception ex) {
			System.out.println("Datenbankverbindung fehlgeschlagen");
			return false;
		}
	}
	
	public boolean saveFilm(Film film) {
		
		try {
			//stmt = connection.createStatement();
			
			//String query = "INSERT INTO" +table +" (id, name, genre)";
			//query = query + "VALUES( " +film.getId() +", " +film.getName() +", " +film.getGenre() +"" );
			
			
			String query = " INSERT INTO testdb (id, name, genre)" + "values (?, ?, ?)";
			
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, film.getId());
			preparedStmt.setString(2, film.getName());
			preparedStmt.setString(3, film.getGenre());
			
			preparedStmt.execute();
			System.out.println("Film gespeichert");
			connection.close();
		}
		
		catch (Exception e) {
			System.out.println("Eintragung fehlgeschlagen");
		}
		
		
		
		return false;
	}
	

}
