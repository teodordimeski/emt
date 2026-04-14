package finki.ukim.emt.booking.service.domain.impl;

import finki.ukim.emt.booking.model.domain.Accommodation;
import finki.ukim.emt.booking.model.enums.Category;
import finki.ukim.emt.booking.model.events.AccommodationRentedEvent;
import finki.ukim.emt.booking.model.projection.LongAccommodationProjection;
import finki.ukim.emt.booking.model.projection.ShortAccommodationProjection;
import finki.ukim.emt.booking.repository.AccommodationRepository;
import finki.ukim.emt.booking.service.domain.AccommodationService;
import java.util.List;
import java.util.Optional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final ApplicationEventPublisher eventPublisher;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository,
                                    ApplicationEventPublisher eventPublisher) {
        this.accommodationRepository = accommodationRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> findWithHostAndCountryById(Long id) {
        return accommodationRepository.findWithHostAndCountryById(id);
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Page<Accommodation> findAllWithFilters(Category category, Long hostId,
                                                  String countryName, Integer minRooms,
                                                  Boolean hasAvailableRooms, Pageable pageable) {
        return accommodationRepository.findAllWithFilters(
                category, hostId, countryName, minRooms, hasAvailableRooms, pageable);
    }

    @Override
    public Page<ShortAccommodationProjection> findAllShortProjections(Pageable pageable) {
        return accommodationRepository.findAllShortProjections(pageable);
    }

    @Override
    public Accommodation create(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accommodationRepository
                .findById(id)
                .map(existing -> {
                    existing.setName(accommodation.getName());
                    existing.setCategory(accommodation.getCategory());
                    existing.setCondition(accommodation.getCondition());
                    existing.setHost(accommodation.getHost());
                    existing.setNumRooms(accommodation.getNumRooms());
                    return accommodationRepository.save(existing);
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
        return accommodationRepository
                .findById(id)
                .map(existing -> {
                    existing.setRented(true);
                    Accommodation saved = accommodationRepository.save(existing);
                    eventPublisher.publishEvent(new AccommodationRentedEvent(saved.getId(), saved.getName()));
                    return saved;
                });
    }

    @Override
    public List<LongAccommodationProjection> findAllLongProjections() {
        return accommodationRepository.findAllWithIdNameCategorNumRoomsHostNameHostSurnameAndHostCountry();
    }
}
