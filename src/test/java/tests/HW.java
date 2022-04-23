package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.valueOf;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasValue;

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

    /*@Test
    void listUsersAvatarIsNotNull() { //получает список пользователей и проверяет, что нет пустых аватаров
        given()
                .contentType(JSON)
                //.when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .assertThat().body("data.id").
        int[] array;

*/
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



}







