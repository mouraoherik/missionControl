package br.com.infnet.missioncontrol.controller;

import br.com.infnet.missioncontrol.model.Astronauta;
import br.com.infnet.missioncontrol.model.ErrorResponse;
import br.com.infnet.missioncontrol.model.Missao;
import br.com.infnet.missioncontrol.model.StatusAstronauta;
import br.com.infnet.missioncontrol.service.AstronautaService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/astronautas")
public class AstronautaController {
    private final AstronautaService astronautaService;

    public AstronautaController(AstronautaService astronautaService) {this.astronautaService = astronautaService;}

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        Astronauta astronauta = astronautaService.buscarPorId(id);

        if (astronauta != null){
            return ResponseEntity.ok().body(astronauta);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Astronauta> listar(@RequestParam (required = false)StatusAstronauta status,String pais){
        if (status != null && pais == null){
            return astronautaService.buscarPorStatus(status);
        }
        if (pais != null && status == null){
            return astronautaService.buscarPorPais(pais);
        }
        return astronautaService.listarAstronautas();
    }

    @PostMapping
    public ResponseEntity salvar(@Valid @RequestBody Astronauta astronauta){

        for (Astronauta checagem : astronautaService.listarAstronautas()){
            if (astronauta.getNome().equals(checagem.getNome())){
                return ResponseEntity.badRequest().body(new ErrorResponse("Nome ja cadastrado!"));
            }
        }

        astronautaService.cadastrarAstronauta(astronauta);
        return ResponseEntity.status(HttpStatus.CREATED).body(astronauta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        if (id <= 0 ){
            return ResponseEntity.badRequest().body(new ErrorResponse("Id invalido"));
        }
        boolean removeu = astronautaService.removerAstronauta(id);
        if (removeu){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Id nao localizado"));
    }


    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long  id,
                                    @RequestBody Astronauta entrada){
        if(id <= 0) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Id invalido"));
        }

        Astronauta existente = null;
        for(Astronauta astronauta : astronautaService.listarAstronautas()){
            if(astronauta.getId().equals(id)){
                existente = astronauta;
                break;
            }
        }
        if(existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Missao nao localizada"));
        }
        existente.setNome(entrada.getNome());
        existente.setPais(entrada.getPais());
        existente.setIdade(entrada.getIdade());
        existente.setStatus(entrada.getStatus());
        existente.setAnosExperiencia(entrada.getAnosExperiencia());
        return ResponseEntity.ok().body(existente);

    }
}
