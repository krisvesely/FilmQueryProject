package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.*;

public class FilmQueryApp {

	private DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.test();
//    app.launch();
	}

	private void test() {
		
//    	Film film = db.findFilmById(54);
//		if (film != null) {
//			System.out.println(film);
//		}
//		else {
//			System.out.println("No such film found!");
//		}
//		System.out.println();
//		
//		Actor actor = db.findActorById(143);
//		if (actor != null) {
//			System.out.println(actor);
//		}
//		else {
//			System.out.println("No such actor found!");
//		}
//		System.out.println();
				
		List<Actor> cast = db.findActorsByFilmId(74); 
		if (cast != null) {
			for (Actor castMember : cast) {
				System.out.println(castMember);
			}
		}
		else {
			System.out.println("No such film found!");
		}
		System.out.println();

//		List<Film> filmography = db.findFilmsByActorId(143);
//		if (filmography != null) {
//			for (Film specificFilm : filmography) {
//				System.out.println(specificFilm);
//			}
//		}
//		else {
//			System.out.println("No such actor found!");
//		}
  }

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {

	}

}
