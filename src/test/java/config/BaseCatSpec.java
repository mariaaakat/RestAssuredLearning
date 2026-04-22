package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
//Автоматически применяет общие настройки где используется
public class BaseCatSpec {
    public static final String BASE_URL = "http://localhost:8081";
    public static RequestSpecification baseCatSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType("application/json")
                .build();
    }
}
// return given()
//               .spec(BaseCatSpec.baseCatSpec()) //пример как использовать baseSpec
//               .body(body)
//               .when()
//               .post(Endpoints.CATS)
//               .then()
//               .extract().response();