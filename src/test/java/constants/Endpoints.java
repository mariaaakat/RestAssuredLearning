package constants;

public class Endpoints {
    public static final String CATS = "/api/v1/cats";
    public static final String CAT_BY_ID = "/api/v1/cats/{id}";//Чтобы использовать эндпоинт с параметром, нужно в методе ApiClient указать .pathParam("id", id)-вставляется прям в гивен запрос

}
