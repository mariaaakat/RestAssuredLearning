package client;

import config.BaseCatSpec;
import constants.Endpoints;
import dto.CreateCatRequestDto;
import dto.CreateCatResponseDto;
import dto.DeleteCatRequestDto;
import dto.UpdateCatRequestDTO;
import io.restassured.response.Response;
import utils.CatGenerator;

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

    public Response deleteCatByID(Integer id){
       return given()
               .spec(BaseCatSpec.baseCatSpec())
               .pathParam("id", id)
               .when()
               .delete(Endpoints.CAT_BY_ID)
               .then()
               .extract().response();
    }
        //Метод принимает на вход дто создание кота , а извлекается как дто ответа
    public CreateCatResponseDto createCat(CreateCatRequestDto body) {
        return given()
                .spec(BaseCatSpec.baseCatSpec())
                .body(body)
                .when()
                .post(Endpoints.CATS)
                .then()
                .extract()
                .as(CreateCatResponseDto.class);
    }

    public UpdateCatRequestDTO updateCat(UpdateCatRequestDTO body, int id){
       return given()
               .spec(BaseCatSpec.baseCatSpec())
               .body(body)
               .pathParam("id", id)
               .when()
               .put(Endpoints.CAT_BY_ID)
               .then()
               .extract()
               .as(UpdateCatRequestDTO.class);
    }
    public DeleteCatRequestDto deleteCat(DeleteCatRequestDto deleteDto){
        int id = deleteDto.getId();
        return (DeleteCatRequestDto) given()
                .spec(BaseCatSpec.baseCatSpec())
                .pathParam("id", id)
                .when()
                .delete(Endpoints.CAT_BY_ID)
                .then()
                .extract().response();
    }
    public CreateCatResponseDto getCat(DeleteCatRequestDto getCat){
       int id = getCat.getId();
       return given()
               .spec(BaseCatSpec.baseCatSpec())
               .pathParam("id", id)
               .when()
               .get(Endpoints.CAT_BY_ID)
               .then()
               .extract()
               .as(CreateCatResponseDto.class);
    }

    public Response updateCatPath(UpdateCatRequestDTO body, int id){
       return given()
               .spec(BaseCatSpec.baseCatSpec())
               .pathParam("id", id)
                .body(body)
                .when()
                .patch(Endpoints.CAT_BY_ID)
                .then()
                .extract()
                .response();
    }
}
