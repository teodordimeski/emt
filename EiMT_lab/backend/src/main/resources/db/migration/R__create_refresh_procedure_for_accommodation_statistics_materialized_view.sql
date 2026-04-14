CREATE OR REPLACE PROCEDURE refresh_accommodation_statistics_view()
LANGUAGE sql
AS $$
    REFRESH MATERIALIZED VIEW CONCURRENTLY accommodation_statistics_view;
$$;

