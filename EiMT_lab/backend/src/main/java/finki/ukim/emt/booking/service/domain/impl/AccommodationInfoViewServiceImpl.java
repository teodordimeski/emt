package finki.ukim.emt.booking.service.domain.impl;

import finki.ukim.emt.booking.model.views.AccommodationInfoView;
import finki.ukim.emt.booking.repository.AccommodationInfoViewRepository;
import finki.ukim.emt.booking.service.domain.AccommodationInfoViewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationInfoViewServiceImpl implements AccommodationInfoViewService {
    private final AccommodationInfoViewRepository accommodationInfoViewRepository;

    public AccommodationInfoViewServiceImpl(AccommodationInfoViewRepository accommodationInfoViewRepository) {
        this.accommodationInfoViewRepository = accommodationInfoViewRepository;
    }

    @Override
    public List<AccommodationInfoView> findAll() {
        return accommodationInfoViewRepository.findAll();
    }
}

