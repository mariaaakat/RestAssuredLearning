package client;

import config.BaseCatSpec;
import constants.Endpoints;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

//клиент нужен для работы с апи , он позволяет создавать вызовы http запросов. Это хорошо для тестов тем что скрывает детали запроса и позволяет переиспользовать код.
// Будет содержать свой метод для каждого запроса.
public class CatApiClient {
   public Response createCat(Map<String, Object> body) {
       return given()
               .spec(BaseCatSpec.baseCatSpec()) //вызов baseSpec позволяет применять общие настройки к запросу
               .body(body)
               .when()
               .post(Endpoints.CATS)
               .then()
               .extract().response();
   }
    public Response getCatById(Integer id) {
        return given()
                .spec(BaseCatSpec.baseCatSpec())//вызов baseSpec позволяет применять общие настройки к запросу
                .pathParam("id", id)
                .when()
                .get(Endpoints.CAT_BY_ID)
                .then()
                .extract().response();
    }
}
