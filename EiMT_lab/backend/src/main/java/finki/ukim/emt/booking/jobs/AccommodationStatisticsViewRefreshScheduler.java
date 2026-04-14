package finki.ukim.emt.booking.jobs;

import finki.ukim.emt.booking.repository.AccommodationStatisticsViewRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccommodationStatisticsViewRefreshScheduler {
    private final AccommodationStatisticsViewRepository accommodationStatisticsViewRepository;

    public AccommodationStatisticsViewRefreshScheduler(
            AccommodationStatisticsViewRepository accommodationStatisticsViewRepository) {
        this.accommodationStatisticsViewRepository = accommodationStatisticsViewRepository;
    }

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void refreshAccommodationStatisticsView() {
        log.info("Refreshing ACCOMMODATION_STATISTICS_VIEW...");
        accommodationStatisticsViewRepository.refresh();
        log.info("ACCOMMODATION_STATISTICS_VIEW successfully refreshed.");
    }
}
