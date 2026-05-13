package tests;

import client.CatApiClient;
import dto.CreateCatResponseDto;
import dto.UpdateCatRequestDTO;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.CatGenerator;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateCatTest {
    private static final CatApiClient client = new CatApiClient();
    private Integer id;

    @BeforeEach
    public void createCat() {
        CreateCatResponseDto createResponse = client.createCat(CatGenerator.createDefaultCat());
        id = createResponse.getId();
        assertNotNull(id);
    }

    @AfterEach
    public void deleteCat(){
        client.deleteCatByID(id).then()
                .statusCode(204);

    }

    @DisplayName("Обновление имени кота")
    @Test
    public void updateCatNameTest(){
        String before = client.getCatById(id).jsonPath().getString("name");
        UpdateCatRequestDTO updateNameDto = UpdateCatRequestDTO.builder().name("Tom").build();
        Response updateResponse = client.updateCatPath(updateNameDto, id);
        String after = client.getCatById(id).jsonPath().getString("name");
        assertEquals(200, updateResponse.statusCode());
        assertNotEquals(before, after);
        assertEquals("Tom", after);

    }
    /**
     * 1.Обновление имени кота
     * 2.Обновление возраста кота
     * 3.Обновление цвета кота
     * 4.Обновление породы кота
     * 5.Обновление веса кота
     * 6.Обновление наличие прививок кота
     * 7.Обновление дня рождения кота
     * 8.Обновление почты владельца кота
     * 9.Обновление всех полей у кота
     * 10.Обновление имени кота негативное , проверка пустого значения
     * 11.Обновление имени кота негативное null
     * 11.Обновление имени кота много символов имени
     * 12.Обновление невалидного возраста кота
     */


    @DisplayName("Обновление возраста кота валидное значение")
    @Test
    public void updateCatAgeVTest(){
        int before = client.getCatById(id).jsonPath().getInt("age");
        UpdateCatRequestDTO updateAgeDto = UpdateCatRequestDTO.builder().age(5).build();
        Response updateResponse = client.updateCatPath(updateAgeDto, id);
        int after = client.getCatById(id).jsonPath().getInt("age");
        assertEquals(200, updateResponse.statusCode());
        assertNotEquals(before, after);
        assertEquals(5, after);

    }

    @DisplayName("Обновление цвета кота")
    // TODO завести баг
    @Test
    public void updateCatColorTest(){
        String before = client.getCatById(id).jsonPath().getString("color");
        UpdateCatRequestDTO updateColorDto = UpdateCatRequestDTO.builder().color("RED").build();
        Response updateResponse = client.updateCatPath(updateColorDto, id);
        String after = client.getCatById(id).jsonPath().getString("color");
        assertEquals(200, updateResponse.statusCode());
        assertNotEquals(before, after);
        assertEquals("RED", after);
    }

    @DisplayName("Обновление породы кота")
    @Test
    public void updateCatBreedTest(){
        String before = client.getCatById(id).jsonPath().getString("breed");
        UpdateCatRequestDTO updateBreedDto = UpdateCatRequestDTO.builder().breed("Sphynx").build();
        Response updateResponse = client.updateCatPath(updateBreedDto, id);
        String after = client.getCatById(id).jsonPath().getString("breed");
        assertNotEquals(before, after);
        assertEquals("Sphynx", after);
        assertEquals(200, updateResponse.statusCode());
    }

    @DisplayName("Обновление веса кота валидное значение")
    @Test
    public void updateCatWeightVTest(){
        double before = client.getCatById(id).jsonPath().getDouble("weight");
        UpdateCatRequestDTO updateWeightDto = UpdateCatRequestDTO.builder().weight(6.3).build();
        Response updateResponse = client.updateCatPath(updateWeightDto, id);
        double after = client.getCatById(id).jsonPath().getDouble("weight");
        assertNotEquals(before, after);
        assertEquals(6.3, after);
        assertEquals(200, updateResponse.statusCode());
    }

    @DisplayName("Обновление наличие прививок кота ")
    @Test
    public void updateVaccinatedTest(){
        boolean before = client.getCatById(id).jsonPath().getBoolean("vaccinated");
        UpdateCatRequestDTO updateVaccinatedtDto = UpdateCatRequestDTO.builder().vaccinated(true).build();
        Response updateResponse = client.updateCatPath(updateVaccinatedtDto, id);
        boolean after = client.getCatById(id).jsonPath().getBoolean("vaccinated");
        assertNotEquals(before, after);
        assertEquals(true, after);
        assertEquals(200, updateResponse.statusCode());
    }

    @DisplayName("Обновление дня рождения кота")
    @Test
    public void updateCatBirthDateTest(){
        String before = client.getCatById(id).jsonPath().getString("birthDate");
        UpdateCatRequestDTO updateBirthDateDto = UpdateCatRequestDTO.builder().birthDate("2020-05-30").build();
        Response updateResponse = client.updateCatPath(updateBirthDateDto, id);
        String after = client.getCatById(id).jsonPath().getString("birthDate");
        assertEquals(200, updateResponse.statusCode());
        assertNotEquals(before, after);
        assertEquals("2020-05-30", after);

    }

    @DisplayName("Обновление почты владельца кота")
    @Test
    public void updateCatOwnerEmailTest(){
        String before = client.getCatById(id).jsonPath().getString("ownerEmail");
        UpdateCatRequestDTO updateOwnerEmaileDto = UpdateCatRequestDTO.builder().ownerEmail("cat@mail.ru").build();
        Response updateResponse = client.updateCatPath(updateOwnerEmaileDto, id);
        String after = client.getCatById(id).jsonPath().getString("ownerEmail");
        assertEquals(200, updateResponse.statusCode());
        assertNotEquals(before, after);
        assertEquals("cat@mail.ru", after);

    }

    @DisplayName("Обновление всех полей кота")
    @Test
    public void updateAllCatTest(){
        String beforeN = client.getCatById(id).jsonPath().getString("name");
        UpdateCatRequestDTO allCatDto = UpdateCatRequestDTO.builder()
             .name("Bim")
             .age(9)
             //.color("White")  // баг на поле
             .breed("Persian")
             .weight(7.1)
             .vaccinated(true)
             .birthDate("2017-03-30")
             .ownerEmail("CatWhite@mail.ru")
             .build();
        Response updateResponse = client.updateCatPath(allCatDto, id);
        String afterN = client.getCatById(id).jsonPath().getString("name");
        assertEquals(200, updateResponse.statusCode(), "Ответ должен иметь статус 200");
        assertNotEquals(beforeN, afterN);
        assertEquals("Bim", afterN);

    }


    @DisplayName("Обновление имени кота пустое значение")
    @Test
    public void updateCatEmptyNameTest(){
        UpdateCatRequestDTO updateNameDto = UpdateCatRequestDTO.builder().name("").build();
        Response updateResponse = client.updateCatPath(updateNameDto, id);
        assertEquals(400, updateResponse.statusCode());
    }
    @DisplayName("Обновление имени кота null значение")
    // TODO завести баг, поле не должно принимать null
    @Test
    public void updateCatNullNameTest(){
        UpdateCatRequestDTO updateNameDto = UpdateCatRequestDTO.builder().name(null).build();
        Response updateResponse = client.updateCatPath(updateNameDto, id);
        assertEquals(400, updateResponse.statusCode());
    }

    @DisplayName("Обновление имени кота много символов в имени")
    @Test
    public void updateCatBigNameTest(){
        UpdateCatRequestDTO updateNameDto = UpdateCatRequestDTO.builder().name("Tomaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaas").build();
        Response updateResponse = client.updateCatPath(updateNameDto, id);
        assertEquals(400, updateResponse.statusCode());
    }
    @DisplayName("Обновление  полей типа строка c пустым значением")
    @Test
    public void updateStringCatEmptyTest(){
        UpdateCatRequestDTO allCatDto = UpdateCatRequestDTO.builder()
                .name("")
                .color("")
                .breed("")
                .birthDate("")
                .ownerEmail("")
                .build();
        Response updateResponse = client.updateCatPath(allCatDto, id);
        assertEquals(400, updateResponse.statusCode());

    }

    @DisplayName("Изменение возраста кота негативный")
    @ParameterizedTest
    @ValueSource(ints = {-2, 99999999})
    public void updateCatNotValidAgeTest(int age) {
        UpdateCatRequestDTO updateAgeDto = UpdateCatRequestDTO.builder().age(age).build();
        Response updateResponse = client.updateCatPath(updateAgeDto, id);
        assertEquals(400, updateResponse.statusCode());
    }

}
