package ms_equipos.ms_equipos.Controller;


import ms_equipos.ms_equipos.DTO.EquiposDTO;
import ms_equipos.ms_equipos.Service.EquiposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Equipos")
public class EquiposController {



    @Autowired

    private EquiposService equiposService;


    @GetMapping

    public List<EquiposDTO> listarTodo (){

        return equiposService.getAll();
    }

    @GetMapping("/{codigo}")

    public EquiposDTO buscarPorCodigo (@PathVariable Integer codigo){

        return equiposService.getById(codigo);
    }

    @PostMapping

    public EquiposDTO agregarEquipos (@RequestBody EquiposDTO equiposDTO){

        return equiposService.save(equiposDTO);
    }
    @PutMapping ("/{codigo}")
    public EquiposDTO actualizarEquipos (@PathVariable Integer codigo, @RequestBody EquiposDTO equiposDTO) {

        return equiposService.update(codigo,equiposDTO);
    }

    @DeleteMapping ("/{codigo}")

    public String eliminarEquipos (@PathVariable Integer codigo){

        return equiposService.delete(codigo) ? "Equipo eliminado": "Equipo no encontrado";
    }



}
