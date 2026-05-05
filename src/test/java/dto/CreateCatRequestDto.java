package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateCatRequestDto {
    private String name;
    private int age;
    private String color;
    private String breed;
    private double weight;
    private boolean vaccinated;
    @JsonProperty("birthDate")
    private String birthDate;
    private String ownerEmail;
    /**
     * @JsonProperty: Задаёт имя поля в JSON, если оно отличается от имени в Java-классе.
     * @JsonIgnore: Исключает поле из сериализации/десериализации.
     * @JsonFormat: Задаёт формат для полей с датой и временем.
     * @JsonInclude: Определяет правила включения полей, например, пропустить null значения: @JsonInclude(JsonInclude.Include.NON_NULL).
     * @JsonCreator + @JsonProperty(required = true): Используется для классов без конструктора по умолчанию и позволяет пометить поля как обязательные
     */
}
