package com.skilldistillery.filmquery.database;

import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Language;

public interface DatabaseAccessor {
  public Film getFilmById(String filmId);
  public Actor getActorById(String actorId);
  public Language getLanguageById(String filmId);
  public List<Film> getFilmBySearchKeyword(String keyword);
  public List<Actor> getActorsByFilmId(String filmId);
}
