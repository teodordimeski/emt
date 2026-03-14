package mk.ukim.finki.emt.lab1_grb.model.dto;

import mk.ukim.finki.emt.lab1_grb.model.domain.Country;
import mk.ukim.finki.emt.lab1_grb.model.domain.Host;

public record CreateHostDto(
        String name,
        String surname,
        Long countryId
) {

    public Host toHost(Country country) {
        return new Host(name, surname, country);
    }

}