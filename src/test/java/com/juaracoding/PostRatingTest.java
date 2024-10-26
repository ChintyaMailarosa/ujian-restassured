package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.client.methods.RequestBuilder.post;
import static org.hamcrest.CoreMatchers.equalTo;

Public class PostRating{
   
    String baseUrl = "https://api.themoviedb.org/3";
    String myToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMmY5MzA1MTBiZjgyYzU0MGZkMWJkOGE1M2NhZTg2ZSIsIm5iZiI6MTcyOTkxMTQ0OC43MTk0ODYsInN1YiI6IjY3MTlkM2VjOWZmNjgxZDllMGEzYzEwZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.o3IcBl-DAHNKz9fpHQhyv1DT_kgUC3JPjUnTTvW0oac";
    int movieId = 32;
    double ratingValue = 8.5;

 @Test
    public void testPost() { 
            JSONObject request = new JSONObject();
            request.put("value", 8.5);

            System.out.println(request.toJSONString());
            given()
                    .header("Authorization", myToken)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(request.toJSONString())
                    .when()
                    .post("/" + movieId + "/rating")
                    .then()
                    .statusCode(201)
                    .body("results.title[0]", equalTo("The item/record was updated successfully."))
                    .log().all();
        }
}

