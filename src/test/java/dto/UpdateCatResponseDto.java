package dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateCatResponseDto {

        private int id;
        private String name;
        private int age;
        private String color;
        private String breed;
        private double weight;
        private boolean vaccinated;
        private String birthDate;
        private String ownerEmail;
        private String createdAt;
        private String updatedAt;
    }

