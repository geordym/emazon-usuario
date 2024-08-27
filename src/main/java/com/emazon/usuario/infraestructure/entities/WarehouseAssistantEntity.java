package com.emazon.usuario.infraestructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "warehouse_assistants")
public class WarehouseAssistantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "identity_document", nullable = false, unique = true)
    private String identityDocument;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private UserEntity user;

    // Constructor vacío
    public WarehouseAssistantEntity() {
    }

    // Constructor con parámetros
    public WarehouseAssistantEntity(String firstName, String lastName, String identityDocument, String phoneNumber,
                                    String email, LocalDate birthDate, UserEntity user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityDocument = identityDocument;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.user = user;
    }
}