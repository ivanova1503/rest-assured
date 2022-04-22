package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import static org.hamcrest.CoreMatchers.is;

public class CoffeemaniaLogin {





    @Test
    void successfullLogin() {
       // WebDriver driver = new ChromeDriver();
/*
        open("https://develop.web-v1.coffeemania.axept.com/login");
        String token = $(By.name("csrf-token")).getAttribute("content");
        System.out.println(token);
       /*
        ДАНО:
        Request url: https://develop.web-v1.coffeemania.axept.com/login
        Data:
        {
         "LoginForm[phone]": "+7(991)700-03-20",
         "password": "1718"
        }
        Response:
        {
           "usertoken": ""
        }
     */
        String data = "{\n" +
                "         \"LoginForm[phone]\": \"+7(991)700-03-20\",\n" +
                "         \"password\": \"1718\"\n" +
                "        }";

        String response =
                 get("https://develop.web-v1.coffeemania.axept.com/login")
                        .then()
                        .extract().response().asString();

        System.out.println(response);
/*
        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://develop.web-v1.coffeemania.axept.com/login")
                .then()
                .statusCode(200)
                .body("usertoken", is(token));
*/
    }
    }


