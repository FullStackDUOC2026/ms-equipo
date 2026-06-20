package ms_equipos.ms_equipos.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "Equipos")
@Builder
public class Equipos {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @Column (name = "Marca", nullable = false, length = 20)
    private String marca;
    @Column (name = "fecha ingreso", nullable = false, length = 50)
    private Date fecha_ingreso;
    @Column (name = "Descripcion", nullable = false, length = 100)
    private String descripcion;
    @Column(name= "Estado", nullable = false)
    private boolean estado;
}
