package br.com.infnet.missioncontrol.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@ToString


public class Astronauta {
    private Long id;
    @NotBlank(message = "Nome e obrigatorio")
    private String nome;
    @NotBlank(message = "Pais e obrigatorio")
    private String pais;
    @NotNull(message = "Idade e obrigatorio") @Min(value = 18,message = "Idade tem que ser maior que 18")
    private Integer idade;
    @NotNull(message = "AnosExperiencia e obrigatorio") @Min(value = 0,message = "Valores abaixo de 0 nao sao permitidos")
    private Integer anosExperiencia;
    @NotNull(message = "Status e obrigatorioa")
    private StatusAstronauta status;


}
/*
@NotNull
@NotEmpty
@NotBlank

Min(3)
Max()
Size
Pattern
 */