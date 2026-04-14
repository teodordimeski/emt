CREATE VIEW accommodation_info_view AS
SELECT
    a.id,
    a.name,
    a.category,
    a.num_rooms,
    a.rented,
    h.name as host_name,
    h.surname as host_surname,
    c.name as country_name,
    a.created_at
FROM accommodations a
JOIN hosts h ON a.host_id = h.id
JOIN countries c ON h.country_id = c.id;
