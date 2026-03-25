import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatCreateTest {
    private static final Log log = LogFactory.getLog(CatCreateTest.class);
    public static String endpointv1 = "/api/v1/cats";
    public static String baseUrl = "http://localhost:8081";

    @Test
    @DisplayName("Создание кота. Текстовый JSON")
    public void CreateCatStringJSONTest(){
        /**
         * что нужно для пост запроса:
         * 1. Body передать - для этого создать стринг переменную
         * 2. Передать хедер - 'Content-Type: application/json'
         * 3.В запросе указать что за метод
         */

        String body = "{\n" +
                "  \"name\": \"Whiskers\",\n" +
                "  \"age\": 3,\n" +
                "  \"color\": \"TABBY\",\n" +
                "  \"breed\": \"Maine Coon\",\n" +
                "  \"weight\": 4.5,\n" +
                "  \"vaccinated\": true,\n" +
                "  \"birthDate\": \"2021-03-15\",\n" +
                "  \"ownerEmail\": \"owner@example.com\"\n" +
                "}";
        String response = given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(body)
                .when()
                .post(endpointv1)
                .then()
                .statusCode(201)
                .extract().asString();
        System.out.println(response);

    }

    @Test
    @DisplayName("Создание кота с боди в виде мапы")
    public void CreateCatWithMapTest(){
        Map<String, Object> body = new HashMap<>();
        body.put("name", "Tom");
        body.put("age", 3);
        body.put("color", "BLACK");
        body.put("breed", "Siamese");
        body.put("weight", 3.2);
        body.put("vaccinated", true);
        body.put("birthDate", "2022-01-10");
        body.put("ownerEmail", "tom@example.com");
        System.out.println(body);
        String response = given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(body)
                .when()
                .post(endpointv1)
                .then()
                .log().all()
                .statusCode(201)
                .extract().asString();


        System.out.println(response);
    }
    @Test
    @DisplayName("Создание кота с боди в виде многострочной cтроки")
    public void CreateCatWithStringTest() {
        String json = """
                {
                "name": "Leo",
                "age": 2,
                "color": "WHITE",
                "breed": "Persian",
                "weight": 3.9,
                "vaccinated": false,
                "birthDate": "2023-05-01",
                "ownerEmail": "leo@example.com"
                }
                """;
        Response response = given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(json)
                .when()
                .post(endpointv1)
                .then()
                .statusCode(201)
                .extract().response();
        response.prettyPrint();
       Integer age = response.path("age");
       Integer id = response.path("id");
       assertEquals(2,age);
       given()
               .baseUri(baseUrl)
               .when()
               .get(endpointv1)
               .then()
               .statusCode(200)
               .body("content.id",hasItem(id));
    }
}
