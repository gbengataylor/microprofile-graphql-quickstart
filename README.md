# microprofile-graphql-quickstart project

https://quarkus.io/guides/microprofile-graphql


This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```
## To test Queries

Go to http://localhost:8080/graphql-ui

### Example Queries

```
#Query all films
query allFilms {
  allFilms {
    title
    director
    releaseDate
    episodeID
  }
}
```

```
#Query all films, certain fields
query allFilms {
  allFilms {
    title
    releaseDate
  }
}
```

```
# retrieve specific film
query getFilm {
  film(filmId: 1) {
    title
    director
    releaseDate
    episodeID
  }
}
```

```
# retrieve specific films
query getFilms {
  film0: film(filmId: 0) {
    title
    director
    releaseDate
    episodeID
  }
  film1: film(filmId: 1) {
    title
    director
    releaseDate
    episodeID
  }
}
```

```
#retrieve films with heroes included..See implement FilmResource.heroes that assoicates Films with Heroes
query getFilmHeroes {
  film(filmId: 1) {
    title
    director
    releaseDate
    episodeID
    heroes {
      name
      height
      mass
      darkSide
      lightSaber
    }
  }
}
```

```
# add a Hero
mutation addHero {
  createHero(hero: {
      name: "Han",
      surname: "Solo"
      height: 1.85
      mass: 80
      darkSide: false
      episodeIds: [4, 5, 6]
  	}
  )
  {
    name
    surname
  }
}
```

```
#Delete a hero
mutation DeleteHero {
  deleteHero(id :3){
    name
    surname
  }
}
```

```
#Query based on field (but using default value, see implementation)
query heroWithDefaultSurname {
  heroesWithSurname{
    name
    surname
    lightSaber
  }
}
#Query based on field
query heroWithSurnames {
  heroesWithSurname(surname: "Vader") {
    name
    surname
    lightSaber
  }
}
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `microprofile-graphql-quickstart-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/microprofile-graphql-quickstart-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/microprofile-graphql-quickstart-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.