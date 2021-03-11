package me.guilhermevieira.movie.recommender.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieRecommenderGatewayApplication

fun main(args: Array<String>) {
    runApplication<MovieRecommenderGatewayApplication>(*args)
}
