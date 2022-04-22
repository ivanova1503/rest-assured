package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniodTests {
    // make request to https://selenoid.autotests.cloud/status
    // total is 20 (максимальное количество сессий в селеноиде)

    @Test
    void checkTotal() {
        given() // эту строку можно удалить
        .when() // и эту строку можно удалить
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void checkChromeVersion() {   //проверяет, что в ответе json есть бразуер chrome версии 91.0
        get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("browsers.chrome", hasKey("99.0"));
    }

    @Test
    void checkTotalBadPractice() {   //плохой тест
    String response =
                get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .extract().response().asString();

        System.out.println(response);

        String expectedResponse = "{\"total\":20,\"used\":0,\"queued\":0,\"pending\":0," +
                "\"browsers\":{" +
                "\"chrome\":{\"100.0\":{},\"99.0\":{}}," +
                "\"firefox\":{\"97.0\":{},\"98.0\":{}}," +
                "\"opera\":{\"84.0\":{},\"85.0\":{}}}}\n";

        assertEquals(expectedResponse, response);
    }


    @Test
    void checkTotalGoodPractice() {   //плохой тест
        Integer response =
                get("https://selenoid.autotests.cloud/status")
                        .then()
                        .statusCode(200)
                        .extract().path("total");

        System.out.println(response);

        assertEquals(20, response);
    }

    @Test
    void responseExamples() {
        Response response =
                get("https://selenoid.autotests.cloud/status")
                        .then()
                        .extract().response();

        System.out.println(response);  // io.restassured.internal.RestAssuredResponseImpl@15e0fbdb
        System.out.println(response.asString());    // ответ в формате json
        System.out.println(response.toString());    // io.restassured.internal.RestAssuredResponseImpl@15e0fbdb
        System.out.println("Response .path(\"total\"): " + response.path("total")); // почему не выводится без текста? просто значение?
        response.path("browsers.chrome", "browsers.chrome");

        System.out.println(response.path("browsers.chrome").toString());  //{100.0={}, 99.0={}}
    }

    @Test
    void mySeleniod() {
        Response response =
                get("http://149.154.70.38:8080/status")
                        .then()
                        .extract().response();

        System.out.println(response.asString());  //ответ в формате json
        System.out.println(response.path("state.total").toString());  //количество сессий
        System.out.println(response.path("state.browsers.chrome").toString());  //версии браузеров
    }


    @Test
    void checkTotalWithAssertJ() {
        Integer response =
                get("https://selenoid.autotests.cloud/status")
                        .then()
                        .statusCode(200)
                        .extract().path("total");

        System.out.println(response);
        assertThat(response).isEqualTo(20);
    }

    @Test
    void checkStatus401() {   //авторизация через restAssured
                get("https://selenoid.autotests.cloud/wd/hub/status")
                        .then()
                        .statusCode(200); //тест упадет так как для него нужна авторизация
                        //.statusCode(401); // test passed
    }

    @Test
    void checkStatus200() {   //авторизация через restAssured
        get("https://user1:1234@selenoid.autotests.cloud/wd/hub/status")    //прокинуты параметры авторизации в запрос
                .then()
                .statusCode(200); //тест упадет так как для него нужна авторизация
    }


    @Test
    void checkStatus200WithGiven() {   //авторизация через restAssured с помощью given
        given()
                .auth().basic("user1", "1234")
                .when()
                .get("https://selenoid.autotests.cloud/wd/hub/status")    //прокинуты параметры авторизации в запрос
                .then()
                .statusCode(200); //тест упадет так как для него нужна авторизация
    }




    }
