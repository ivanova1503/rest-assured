package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.equalTo;

public class HW {

    @Test
    void listUsersGetId() { //получает список пользователей и проверяет, что есть нужный id ="..."
        given()
                .contentType(JSON)
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data.id", hasItems(11));  // проверяет, что есть такой id=11
    }

    @Test
    void listUsersGetAvatar() { //получает список пользователей и проверяет, что определенный аватар не пустой
        given()
                .contentType(JSON)
                //.when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data.avatar", hasItems("https://reqres.in/img/faces/8-image.jpg"));  // проверяет, что есть аватар по данной ссылке
    }

    @Test
    void listUsersHasName() { //получает список пользователей и проверяет, что он содержит каке-то имена из списка
        given()
                .contentType(JSON)
                //.when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data.first_name", hasItems("Michael","Lindsay","Tobias"));
    }

    @Test
    void singleUser() { //получает список пользователей и проверяет, что он содержит каке-то имена из списка
        given()
                .contentType(JSON)
                .get("https://reqres.in/api/users/2")
                .then()
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"))
                .body("support.url", equalTo("https://reqres.in/#support-heading"));
    }

    @Test
    void singleUserNotFound() { //пользователь не найден
        given()
                .contentType(JSON)
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    void countTotal() {
        Integer response =
                get("https://reqres.in/api/users?page=2")
                        .then()
                        .statusCode(200)
                        .extract().path("total");

        System.out.println(response);
        assertThat(response).isEqualTo(12);
    }

    @Test
    void countUsers() {
        ArrayList response =
                get("https://reqres.in/api/users?page=2")
                        .then()
                        .statusCode(200)
                        .extract().path("data.id");

        System.out.println("Количество пользователей = " + response.size());
    }

    @Test
    void avatarIsNotNull() {
        ArrayList<String> response =       //создаем arraylist из стринговых значений ответа
                get("https://reqres.in/api/users?page=2")
                        .then()
                        .statusCode(200)
                        .extract().path("data.avatar");
        //response.set(0, null);  если установить в один из элементов массива null, то тест упадет

        for (int i=0; i<response.size(); i++) {      // выполняется проверка, что каждый элемент массива не пустой, то есть аватар установлен
            assertThat(response.get(i)).isNotNull();
        }
        System.out.println("Проверено, что у всех пользователей установлен аватар");

    }

}







