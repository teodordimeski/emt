package mk.ukim.finki.emt.lab1_grb.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mk.ukim.finki.emt.lab1_grb.model.domain.Country;
import mk.ukim.finki.emt.lab1_grb.model.domain.Host;

public record CreateHostDto(
        @NotBlank
        String name,
        @NotBlank
        String surname,
        @NotNull
        Long countryId
) {

    public Host toHost(Country country) {
        return new Host(name, surname, country);
    }

}