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
		//app.test();
		app.launch();
	}

	private void test() {
		Film film = db.getFilmById(1);
		System.out.println(film);
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {

		DatabaseAccessorObject dbAccess = new DatabaseAccessorObject();
		
		// Select an Actor by their ID
		System.out.print("Please select an Actor by their ID: ");
		int id = input.nextInt();
		
		// Print actor results
		System.out.println("\nBelow is the information for the selected Actor");
		System.out.println(dbAccess.getActorById(id) + "\n");
		
		// Select a Film by the film ID
		System.out.print("Please select a Film by the film ID: ");
		id = input.nextInt();
		
		// Print film results
		System.out.println("\nBelow is the information for the selected Film");
		System.out.println(dbAccess.getFilmById(id) + "\n");
		
		// Get Actors by Film ID
		System.out.print("Please select a Film ID to obtain a list of actors: ");
		id = input.nextInt();
		
		// Print Actors by Film ID results
		System.out.println("\nBelow is the information for the actors by the Film ID " + id);
		List<Actor> actorList = dbAccess.getActorsByFilmId(id);
		int count = 1;
		for (Actor a : actorList) {
			System.out.println("Actor " + count++ + ": " + a.toString());
		}
	}

}
