package me.guilhermevieira.personalizedmovie.recommender

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PersonalizedMovieRecommenderApplication

fun main(args: Array<String>) {
    runApplication<PersonalizedMovieRecommenderApplication>(*args)
}
