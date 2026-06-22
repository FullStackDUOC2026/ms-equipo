package ms_equipos.ms_equipos.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ms_equipos.ms_equipos.enums.EstadoEquipo;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Equipos")
@Builder
public class Equipos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "Marca", nullable = false, length = 50)
    private String tipo;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_ingreso", nullable = false)
    private Date fechaIngreso;

    @Column(name = "Descripcion", nullable = false, length = 100)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "Estado", nullable = false)
    private EstadoEquipo estado = EstadoEquipo.LIBRE;
}
