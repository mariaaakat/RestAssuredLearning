package dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateCatResponseDto {
    private Integer id;
    private String name;
    private Integer age;
    private String color;
    private String breed;
    private Double weight;
    private Boolean vaccinated;
    private String birthDate;
    private String ownerEmail;
    private String status;
    private String createdAt;
    private String updatedAt;
}
