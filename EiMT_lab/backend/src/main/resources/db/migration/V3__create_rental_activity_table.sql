CREATE TABLE rental_activities
(
    id                 BIGSERIAL PRIMARY KEY,
    created_at         TIMESTAMP    NOT NULL,
    accommodation_id   BIGINT       NOT NULL REFERENCES accommodations (id) ON DELETE CASCADE,
    accommodation_name VARCHAR(255) NOT NULL,
    activity_type      VARCHAR(255) NOT NULL,
    event_time         TIMESTAMP    NOT NULL
);

CREATE INDEX idx_rental_activities_accommodation_id ON rental_activities (accommodation_id);
CREATE INDEX idx_rental_activities_event_time ON rental_activities (event_time);

