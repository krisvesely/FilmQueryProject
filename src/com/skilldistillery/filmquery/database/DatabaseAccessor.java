package com.skilldistillery.filmquery.database;

import java.util.List;

import com.skilldistillery.filmquery.entities.*;

public interface DatabaseAccessor {
	Film findFilmById(int filmId);

	Actor findActorById(int actorId);

	List<Actor> findActorsByFilmId(int filmId);

	List<Film> findFilmsByActorId(int actorId);

	List<Film> findFilmsByKeyword(String keyword);

	List<InventoryItem> findInventoryByFilmId(int filmId);

	String convertLanguage(int langId);

	String findCategory(int filmId);

}
