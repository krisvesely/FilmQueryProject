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
//		app.test();
		app.launch();
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
//				
//		List<Actor> cast = db.findActorsByFilmId(74); 
//		if (cast != null) {
//			for (Actor castMember : cast) {
//				System.out.println(castMember);
//			}
//		}
//		else {
//			System.out.println("No such film found!");
//		}
//		System.out.println();
//
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
		boolean interfaceOpen = true;
		
		System.out.println("Welcome to the Film Database!");
		
		do {
			displayMenu();
			boolean validResponse = false;
			do {
				String response = input.next();
				switch (response) {
				case "1":
					lookupFilmById(input);
					validResponse = true;
					break;
				case "2":
					lookupFilmByKeyword(input);
					validResponse = true;
					break;
				case "3":
				case "exit":
					System.out.println("\nGoodbye.");
					validResponse = true;
					interfaceOpen = false;
					break;
				default:
					System.out.print("\nInvalid response. Please enter 1, 2, or 3 according to your menu choice: ");
				}
				input.nextLine();
			} while (!validResponse);
		} while (interfaceOpen);
	}

	private void displayMenu() {
		System.out.println("\n__________________Menu__________________");
		System.out.println("1: Look up a film by its ID.");
		System.out.println("2: Look up a film by a search keyword.");
		System.out.println("3: Exit the application.");
		System.out.print("\nPlease enter a number corresponding to your menu choice: ");
	}

	private void lookupFilmById(Scanner input) {
		System.out.print("\nPlease enter a film ID: ");
		int filmId = input.nextInt();
		Film film = db.findFilmById(filmId);
		if (film != null) {
			System.out.println("\nHere is the film with ID " + filmId + ":");
			System.out.println(film);
		}
		else {
			System.out.println("\nThere is no film with that ID in the database.");
		}
	}
	
	private void lookupFilmByKeyword(Scanner input) {
		System.out.print("\nPlease enter a keyword: ");
		String filmKeyword = input.next();
		List<Film> keywordFilms = db.findFilmsByKeyword(filmKeyword);
		if (keywordFilms.size() >= 1) {
			System.out.println("\nHere are the " + keywordFilms.size() + " film(s) with '" + filmKeyword + "' in their title or description:");
			for (Film film : keywordFilms) {
				System.out.println(film);
			}
		}
		else {
			System.out.println("\nThere is no film in the database containing '" + filmKeyword + "' within its title or description.");
		}
	}

}
