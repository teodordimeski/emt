package finki.ukim.emt.booking.service.domain.impl;

import finki.ukim.emt.booking.model.views.AccommodationStatisticsView;
import finki.ukim.emt.booking.repository.AccommodationStatisticsViewRepository;
import finki.ukim.emt.booking.service.domain.AccommodationStatisticsViewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationStatisticsViewServiceImpl implements AccommodationStatisticsViewService {
    private final AccommodationStatisticsViewRepository accommodationStatisticsViewRepository;

    public AccommodationStatisticsViewServiceImpl(AccommodationStatisticsViewRepository accommodationStatisticsViewRepository) {
        this.accommodationStatisticsViewRepository = accommodationStatisticsViewRepository;
    }

    @Override
    public List<AccommodationStatisticsView> findAll() {
        return accommodationStatisticsViewRepository.findAll();
    }
}

