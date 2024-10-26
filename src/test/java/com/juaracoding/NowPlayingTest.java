package com.juaracoding;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class NowPlayingTest {
    String baseUrl = "https://api.themoviedb.org/3";

    String myToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMmY5MzA1MTBiZjgyYzU0MGZkMWJkOGE1M2NhZTg2ZSIsIm5iZiI6MTcyOTkxMTQ0OC43MTk0ODYsInN1YiI6IjY3MTlkM2VjOWZmNjgxZDllMGEzYzEwZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.o3IcBl-DAHNKz9fpHQhyv1DT_kgUC3JPjUnTTvW0oac";

    @Test
    public void testNowPlaying(){
        given()
                .queryParam("language", "en-US")
                .queryParam("page", 1)
                .header("Authorization",myToken)
                .when()
                .get(baseUrl+"/movie/now_playing")
                .then()
                .statusCode(200)
                .body("results.title[0]",equalTo("The Wild Robot"))
                .log().all();
    }

    @Test
    public void testGetMovieNowPlayingNegativeCase() {
        given()
                .queryParam("invalid", "invalid")
                .queryParam("invalid", "invalid")
                .header("Authorization",myToken)
                .when()
                .get(baseUrl+"/movie/now_playing")
                .then()
                .statusCode(400)
                .body("error",equalTo("The Substance"))
                .log().all();

//        given()
//                .queryParam("page", "2")
//                .when()
//                .get(baseUrl+ "/movie/now_playing")
//                .then()
//                .statusCode(400)
//                .body("error", equalTo("Invalid parameters"));
    }

}
