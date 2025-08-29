package com.mitocode.mitotuition.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "registration_detail")
public class RegistrationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_registration", nullable = false, foreignKey = @ForeignKey(name = "FK_DETAIL_REGISTRATION"))
    private Registration registration;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false, foreignKey = @ForeignKey(name = "FK_DETAIL_COURSE"))
    private Course course;

    @Column(nullable = false, length = 50)
    private String classroom;
}