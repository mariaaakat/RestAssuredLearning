package tests;

import client.CatApiClient;
import constants.Endpoints;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.CatGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    @Test
    @DisplayName("Создание и получение кота по id")
    public void GettingCatTest(){
        Map<String, Object> body = CatGenerator.catGenerator();

        Response response = catApiClient.createCat(body)
                .then()
                .statusCode(201)
                .extract().response();
        Integer age = response.path("age");
        Integer id = response.path("id");
        Response getCatResponse = catApiClient.getCatById(id)
                .then()
                .statusCode(200)
                .extract().response();

        String name = getCatResponse.path("name");
        Integer ageFromJson = getCatResponse.jsonPath().getInt("age");
        String color = getCatResponse.path("color");
        String ownerEmail = getCatResponse.path("ownerEmail");
        assertEquals(body.get("name"), name);
        assertEquals(body.get("age"), age);
        assertEquals(body.get("color"), color);
        assertEquals(body.get("ownerEmail"), ownerEmail);


    }
    @Test
    @DisplayName("Создание кота без обязательных полей имя и емейл")
    public void NegativeCreateCatTest(){
        Map<String, Object> body = new HashMap<>();
        String randomCat = "Cat_" + UUID.randomUUID().toString().substring(0, 5);
        body.put("age", 3);
        body.put("color", "BLACK");
        body.put("breed", "Siamese");
        body.put("weight", 3.2);
        body.put("vaccinated", true);
        body.put("birthDate", "2022-01-10");
        Response response = catApiClient.createCat(body)
                .then()
                .statusCode(400)
                .extract().response();
    }
    @Test
    @DisplayName("Создание кота c отрицательным возрастом")
    public void NegativeAgeCreateCatTest(){
        Map<String, Object> body = new HashMap<>();
        body.put("name", "Tom");
        body.put("age", -3);
        body.put("color", "BLACK");
        body.put("breed", "Siamese");
        body.put("weight", 3.2);
        body.put("vaccinated", true);
        body.put("birthDate", "2022-01-10");
        body.put("ownerEmail", "Tom@mail.ru");
        Response response = catApiClient.createCat(body)
                .then()
                .statusCode(400)
                .extract().response();
    }
    @Test
    @DisplayName("Создание кота c пустыми полями")
    public void NegativeEmptyFieldsCreateCatTest(){
        Map<String, Object> body = new HashMap<>();
        body.put("name", "");
        body.put("age",null );
        body.put("color", "");
        body.put("breed", "");
        body.put("weight", null);
        body.put("vaccinated", null);
        body.put("birthDate", "");
        body.put("ownerEmail", "");
        Response response = catApiClient.createCat(body)
                .then()
                .statusCode(400)
                .extract().response();
    }
    @Test
    @DisplayName("Создание кота c неправмильным ключом name")
    public void NegativeWrongKeyCreateCatTest(){
        Map<String, Object> body = new HashMap<>();
        body.put("nam", "Tom");
        body.put("age",5 );
        body.put("color", "Grey");
        body.put("breed", "Persian");
        body.put("weight", 5);
        body.put("vaccinated", true);
        body.put("birthDate", "2021-01-10");
        body.put("ownerEmail", "Tom@mail.ru");
        Response response = catApiClient.createCat(body)
                .then()
                .statusCode(400)
                .extract().response();
    }

    @Test
    @DisplayName("Создание кота c типом данных String вместо Integer в age")
    public void NegativeStringAgeCreateCatTest(){
        Map<String, Object> body = new HashMap<>();
        body.put("nam", "Tom");
        body.put("age","5" );
        body.put("color", "Grey");
        body.put("breed", "Persian");
        body.put("weight", 5);
        body.put("vaccinated", true);
        body.put("birthDate", "2021-01-10");
        body.put("ownerEmail", "Tom5@mail.ru");
        Response response = catApiClient.createCat(body)
                .then()
                .statusCode(400)
                .extract().response();
    }

    @Test
    @DisplayName("Создание кота c типом данных Integer вместо String в birthDate")
    public void NegativeIntegerBDataCreateCatTest(){
        Map<String, Object> body = new HashMap<>();
        body.put("name", "Tom");
        body.put("age",5);
        body.put("color", "Grey");
        body.put("breed", "Persian");
        body.put("weight", 5);
        body.put("vaccinated", true);
        body.put("birthDate", 2021-01-10);
        body.put("ownerEmail", "Tom5@mail.ru");
        Response response = catApiClient.createCat(body)
                .then()
                .statusCode(400)
                .extract().response();
    }
    @Test
    @DisplayName("Создание кота c null в обязательных полях емейл и имя")
    public void NegativeNullNameAndEmailCreateCatTest(){
        Map<String, Object> body = new HashMap<>();
        body.put("name", null);
        body.put("age",5);
        body.put("color", "Grey");
        body.put("breed", "Persian");
        body.put("weight", 5);
        body.put("vaccinated", true);
        body.put("birthDate", "2021-01-10");
        body.put("ownerEmail", null);
        Response response = catApiClient.createCat(body)
                .then()
                .statusCode(400)
                .extract().response();
    }
    @Test
    @DisplayName("Создание кота c большим количествоим символов в имене")
    public void NegativeManyCharCreateCatTest(){
        Map<String, Object> body = new HashMap<>();
        body.put("name", "YOOOOOOOOOOOOOOOOOOOOOOOOOOO55");
        body.put("age", 5);
        body.put("color", "Grey");
        body.put("breed", "Persian");
        body.put("weight", 5.2);
        body.put("vaccinated", true);
        body.put("birthDate", "2021-01-10");
        body.put("ownerEmail", "Tom5@mail.ru");
        Response response = catApiClient.createCat(body)
                .then()
                .statusCode(400)
                .extract().response();
    }

}
