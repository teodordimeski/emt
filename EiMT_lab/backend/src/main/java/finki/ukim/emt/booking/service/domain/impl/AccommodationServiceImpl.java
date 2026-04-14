package finki.ukim.emt.booking.service.domain.impl;

import finki.ukim.emt.booking.model.domain.Accommodation;
import finki.ukim.emt.booking.model.domain.Country;
import finki.ukim.emt.booking.model.domain.Host;
import finki.ukim.emt.booking.model.enums.Category;
import finki.ukim.emt.booking.model.events.AccommodationRentedEvent;
import finki.ukim.emt.booking.model.exception.AccommodationNotAvailableException;
import finki.ukim.emt.booking.model.exception.ResourceNotFoundException;
import finki.ukim.emt.booking.model.projection.LongAccommodationProjection;
import finki.ukim.emt.booking.model.projection.ShortAccommodationProjection;
import finki.ukim.emt.booking.repository.AccommodationRepository;
import finki.ukim.emt.booking.service.domain.AccommodationService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final ApplicationEventPublisher eventPublisher;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, ApplicationEventPublisher eventPublisher) {
        this.accommodationRepository = accommodationRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Accommodation create(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accommodationRepository
                .findById(id)
                .map((existingAccommodation) -> {
                    existingAccommodation.setName(accommodation.getName());
                    existingAccommodation.setCategory(accommodation.getCategory());
                    existingAccommodation.setHost(accommodation.getHost());
                    existingAccommodation.setNumRooms(accommodation.getNumRooms());
                    return accommodationRepository.save(existingAccommodation);
                });
    }

    @Override
    public Optional<Accommodation> delete(Long id) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(id);
        accommodation.ifPresent(accommodationRepository::delete);
        return accommodation;
    }

    @Override
    public Accommodation rent(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Accommodation with id %d is not found!", id)));

        if(accommodation.getRented()) {
            throw new AccommodationNotAvailableException(id);
        }
        accommodation.setRented(true);
        Accommodation savedAccommodation = accommodationRepository.save(accommodation);
        
        // Publish rental event
        eventPublisher.publishEvent(new AccommodationRentedEvent(this, savedAccommodation));
        
        return savedAccommodation;
    }

    @Override
    public List<Accommodation> findAllByRented(boolean isRented) {
        return accommodationRepository.findAllByRented(isRented);
    }

    @Override
    public List<LongAccommodationProjection> findAllLongProjections() {
        return accommodationRepository.findAllWithIdNameCategorNumRoomsHostNameHostSurnameAndHostCountry();
    }

    @Override
    public List<ShortAccommodationProjection> findAllShortProjections() {
        return accommodationRepository.findAllWithIdNameCategoryAndNumRooms();
    }

    @Override
    public Page<Accommodation> findAllPaged(Category category, Host host, Country hostCountry, int numRooms, boolean hasFreeRooms, int pageNumber, int pageSize) {

        Specification<Accommodation> specification = Specification.allOf(
                FieldFilterSpecification.filterEqualsV(Accommodation.class, "category", category),
                FieldFilterSpecification.filterEqualsV(Accommodation.class, "host.id", host != null ? host.getId() : null),
                FieldFilterSpecification.filterEqualsV(Accommodation.class, "host.country.id", hostCountry != null ? hostCountry.getId() : null),
                FieldFilterSpecification.filterEqualsV(Accommodation.class, "numRooms", numRooms > 0 ? numRooms : null),
                FieldFilterSpecification.filterEqualsV(Accommodation.class, "rented", hasFreeRooms ? false : null)
        );

        return accommodationRepository.findAll(specification,
                PageRequest.of(pageNumber, pageSize,
                        Sort.by(Sort.Direction.ASC, "name")
                                .and(Sort.by(Sort.Direction.ASC, "createdAt"))));
    }



}
