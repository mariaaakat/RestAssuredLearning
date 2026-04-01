package tests;

import client.CatApiClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.CatGenerator;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatCreateRefactorTest {
    private final CatApiClient catApiClient = new CatApiClient();


    @Test
    @DisplayName("Создание кота с боди в виде мапы")
    public void CreateCatWithMapTest(){
        Map<String, Object> body = CatGenerator.catGenerator();

      Response response = catApiClient.createCat(body)
                .then()
                .statusCode(201)
                .extract().response();
        Integer age = response.path("age");
        Integer id = response.path("id");
        assertEquals(3,age);

    }
}
