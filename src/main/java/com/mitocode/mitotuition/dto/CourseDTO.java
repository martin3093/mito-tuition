package com.mitocode.mitotuition.dto;



import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Integer id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Acronym is required")
    private String acronym;

    private Boolean status;

}