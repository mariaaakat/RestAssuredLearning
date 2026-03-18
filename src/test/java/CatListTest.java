import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CatListTest {
    public static String endpoint = "/api/cats";
    public static String endpointv1 = "/api/v1/cats";

    @Test
    @DisplayName("Получить список котов")
    public void getCatListTest(){
        given()
                .when()
                .get("http://localhost:8081" + endpointv1)
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Получить список котов с логированием body")
    public void getWithLogListTest(){
        given()
                .when()
                .get("http://localhost:8081" + endpointv1)
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    @DisplayName("Проверка технических значений возвращаемого списка")
    public void getReturnListTest(){
        given()
                .baseUri("http://localhost:8081")
                .when()
                .get(endpointv1)
                .then()
                .log().body()
                .statusCode(200)
                .body("last", equalTo(false))
                .body("size", equalTo(10))
                .body("totalElements", equalTo(16));
    }

    @Test
    @DisplayName("Проверка кошки из возвращаемого списка")
    public void getCatFromListCatTest(){
        given()
                .baseUri("http://localhost:8081")
                .when()
                .get(endpointv1)
                .then()
                //.log().body()
                .statusCode(200)
                .body("content[1].id", equalTo(11))
                .body("content[1].createdAt", notNullValue())
                .body("content[1].id", greaterThan(0))//больше чем
                .body("content[1].weight", lessThan(10F));//меньше чем
    }

    @Test
    @DisplayName("Проверка отправки запроса на некорректный эндпоинт")
    public void getCatListErrorEndpoindTest(){
        given()
                .baseUri("http://localhost:8081")
                .when()
                .get("/api/v1/catss")
                .then()
                .log().body()
                .statusCode(500);
    }

    @Test
    @DisplayName("Проверка отправки запроса с некорректным значением")
    public void getCatListErrorParameterTest(){

        given()
                .baseUri("http://localhost:8081")
                .when()
                .get(endpoint + "?page=-1")
                .then()
                //.log().body()
                .statusCode(500);
    }
}
