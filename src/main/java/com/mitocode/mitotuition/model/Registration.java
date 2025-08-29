package com.mitocode.mitotuition.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false, foreignKey = @ForeignKey(name = "FK_REGISTRATION_STUDENT"))
    private Student student;

    @Column(nullable = false)
    private Boolean status = true;

    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistrationDetail> details;
}