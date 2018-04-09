package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Language;

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
		input.close();
	}

	private void implementSelection(Scanner input, String selection) {

		DatabaseAccessorObject dbAccess = new DatabaseAccessorObject();
		String id = "";

		if (selection.equals("1")) {
			// Select a Film by the film ID
			System.out.print("Please select a Film by the film ID: ");
			if (input.hasNextInt()) {
				id = input.nextLine();
			}
			else {
				System.out.println("Invalid ID. It must be an integer.");
				input = new Scanner(System.in);
				implementSelection(input, selection);
				return;
			}

			// Verify if result was returned
			Film film = dbAccess.getFilmById(id);
			Language l = dbAccess.getLanguageById(id);
			List<Actor> actors = dbAccess.getActorsByFilmId(id);
			if (film == null) {
				System.out.println("The film ID provided did not return a match");
				implementSelection(input, selection);
			}
			// Print film results
			else {
				System.out.println("\nBelow is the information for the selected Film(s)");
				System.out.println("Film Title: " + film.getTitle() + " | Year: " + film.getRelease_year()
						+ " | Rating: " + film.getRating() + " | Description: " + film.getDescription() + 
						" | Language: " + l.getLanguage());
				System.out.println("The actors in this film are " + actors.toString());
			}

		} else if (selection.equals("2")) {
			System.out.print("Please enter a search keyword: ");
			String text = input.nextLine();

			// Verify if result was returned
			List<Film> films = dbAccess.getFilmBySearchKeyword(text);
			if (films.size() == 0) {
				System.out.print("There were no films that matched that keyword");
			} else {
				System.out.println("\nBelow is the information for the selected Film");
				for (Film f : films) {
					Language l = db.getLanguageById(Integer.toString(f.getId()));
					List<Actor> actors = dbAccess.getActorsByFilmId(Integer.toString(f.getId()));
					System.out.println("Film Title: " + f.getTitle() + " | Year: " + f.getRelease_year() + " | Rating: "
							+ f.getRating() + " | Description: " + f.getDescription() + " | Language: " + l.getLanguage());
					System.out.println("The actors in this film are " + actors.toString() + "\n");
				}
			}
		}
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
