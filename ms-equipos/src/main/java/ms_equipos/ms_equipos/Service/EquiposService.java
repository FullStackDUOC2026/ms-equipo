package ms_equipos.ms_equipos.Service;


import ms_equipos.ms_equipos.DTO.EquiposDTO;
import ms_equipos.ms_equipos.Entity.Equipos;
import ms_equipos.ms_equipos.Repository.EquiposRepository;
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
                .marca(equiposDTO.getMarca())
                .descripcion(equiposDTO.getDescripcion())
                .fecha_ingreso(equiposDTO.getFecha_ingreso())
                .build();


        Equipos saved = equiposRepository.save(entity);

        EquiposDTO response = EquiposDTO.builder()
                .codigo(saved.getCodigo())
                .fecha_ingreso(saved.getFecha_ingreso())
                .marca(saved.getMarca())
                .descripcion(saved.getDescripcion())
                .build();
        return response;
    }

    public List<EquiposDTO> getAll (){

        List<Equipos> listaEquipos = equiposRepository.findAll();

        return  listaEquipos.stream().map(equipos->EquiposDTO.builder()
                .codigo(equipos.getCodigo())
                .marca(equipos.getMarca())
                .fecha_ingreso(equipos.getFecha_ingreso())
                .descripcion(equipos.getDescripcion())
                .build())
                .toList();

    }

    public EquiposDTO getById (Integer codigo){

        Equipos entity = equiposRepository.findById(codigo).orElseThrow(() ->
                new RuntimeException("Error message"));

        return EquiposDTO.builder()
                .codigo(entity.getCodigo())
                .marca(entity.getMarca())
                .fecha_ingreso(entity.getFecha_ingreso())
                .descripcion(entity.getDescripcion())
                .build();
    }

    public EquiposDTO update (Integer codigo, EquiposDTO equiposDTO){

        Equipos entity = equiposRepository.findById(codigo).orElseThrow(() ->
                new RuntimeException("Error message"));
        entity.setMarca(equiposDTO.getMarca());
        entity.setFecha_ingreso(equiposDTO.getFecha_ingreso());
        entity.setDescripcion(equiposDTO.getDescripcion());

        Equipos update = equiposRepository.save(entity);

        return  EquiposDTO.builder()
                .marca(update.getMarca())
                .fecha_ingreso(update.getFecha_ingreso())
                .descripcion(update.getDescripcion())
                .build();
    }

    public Boolean delete (Integer id) {

        if (equiposRepository.existsById(id)){

            equiposRepository.deleteById(id);
            return true;
        }
        return false;
    }


        public EquiposDTO asignarEquipo(Integer cod, Integer idUsuario) {
            Equipos equipo = equiposRepository.findById(cod)
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

            if (!equipo.getEstado().equals("LIBRE")) {
                throw new RuntimeException("Equipo no disponible");
            }

            equipo.setEstado("OCUPADO");

            equiposRepository.save(equipo);

           return EquiposDTO.builder()
                    .codigo(equipo.getCodigo())
                    .marca(equipo.getMarca())
                    .fecha_ingreso(equipo.getFecha_ingreso())
                    .descripcion(equipo.getDescripcion())
                    .estado(equipo.getEstado())
                    .build();
        }

        public EquiposDTO liberarEquipo(Integer codigo) {
            Equipos equipo = equiposRepository.findById(codigo)
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

            equipo.setEstado("LIBRE");

            equiposRepository.save(equipo);

            return EquiposDTO.builder()
                    .codigo(equipo.getCodigo())
                    .marca(equipo.getMarca())
                    .fecha_ingreso(equipo.getFecha_ingreso())
                    .descripcion(equipo.getDescripcion())
                    .estado(equipo.getEstado())
                    .build();
        }

        public EquiposDTO enviarMantencion(Integer codigo) {
            Equipos equipo = equiposRepository.findById(codigo)
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

            equipo.setEstado("MANTENCION");

            equiposRepository.save(equipo);

            return EquiposDTO.builder()
                    .codigo(equipo.getCodigo())
                    .marca(equipo.getMarca())
                    .fecha_ingreso(equipo.getFecha_ingreso())
                    .descripcion(equipo.getDescripcion())
                    .estado(equipo.getEstado())
                    .build();
        }
    }




