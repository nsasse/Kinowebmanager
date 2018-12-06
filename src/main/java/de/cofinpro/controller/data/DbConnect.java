package de.cofinpro.controller.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
			
			String query = " INSERT INTO filme" +"(name, genre, regisseur, sprache, erscheinungsjahr, erscheinungsland, fsk, spieldauer, beliebtheit, kosten)"
			+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

			PreparedStatement preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setInt(0, film.getId());
			preparedStmt.setString(1, film.getName());
			preparedStmt.setString(2, film.getGenre());
			preparedStmt.setString(3, film.getRegisseur());
			preparedStmt.setString(4, film.getSprache());
			preparedStmt.setInt(5, film.getErscheinungsjahr());
			preparedStmt.setString(6, film.getErscheinungsland());
			preparedStmt.setInt(7, film.getFsk());
			preparedStmt.setInt(8, film.getSpieldauer());
			preparedStmt.setInt(9, film.getBeliebtheit());
			preparedStmt.setString(10, film.getKosten().toString());
			//DreiD wird nicht in der Datenbank gespeichert
			//Eff wird nicht in der Datenbank gespeichert

			preparedStmt.execute();
			System.out.println("Film, " +film.getName() +" gespeichert");
			//connection.close();
		}

		catch (Exception e) {
			System.out.println("Film, " +film.getName() +" fehlgeschlagen");
		}

		return false;
	}

	//Fehler beim auslesen
	public void getFilme() throws SQLException {
		/*
		String query = " SELECT * FROM filme";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		int columns = rs.getMetaData().getColumnCount();
		
		for (int i = 1; i <= columns; i++) {
			System.out.println(rs.getString(i));
		}
		*/
	}
}
