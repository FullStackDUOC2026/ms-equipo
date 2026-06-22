package ms_equipos.ms_equipos.Controller;


import ms_equipos.ms_equipos.DTO.EquiposDTO;

import ms_equipos.ms_equipos.Service.EquiposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")
public class EquiposController {

    @Autowired
    private EquiposService equiposService;

    @GetMapping
    public List<EquiposDTO> listarTodo() {
        return equiposService.getAll();
    }

    @GetMapping("/{codigo}")
    public EquiposDTO buscarPorCodigo(@PathVariable Long codigo) {
        return equiposService.getById(codigo);
    }

    @PostMapping
    public EquiposDTO agregarEquipos(@RequestBody EquiposDTO equiposDTO) {
        return equiposService.save(equiposDTO);
    }

    @PutMapping("/{codigo}")
    public EquiposDTO actualizarEquipos(@PathVariable Long codigo, @RequestBody EquiposDTO equiposDTO) {
        return equiposService.update(codigo, equiposDTO);
    }

    @DeleteMapping("/{codigo}")
    public String eliminarEquipos(@PathVariable Long codigo) {
        return equiposService.delete(codigo) ? "Equipo eliminado" : "Equipo no encontrado";
    }


    @PutMapping("/{codigo}/asignar/{usuarioId}")
    public ResponseEntity<EquiposDTO> asignar(@PathVariable Long codigo, @PathVariable Long usuarioId) {
        return ResponseEntity.ok(equiposService.asignarEquipo(codigo, usuarioId));
    }

    @PutMapping("/{codigo}/liberar")
    public ResponseEntity<EquiposDTO> liberar(@PathVariable Long codigo) {
        return ResponseEntity.ok(equiposService.liberarEquipo(codigo));
    }

    @PutMapping("/{codigo}/mantencion")
    public ResponseEntity<EquiposDTO> mantencion(@PathVariable Long codigo) {
        return ResponseEntity.ok(equiposService.enviarMantencion(codigo));
    }
}