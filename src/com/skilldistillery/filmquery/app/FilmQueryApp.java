package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.makeSelection();
	}

	private void makeSelection() {
		Scanner input = new Scanner(System.in);
		String selection = userMenu(input);
		if (selection.equals("3")) {
			System.out.println("Goodbye");
			return;
		} else if (selection.equals("1") || selection.equals("2")) {
			implementSelection(input, selection);
			return;
		} else {
			System.out.println("The number provided was not valid. Please select a valid option");
			makeSelection();
		}
		// startUserInterface(input);
		input.close();
	}

	private void implementSelection(Scanner input, String selection) {

		DatabaseAccessorObject dbAccess = new DatabaseAccessorObject();
		int id;

		if (selection.equals("1")) {
			// Select a Film by the film ID
			System.out.print("Please select a Film by the film ID: ");
			id = input.nextInt();

			// Verify if result was returned
			Film film = dbAccess.getFilmById(id);
			if (film == null) {
				System.out.println("The film ID provided was not valid");
				implementSelection(input, selection);
			}
			// Print film results
			else {
				System.out.println("\nBelow is the information for the selected Film");
				System.out.println("Film Title: " + film.getTitle() + " | Year: " + film.getRelease_year()
						+ " | Rating: " + film.getRating() + " | Description: " + film.getDescription() + "\n");
			}

		} else if (selection.equals("2")) {
			System.out.print("Please enter a search keyword: ");
			String text = input.nextLine();

			// Verify if result was returned
			Film film = dbAccess.getFilmBySearchKeyword(text);
			if (film == null) {
				System.out.println("The film ID provided was not valid");
				implementSelection(input, selection);
			}
			// Print film results
			else {
				System.out.println("\nBelow is the information for the selected Film");
				System.out.println("Film Title: " + film.getTitle() + " | Year: " + film.getRelease_year()
						+ " | Rating: " + film.getRating() + " | Description: " + film.getDescription() + "\n");
			}
		}
		/*
		 * // Select an Actor by their ID
		 * System.out.print("Please select an Actor by their ID: "); id =
		 * input.nextInt();
		 * 
		 * // Print actor results
		 * System.out.println("\nBelow is the information for the selected Actor");
		 * System.out.println(dbAccess.getActorById(id) + "\n");
		 * 
		 * 
		 * 
		 * // Get Actors by Film ID
		 * System.out.print("Please select a Film ID to obtain a list of actors: "); id
		 * = input.nextInt();
		 * 
		 * // Print Actors by Film ID results System.out.
		 * println("\nBelow is the information for the actors by the Film ID " + id);
		 * List<Actor> actorList = dbAccess.getActorsByFilmId(id); int count = 1; for
		 * (Actor a : actorList) { System.out.println("Actor " + count++ + ": " +
		 * a.toString()); }
		 */
	}

	public String userMenu(Scanner input) {
		System.out.println("Please select from the following options:\n1. Look up a film by its ID\n2. "
				+ "Look up a film by a search keyword\n3. Exit the application");
		String selection;
		if (input.hasNextInt()) {
			selection = input.nextLine();
		} else {
			selection = "";
		}
		return selection;
	}

}
