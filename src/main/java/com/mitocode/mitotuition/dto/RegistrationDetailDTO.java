package com.mitocode.mitotuition.dto;




import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDetailDTO {


    private Integer id;

    @NotNull(message = "Course is required")
    private CourseDTO course;

    @NotEmpty(message = "Classroom is required")
    private String classroom;



}