package br.com.infnet.missioncontrol.service;


import br.com.infnet.missioncontrol.model.Astronauta;
import br.com.infnet.missioncontrol.model.StatusAstronauta;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class AstronautaService {
    private final ArrayList<Astronauta> astronautas = new ArrayList<>();

    private Long nextId = 1L;


    public List<Astronauta> listarAstronautas(){
        return astronautas;
    }


    public Astronauta buscarPorId(Long id){
        for (Astronauta astronauta : astronautas){
            if (astronauta.getId().equals(id)){
                return astronauta;
            }
        }
        return null;
    }


    public List<Astronauta> buscarPorStatus(StatusAstronauta status){
        ArrayList<Astronauta> resultado = new ArrayList<>();

        for (Astronauta astronauta : astronautas){
            if (astronauta.getStatus().equals(status)){
                resultado.add(astronauta);
            }
        }
        return resultado;
    }

    public List<Astronauta> buscarPorPais(String pais){
        ArrayList<Astronauta> resultado = new ArrayList<>();

        for (Astronauta astronauta : astronautas){
            if (astronauta.getPais().equals(pais)){
                resultado.add(astronauta);
            }
        }
        return resultado;
    }


    public boolean removerAstronauta(Long id){
        boolean removeu = false;
        for (int i = 0 ; i < astronautas.size(); i++){
            if (astronautas.get(i).getId().equals(id)){
                astronautas.remove(i);
                removeu = true;
            }
        }
        return removeu;
    }

    public void cadastrarAstronauta(Astronauta astronauta){
        astronauta.setId(nextId++);
        astronautas.add(astronauta);
    }



}
