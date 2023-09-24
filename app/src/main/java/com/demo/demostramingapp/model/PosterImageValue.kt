package com.demo.demostramingapp.model

/** Enum class use to hold the Poster file name. */
enum class PosterImageValue(val poster: String) {
    POSTER1("poster1.jpg"),
    POSTER2("poster2.jpg"),
    POSTER3("poster3.jpg"),
    POSTER4("poster4.jpg"),
    POSTER5("poster5.jpg"),
    POSTER6("poster6.jpg"),
    POSTER7("poster7.jpg"),
    POSTER8("poster8.jpg"),
    POSTER9("poster9.jpg"),
    UNKNOWN("placeholder_for_missing_posters"),
}

/** Enum class use to hold the JSON file name. */
enum class MovieListFileName(val fileName: String) {
    PAGE1("CONTENTLISTINGPAGE-PAGE1.json"),
    PAGE2("CONTENTLISTINGPAGE-PAGE2.json"),
    PAGE3("CONTENTLISTINGPAGE-PAGE3.json")
}