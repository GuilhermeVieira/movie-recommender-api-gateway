package me.guilhermevieira.movie.recommender.gateway

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Configuration
class Gateway {

    @Bean
    fun routeLocator(routeLocatorBuilder: RouteLocatorBuilder): RouteLocator = routeLocatorBuilder.routes()
        .route("Personalized Movie Recommender") { routeSpec ->
            routeSpec
                .path("/movies/recommendations")
                .and().asyncPredicate { it.isUsernameInWhiteList() }
                .filters { filterSpec ->
                    filterSpec
                        .circuitBreaker { circuitBreakerConfig ->
                            circuitBreakerConfig.setName("Personalized Fallback")
                                .setFallbackUri("forward:/fallback")
                                .addStatusCode(HttpStatus.NOT_FOUND.name)
                        }
                        .filter { exchange, chain ->
                            chain.filter(
                                exchange.mutate().request {
                                    it.header("X-USERNAME", exchange.extractUsernameFromQueryParam())
                                }.build()
                            )
                        }
                        .removeRequestParameter("username")
                }
                .uri("http://localhost:8082")
        }
        .route("Generic Movie Recommender") { routeSpec ->
            routeSpec
                .path("/movies/recommendations", "/fallback")
                .filters {
                    it.rewritePath("/fallback", "/movies/recommendations")
                }
                .uri("http://localhost:8081")
        }.build()

    private fun ServerWebExchange.extractUsernameFromQueryParam(): String =
        this.request.queryParams.getFirst("username") ?: ""

    private fun ServerWebExchange.isUsernameInWhiteList(): Mono<Boolean> = Mono.just(
        Username.whitelist.contains(this.extractUsernameFromQueryParam())
    )

}