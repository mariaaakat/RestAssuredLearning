package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CatGenerator {
    public static Map<String, Object> catGenerator(){
        Map<String, Object> body = new HashMap<>();
        String randomCat = "Cat_" + UUID.randomUUID().toString().substring(0, 5);
        body.put("name", randomCat);
        body.put("age", 3);
        body.put("color", "BLACK");
        body.put("breed", "Siamese");
        body.put("weight", 3.2);
        body.put("vaccinated", true);
        body.put("birthDate", "2022-01-10");
        body.put("ownerEmail", randomCat.toLowerCase() + "@mail.ru");
        return body;
    }
}
