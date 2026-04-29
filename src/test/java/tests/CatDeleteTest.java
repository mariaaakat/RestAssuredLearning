package tests;

import client.CatApiClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.CatGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatDeleteTest {
 private static final CatApiClient client = new CatApiClient();
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
    Response createCat = client.createCat(CatGenerator.catGenerator());
    createCat.prettyPrint();
    Integer id = createCat.jsonPath().getInt("id");
    Response deleteCat = client.deleteCatByID(id);
    assertEquals(204 , deleteCat.statusCode(),"Удаление кота прошло не успешно");

}
  @DisplayName("Удаление кота по несуществующему ид с типом int")
  @ParameterizedTest
  @ValueSource(ints = {-2, 0, 1000, 99999999})
  public void deleteCatNotValidIdTest(int id) {
    Response deleteResponse = client.deleteCatByID(id);
    assertEquals(404, deleteResponse.statusCode());
  }

  @DisplayName("Удаление кота с невалидными значениями с пустой, null ")
 // @ParameterizedTest
//  @EmptySource // используется для объектов которые могут содержать стринг или самих стрингов
 // @NullSource // используется для объектов которые могут содержать стринг или самих стрингов и для других ссылочных типов данных
  public void deleteCatNotIntIdTest(Integer id){
    // Response createCatResponse = client.createCat(CatGenerator.generateBaseCat("Tom", 5, "red", "street", 5.5, true, "10.11.2020", "tom@mail.ru"));
    Response deleteResponse = client.deleteCatByID(id);
    assertEquals(404, deleteResponse.statusCode());
  }

  @DisplayName("Удаление уже удаленного кота")
  @Test
  public void deleteCatTwice(){
  int id =  client.createCat(CatGenerator.catGenerator()).jsonPath().getInt("id");
  assertEquals( 204, client.deleteCatByID(id).statusCode());
  assertEquals( 404, client.deleteCatByID(id).statusCode());

  }


}
