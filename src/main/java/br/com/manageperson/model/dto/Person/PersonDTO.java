package br.com.manageperson.model.dto.Person;

import br.com.manageperson.model.dto.Address.AddressViewDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonDTO {

    private Long id;

    @NotNull(message = "The Name is required.")
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "The Bith Date is required.")
    private LocalDate birthDate;

    private List<AddressViewDTO> addresses;

}
