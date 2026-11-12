
package com.nutrivida.servicioservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 12)
    private String run;

    @Column(nullable = false, length = 50)
    private String pnombre;

    @Column(length = 50)
    private String snombre;

    @Column(nullable = false, length = 50)
    private String apaterno;

    @Column(length = 50)
    private String amaterno;

    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(length = 300)
    private String direccion;
}
