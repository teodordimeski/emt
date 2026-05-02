import { useCallback, useEffect, useState } from 'react';
import accommodationApi from '../api/accommodationApi';
import type { AccommodationDetails } from '../api/types/accommodation';

const useAccommodationDetails = (id?: string) => {
  const [accommodationDetails, setAccommodationDetails] = useState<AccommodationDetails | null>(null);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<Error | null>(null);

  const fetch = useCallback(async () => {
    if (!id) {
      return;
    }

    setLoading(true);

    try {
      const response = await accommodationApi.findById(id);
      setAccommodationDetails(response.data);
      setError(null);
    } catch (err) {
      setError(err instanceof Error ? err : new Error('An unknown error occurred.'));
    } finally {
      setLoading(false);
    }
  }, [id]);

  useEffect(() => {
    void fetch();
  }, [fetch]);

  return { accommodationDetails, loading, error };
};

export default useAccommodationDetails;

