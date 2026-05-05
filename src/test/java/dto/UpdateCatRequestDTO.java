package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateCatRequestDTO {
    public class UpdateCatRequestDto {
        private int id;
        private String name;
        private int age;
        private String color;
        private String breed;
        private double weight;
        private boolean vaccinated;
        private String birthDate;
        private String ownerEmail;
    }
}
