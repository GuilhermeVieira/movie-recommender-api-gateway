package me.guilhermevieira.generic.movie.recommender

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/movies/recommendations")
class Recommender {

    @GetMapping
    fun findMoviesRecommendationsByUsername(@RequestParam username: String) =
        UserCountry.usernameToCountryMap[username]?.let { country ->
            ResponseEntity.ok(RecommendedMovies.countryToMoviesMap.getValue(country))
        } ?: ResponseEntity.notFound().build()

}

object UserCountry {
    val usernameToCountryMap = mapOf(
        "bioremoa5" to "USA",
        "leonafricanoc2" to "USA",
        "freguentsfw" to "USA",
        "zbrusivu" to "USA",
        "bularioly" to "USA",
        "Mobilteilf8" to "USA",
        "popadijahe" to "USA",
        "teretanapx" to "USA",
        "RemeJoumbzm" to "USA",
        "erarAnnonsedo" to "USA",
        "cwponau6a" to "Australia",
        "prenosujv" to "Australia",
        "gCrosv8" to "Australia",
        "sparkly18ee" to "Australia",
        "pozavo1f" to "Australia",
        "kidamoilatn" to "Australia",
        "AgreeriAged3f" to "Australia",
        "sinnepinunq" to "Australia",
        "gledalarq" to "Australia",
        "matuterek" to "Australia",
        "Batassinif4" to "UK",
        "odmetuog" to "UK",
        "fishead08do" to "UK",
        "RearHoarmamal2o" to "UK",
        "pararlo2k" to "UK",
        "futrolup3" to "UK",
        "flacsoticsby" to "UK",
        "iceridergtrmg" to "UK",
        "motikamit1" to "UK",
        "ammollatol1" to "UK"
    )
}

object RecommendedMovies {
    val countryToMoviesMap = mapOf(
        "Australia" to listOf(
            "Pieces of a Woman",
            "Death to 2020",
            "Rebecca",
            "The Trial of the Chicago 7",
            "Top End Wedding",
            "Call Me By Your Name",
            "The Half of It",
            "BlacKkKlansman",
            "All the Bright Places",
            "Uncut Gems"
        ),
        "UK" to listOf(
            "Parasite",
            "Soul",
            "Clemency",
            "Collective",
            "Portrait of a Lady on Fire",
            "Rocks",
            "Saint Maud",
            "The Assistant",
            "Mank",
            "Never Rarely Sometimes Always"
        ),
        "USA" to listOf(
            "Hamilton",
            "Borat 2: Subsequent Moviefilm",
            "My Spy",
            "Extraction",
            "Phineas & Ferb the Movie",
            "Mulan",
            "The Old Guard",
            "The Trial of the Chicago 7",
            "The Witches",
            "The Lovebirds"
        )
    )
}
