package br.com.manageperson.model.dto.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressViewDTO {

    private Long id;

    private String street;

    private Integer number;

    private String cep;

    private String city;

    private Boolean mainAddress;

}
