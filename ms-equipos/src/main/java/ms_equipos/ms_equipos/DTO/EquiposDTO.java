package ms_equipos.ms_equipos.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ms_equipos.ms_equipos.enums.EstadoEquipo;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquiposDTO {

    private Long codigo;
    private String tipo;
    private Date fechaIngreso;
    private String descripcion;
    private EstadoEquipo estado;
}

