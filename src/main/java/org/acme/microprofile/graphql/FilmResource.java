package org.acme.microprofile.graphql;


import java.util.List;

import javax.inject.Inject;

import org.eclipse.microprofile.graphql.DefaultValue;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

import io.vertx.core.cli.annotations.Description;

@GraphQLApi
public class FilmResource {

    @Inject
    GalaxyService service;

    // queries

    @Query("allFilms") 
    @Description("Get all Films from a galaxy far far away") 
    public List<Film> getAllFilms() {
        return service.getAllFilms();
    }

    @Query
    @Description("Get a Films from a galaxy far far away")
    public Film getFilm(@Name("filmId") int id) {
        return service.getFilm(id);
    } 
    // this is not a "hero" query however will update the film schema to include associated heroes
    public List<Hero> heroes(@Source Film film) { 
        return service.getHeroesByFilm(film);
    }

// mutations
    @Mutation
    public Hero createHero(Hero hero) {
        service.addHero(hero);
        return hero;
    }

    @Mutation
    public Hero deleteHero(int id) {
        return service.deleteHero(id);
    }

    // queries by field
    // note this is a hero query
    @Query
    public List<Hero> getHeroesWithSurname(@DefaultValue("Skywalker") String surname) {
        return service.getHeroesBySurname(surname);
    }
}