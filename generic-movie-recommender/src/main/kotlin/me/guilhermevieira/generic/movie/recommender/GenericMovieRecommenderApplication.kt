package me.guilhermevieira.generic.movie.recommender

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GenericMovieRecommenderApplication

fun main(args: Array<String>) {
    runApplication<GenericMovieRecommenderApplication>(*args)
}
