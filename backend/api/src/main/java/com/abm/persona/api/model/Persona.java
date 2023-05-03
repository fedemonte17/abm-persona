package com.abm.persona.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "numero_documento")
    private Integer numeroDocumento;

    @Column(name = "fecha_nacimiento")
    private Instant fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento")
    @NotNull
    private TipoDocumento tipoDocumento;

}
