package finki.ukim.emt.booking.service.domain;

import finki.ukim.emt.booking.model.views.AccommodationStatisticsView;

import java.util.List;

public interface AccommodationStatisticsViewService {
    List<AccommodationStatisticsView> findAll();
}

