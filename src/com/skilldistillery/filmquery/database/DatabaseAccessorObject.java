package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Language;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL2 = "jdbc:mysql://localhost:3306/sdvid?verifyServerCertificate=false&useSSL=true";
	private static final String user = "student";
	private static final String pass = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			System.err.println("Unable to load DB Driver. Exiting.");
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public Film getFilmById(String filmId) {
		Film film = null;
		try {
			String sql = "SELECT * FROM film WHERE id = ?";
			Connection conn = DriverManager.getConnection(URL2, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			int fID = Integer.parseInt(filmId);
			stmt.setInt(1, fID);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				film = new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getDouble(9), rs.getString(10),
						rs.getString(11));
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return film;
	}

	@Override
	public Actor getActorById(String actorId) {
		Actor actor = null;
		try {
			String sql = "SELECT * FROM actor WHERE id = ?";
			Connection conn = DriverManager.getConnection(URL2, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			int aID = Integer.parseInt(actorId);
			stmt.setInt(1, aID);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				actor = new Actor(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> getActorsByFilmId(String filmId) {
		List<Actor> actorsByFilmId = null;

		try {
			String sql = "SELECT a.id, a.first_name, a.last_name FROM actor a JOIN film_actor fa "
					+ "ON a.id = fa.actor_id JOIN film f ON fa.film_id = f.id WHERE f.id = ?";
			Connection conn = DriverManager.getConnection(URL2, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			int fID = Integer.parseInt(filmId);
			stmt.setInt(1, fID);
			ResultSet rs = stmt.executeQuery();

			actorsByFilmId = new ArrayList<>();
			while (rs.next()) {
				Actor actor = new Actor(rs.getInt(1), rs.getString(2), rs.getString(3));
				actorsByFilmId.add(actor);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actorsByFilmId;
	}

	@Override
	public List<Film> getFilmBySearchKeyword(String keyword) {
		
		List<Film> films = null;
		String newKW;
		try {
			String sql = "SELECT * FROM film WHERE title LIKE ?";
			Connection conn = DriverManager.getConnection(URL2, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			newKW = "%" + keyword + "%";
			stmt.setString(1, newKW);
			ResultSet rs = stmt.executeQuery();

			films = new ArrayList<>();
			while (rs.next()) {
				Film film = new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getDouble(9), rs.getString(10),
						rs.getString(11));
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return films;
	}

	@Override
	public Language getLanguageById(String filmId) {
		Language l = null;
		try {
			String sql = "SELECT l.id, l.name FROM film f JOIN language l ON f.language_id = l.id WHERE f.id = ?";
			Connection conn = DriverManager.getConnection(URL2, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			int fID = Integer.parseInt(filmId);
			stmt.setInt(1, fID);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				l = new Language(rs.getInt(1), rs.getString(2));
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return l;
	}

}
