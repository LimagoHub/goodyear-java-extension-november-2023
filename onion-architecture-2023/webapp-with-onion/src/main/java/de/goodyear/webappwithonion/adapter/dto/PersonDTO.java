package de.goodyear.webappwithonion.adapter.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@XmlRootElement
public class PersonDTO {


    @NotNull
    private UUID id;


    @NotBlank
    @Size(min=2, max = 30)
    private String vorname;

    @NotBlank
    @Size(min=2, max = 30)
    private String nachname;

}
