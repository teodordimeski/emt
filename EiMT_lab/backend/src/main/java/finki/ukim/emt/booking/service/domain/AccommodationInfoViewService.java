package finki.ukim.emt.booking.service.domain;

import finki.ukim.emt.booking.model.views.AccommodationInfoView;

import java.util.List;

public interface AccommodationInfoViewService {
    List<AccommodationInfoView> findAll();
}

