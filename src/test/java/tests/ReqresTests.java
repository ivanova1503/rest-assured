package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;


public class ReqresTests {

    @Test
    void successfullLogin() {

        /*
        ДАНО:

        Request url: https://reqres.in/api/login

        Data:
        {
         "email": "eve.holt@reqres.in",
         "password": "cityslicka"
        }

        Response:

        {
           "token": "QpwL5tke4Pnpja7X4"
        }
         */

        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";    // следить за символами, может сломать тест

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));


}

    @Test
    void negativeLogin() {

        /*
        ДАНО:

        Request url: https://reqres.in/api/login

        Data:
        {
            "email": "peter@klaven"
        }

        Response:
        {
            "error": "Missing password"
        }
         */

        String data = "{\"email\": \"peter@klaven\"}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));


    }


}
