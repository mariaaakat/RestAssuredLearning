import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CatCardTest {
    public static final org.hamcrest.Matcher<Object> MATCHER = notNullValue();
    public static String endpoint = "http://localhost:8081/api/cats/";
    public static String endpointv1 = "http://localhost:8081/api/v1/cats/";
    /**
     * Чек лист:
     * 1.Проверка статус коа ответа при успехе (200)
     * 2.Проверка формата ответа
     * 3.Проверка существующего id
     * 4.Проверка несуществующего id
     * 5.Проверка id не с числовым форматом
     * 6.Проверка id отрицательного значения
     * 7.Проверка структуры ответа
     */

    @Test
    @DisplayName("Проверка статус кода ответа 200")
    public void CatCardStatusCod200Test(){
        given()
                .when()
                .get(endpointv1 + "1")
                .then()
                .statusCode(200);
    }


    @Test
    @DisplayName("Проверка формата ответа - JSON")
    public void CatCardFormatJSONTest(){
        given()
                .when()
                .get(endpointv1 + "1")
                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    @DisplayName("Проверка содержимого ответа")
    public void CatCardFormatContentTest(){
        given()
                .when()
                .get(endpointv1 + "1")
                .then()
                //.log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1))
                .body("name", notNullValue())
                .body("age",notNullValue())
                .body("color",notNullValue())
                .body("breed",notNullValue())
                .body("weight",notNullValue())
                .body("vaccinated",notNullValue() )
                .body("birthDate",notNullValue())
                .body("ownerEmail",notNullValue())
                .body("status",notNullValue())
                .body("createdAt",notNullValue())
                .body("updatedAt",notNullValue());
    }


    @Test
    @DisplayName("Проверка несуществующего id")
    public void CatCardNotIdTest(){
        given()
                .when()
                .get(endpointv1 + "20")
                .then()
                .log().body()
                .statusCode(404)
                .contentType(ContentType.JSON);
    }

    @Test
    @DisplayName("Проверка не правильного формата id")
    public void CatCardStringIdTest(){
        given()
                .when()
                .get(endpointv1 + "Go")
                .then()
                .log().body()
                .statusCode(400)
                .contentType(ContentType.JSON);
    }

    @Test
    //TODO Требуется завести баг
    @DisplayName("Проверка отрицательного id")
    public void CatCardNegativeIdTest(){
        given()
                .when()
                .get(endpointv1 + "-15")
                .then()
                .log().body()
                .statusCode(400)
                .contentType(ContentType.JSON);
    }

}
