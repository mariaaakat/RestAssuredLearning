package dto;

import lombok.*;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateCatRequestDTO {
       // private Integer id;
        private String name;
        private Integer age;
        private String color;
        private String breed;
        private Double weight;
        private Boolean vaccinated;
        private String birthDate;
        private String ownerEmail;
    }

