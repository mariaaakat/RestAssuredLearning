package tests;

import client.CatApiClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import utils.CatGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class CatDeleteTest {

    /**
     * Чек-лист удаления кота:
     * 1.Удаления кота по ид
     *
     * 2.Удаления кота по несуществующему ид
     * 3.Удаление уже удаленного ид
     *
     * 4.Удаление кота с ид с невалидным значением
     * 5.Удаление кота с неверным эндпоинтом
     */

@DisplayName("Удаление кота по существующему ид")
  @Test
  public void deleteCatById(){
    CatApiClient client = new CatApiClient();
    Response createCat = client.createCat(CatGenerator.catGenerator());
    createCat.prettyPrint();
    Integer id = createCat.jsonPath().getInt("id");
    Response deleteCat = client.deleteCatByID(id);
    assertEquals(204 , deleteCat.statusCode());

}
  @DisplayName("Удаление кота по несуществующему ид с типом int")
  @ParameterizedTest
  @ValueSource(ints = {-2, 0, 1000})
  public void deleteCatNotValidIdTest(int id) {
    CatApiClient client = new CatApiClient();
   // Response createCatResponse = client.createCat(CatGenerator.generateBaseCat("Tom", 5, "red", "street", 5.5, true, "10.11.2020", "tom@mail.ru"));
    Response deleteResponse = client.deleteCatByID(id);
    assertEquals(404, deleteResponse.statusCode());
  }

  @DisplayName("Удаление кота с невалидными значениями с пустой, null и String значением")
  @ParameterizedTest
  @NullSource
  @EmptySource
  public void deleteCatNotIntIdTest(Integer id){
    CatApiClient client = new CatApiClient();
    // Response createCatResponse = client.createCat(CatGenerator.generateBaseCat("Tom", 5, "red", "street", 5.5, true, "10.11.2020", "tom@mail.ru"));
    Response deleteResponse = client.deleteCatByID(id);
    assertEquals(404, deleteResponse.statusCode());
  }

}
