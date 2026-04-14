CREATE MATERIALIZED VIEW accommodation_statistics_view AS
SELECT
    a.category,
    COUNT(a.id) as total_accommodations,
    SUM(a.num_rooms) as total_rooms,
    ROUND(AVG(a.num_rooms)::numeric, 2) as avg_rooms_per_accommodation
FROM accommodations a
GROUP BY a.category;

CREATE UNIQUE INDEX idx_accommodation_statistics_view_category
    ON accommodation_statistics_view (category);

