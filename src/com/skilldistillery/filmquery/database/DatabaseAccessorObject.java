package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.InventoryItem;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String USER = "student";
	private static final String PWD = "student";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			String sql = "SELECT * FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			while (filmResult.next()) {
				String title = filmResult.getString("title");
				String desc = filmResult.getString("description");
				short releaseYear = filmResult.getShort("release_year");
				int langId = filmResult.getInt("language_id");
				String language = convertLanguage(langId);
				int rentDur = filmResult.getInt("rental_duration");
				double rate = filmResult.getDouble("rental_rate");
				int length = filmResult.getInt("length");
				double repCost = filmResult.getDouble("replacement_cost");
				String rating = filmResult.getString("rating");
				String features = filmResult.getString("special_features");
				String category = findCategory(filmId);
				film = new Film(filmId, title, desc, releaseYear, langId, language, rentDur, rate, length, repCost, rating, features, category);
				film.setCast(findActorsByFilmId(filmId));
				film.setInventoryItems(findInventoryByFilmId(filmId));
			}
		
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			String sql = "SELECT * FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				String firstName = actorResult.getString("first_name");
				String lastName = actorResult.getString("last_name");
				actor = new Actor(actorId, firstName, lastName);
				actor.setFilms(findFilmsByActorId(actorId));
			}
			actorResult.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			String sql = "SELECT actor.* "
					+ "FROM actor "
					+ "JOIN film_actor ON actor.id = film_actor.actor_id "
					+ "JOIN film ON film_actor.film_id = film.id "
					+ "WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet castResult = stmt.executeQuery();
			while (castResult.next()) {
				int actorId = castResult.getInt("id");
				String firstName = castResult.getString("first_name");
				String lastName = castResult.getString("last_name");
				Actor actor = new Actor(actorId, firstName, lastName);
//				If user wishes to select an actor from the cast listing, to view their film list,
//				a discrete setFilms method will need to be called from outside this method
//				with the actor passed as a parameter, to prevent recursive methods				
//				actor.setFilms(findFilmsByActorId(actorId));
				actors.add(actor);
			}
			castResult.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	@Override
	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			String sql = "SELECT film.* "
					+ "FROM film "
					+ "JOIN film_actor ON film.id = film_actor.film_id "
					+ "WHERE film_actor.actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet filmographyResult = stmt.executeQuery();
			while (filmographyResult.next()) {
				int filmId = filmographyResult.getInt("id");
//				String title = filmographyResult.getString("title");
//				String desc = filmographyResult.getString("description");
//				short releaseYear = filmographyResult.getShort("release_year");
//				int langId = filmographyResult.getInt("language_id");
//				String language = convertLanguage(langId);
//				int rentDur = filmographyResult.getInt("rental_duration");
//				double rate = filmographyResult.getDouble("rental_rate");
//				int length = filmographyResult.getInt("length");
//				double repCost = filmographyResult.getDouble("replacement_cost");
//				String rating = filmographyResult.getString("rating");
//				String features = filmographyResult.getString("special_features");
//				Film film = new Film(filmId, title, desc, releaseYear, langId, language, rentDur, rate, length, repCost, rating, features);
//				film.setCast(findActorsByFilmId(filmId));
				Film film = findFilmById(filmId);
				films.add(film);
			}
			filmographyResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}
	
	@Override
	public List<Film> findFilmsByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet keywordFilmResult = stmt.executeQuery();
			while (keywordFilmResult.next()) {
				int filmId = keywordFilmResult.getInt("id");
//				String title = keywordFilmResult.getString("title");
//				String desc = keywordFilmResult.getString("description");
//				short releaseYear = keywordFilmResult.getShort("release_year");
//				int langId = keywordFilmResult.getInt("language_id");
//				String language = convertLanguage(langId);
//				int rentDur = keywordFilmResult.getInt("rental_duration");
//				double rate = keywordFilmResult.getDouble("rental_rate");
//				int length = keywordFilmResult.getInt("length");
//				double repCost = keywordFilmResult.getDouble("replacement_cost");
//				String rating = keywordFilmResult.getString("rating");
//				String features = keywordFilmResult.getString("special_features");
//				Film film = new Film(filmId, title, desc, releaseYear, langId, language, rentDur, rate, length, repCost, rating, features);
//				film.setCast(findActorsByFilmId(filmId));
				Film film = findFilmById(filmId);
				films.add(film);
			}
			keywordFilmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}
	
	@Override
	public List<InventoryItem> findInventoryByFilmId(int filmId) {
		List<InventoryItem> inventoryItems = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			String sql = "SELECT inventory_item.* "
					+ "FROM film "
					+ "JOIN inventory_item ON film.id = inventory_item.film_id "
					+ "WHERE film.id = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet inventoryResult = stmt.executeQuery();
			while (inventoryResult.next()) {
				int id = inventoryResult.getInt("id");
				int storeId = inventoryResult.getInt("store_id");
				String condition = inventoryResult.getString("media_condition");
				Timestamp timestamp = inventoryResult.getTimestamp("last_update");
				InventoryItem item = new InventoryItem(id, filmId, storeId, condition, timestamp);
				inventoryItems.add(item);
			}
			inventoryResult.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inventoryItems;
	}
	
	@Override
	public String convertLanguage(int langId) {
		String language = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			String sql = "SELECT language.* "
					+ "FROM film "
					+ "JOIN language ON film.language_id = language.id "
					+ "WHERE language.id = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, langId);
			ResultSet languageResult = stmt.executeQuery();
			if (languageResult.next()) {
				language = languageResult.getString("name");
			}
			languageResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return language;
	}
	
	@Override
	public String findCategory(int filmId) {
		String category = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			String sql = "SELECT category.name "
					+ "FROM film "
					+ "JOIN film_category ON film.id = film_category.film_id "
					+ "JOIN category ON film_category.category_id = category.id "
					+ "WHERE film.id = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet categoryResult = stmt.executeQuery();
			if (categoryResult.next()) {
				category = categoryResult.getString("name");
			}
			categoryResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
}
