package com.mitocode.mitotuition.dto;




import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {

    private Integer id;

    private LocalDateTime registrationDate;

    @NotNull(message = "Student is required")
    private StudentDTO student;

    @Valid
    @NotNull(message = "At least one registration detail is required")
    private List<RegistrationDetailDTO> details;

    private Boolean status;


}