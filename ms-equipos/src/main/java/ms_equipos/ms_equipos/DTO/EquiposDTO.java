package ms_equipos.ms_equipos.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquiposDTO {

    private Integer codigo;
    private String marca;
    private Date fecha_ingreso;
    private String descripcion;
}
