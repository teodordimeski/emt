package mk.ukim.finki.emt.lab1_grb.service.domain.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.lab1_grb.model.domain.Accommodation;
import mk.ukim.finki.emt.lab1_grb.repository.AccommodationRepository;
import mk.ukim.finki.emt.lab1_grb.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {

    AccommodationRepository accommodationRepository;


    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Accommodation create(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accommodationRepository.findById(id).map(a -> {
            a.setName(accommodation.getName());
            a.setCategory(accommodation.getCategory());
            a.setRooms(accommodation.getRooms());
            a.setHost(accommodation.getHost());
            return accommodationRepository.save(a);
        });
    }

    @Override
    public Optional<Accommodation> deleteById(Long id) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(id);
        accommodation.ifPresent(accommodationRepository::delete);

        return accommodation;
    }

    @Override
    public Optional<Accommodation> markAsRented(Long id) {
        return accommodationRepository.findById(id).map(a -> {
            a.setRented(true);
            return accommodationRepository.save(a);
        });
    }
}
