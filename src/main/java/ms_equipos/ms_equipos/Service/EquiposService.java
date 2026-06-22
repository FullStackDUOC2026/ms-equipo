package ms_equipos.ms_equipos.Service;


import ms_equipos.ms_equipos.DTO.EquiposDTO;
import ms_equipos.ms_equipos.Entity.Equipos;
import ms_equipos.ms_equipos.Repository.EquiposRepository;
import ms_equipos.ms_equipos.enums.EstadoEquipo;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class EquiposService {


    private EquiposRepository equiposRepository;

    public EquiposService (EquiposRepository equiposRepository){
        this.equiposRepository = equiposRepository;
    }

    public EquiposDTO save (EquiposDTO equiposDTO){
        Equipos entity = Equipos.builder()
                .tipo(equiposDTO.getTipo())
                .descripcion(equiposDTO.getDescripcion())
                .fechaIngreso(equiposDTO.getFechaIngreso())
                .estado(ms_equipos.ms_equipos.enums.EstadoEquipo.LIBRE)
                .build();



        Equipos saved = equiposRepository.save(entity);

        EquiposDTO response = EquiposDTO.builder()
                .codigo(saved.getCodigo())
                .fechaIngreso(saved.getFechaIngreso())
                .tipo(saved.getTipo())
                .descripcion(saved.getDescripcion())
                .estado(saved.getEstado())
                .build();
        return response;
    }

    public List<EquiposDTO> getAll (){

        List<Equipos> listaEquipos = equiposRepository.findAll();

        return  listaEquipos.stream().map(equipos->EquiposDTO.builder()
                .codigo(equipos.getCodigo())
                .tipo(equipos.getTipo())
                .fechaIngreso(equipos.getFechaIngreso())
                .descripcion(equipos.getDescripcion())
                        .estado(equipos.getEstado())
                .build())
                .toList();

    }

    public EquiposDTO getById (Long codigo){

        Equipos entity = equiposRepository.findById(codigo).orElseThrow(() ->
                new RuntimeException("Error message"));

        return EquiposDTO.builder()
                .codigo(entity.getCodigo())
                .tipo(entity.getTipo())
                .fechaIngreso(entity.getFechaIngreso())
                .descripcion(entity.getDescripcion())
                .estado(entity.getEstado())
                .build();
    }

    public EquiposDTO update (Long codigo, EquiposDTO equiposDTO){

        Equipos entity = equiposRepository.findById(codigo).orElseThrow(() ->
                new RuntimeException("Error message"));
        entity.setTipo(equiposDTO.getTipo());
        entity.setFechaIngreso(equiposDTO.getFechaIngreso());
        entity.setDescripcion(equiposDTO.getDescripcion());


        Equipos update = equiposRepository.save(entity);

        return EquiposDTO.builder()
                .codigo(update.getCodigo())
                .tipo(update.getTipo())
                .fechaIngreso(update.getFechaIngreso())
                .descripcion(update.getDescripcion())
                .estado(update.getEstado())
                .build();

    }

    public Boolean delete (Long id) {

        if (equiposRepository.existsById(id)) {

            equiposRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public EquiposDTO asignarEquipo(Long codigo, Long idUsuario) {
        Equipos equipo = equiposRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        if (equipo.getEstado() != EstadoEquipo.LIBRE) {
            throw new RuntimeException("Equipo no disponible, estado actual: " + equipo.getEstado());
        }

        equipo.setEstado(EstadoEquipo.OCUPADO);
        equiposRepository.save(equipo);
        return EquiposDTO.builder()
                .codigo(equipo.getCodigo())
                .tipo(equipo.getTipo())
                .fechaIngreso(equipo.getFechaIngreso())
                .descripcion(equipo.getDescripcion())
                .estado(equipo.getEstado())
                .build();
    }

    public EquiposDTO liberarEquipo(Long codigo) {
        Equipos equipo = equiposRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        equipo.setEstado(EstadoEquipo.LIBRE);
        equiposRepository.save(equipo);
        return EquiposDTO.builder()
                .codigo(equipo.getCodigo())
                .tipo(equipo.getTipo())
                .fechaIngreso(equipo.getFechaIngreso())
                .descripcion(equipo.getDescripcion())
                .estado(equipo.getEstado())
                .build();
    }

    public EquiposDTO enviarMantencion(Long codigo) {
        Equipos equipo = equiposRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        equipo.setEstado(EstadoEquipo.MANTENCION);
        equiposRepository.save(equipo);

        return EquiposDTO.builder()
                .codigo(equipo.getCodigo())
                .tipo(equipo.getTipo())
                .fechaIngreso(equipo.getFechaIngreso())
                .descripcion(equipo.getDescripcion())
                .estado(equipo.getEstado())
                .build();
    }
    }




