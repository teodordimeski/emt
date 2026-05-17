import { useCallback, useEffect, useState } from 'react';
import accommodationApi from '../api/accommodationApi';
import type { AccommodationDetails } from '../api/types/accommodation';
import useSnackbar from './useSnackbar';

const useAccommodationDetails = (id?: string) => {
  const { showSnackbar } = useSnackbar();
  const [accommodationDetails, setAccommodationDetails] = useState<AccommodationDetails | null>(null);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<Error | null>(null);

  const fetch = useCallback(async () => {
    if (!id) {
      return;
    }

    setLoading(true);

    try {
      const response = await accommodationApi.findWithDetailsById(id);
      setAccommodationDetails(response.data);
      setError(null);
    } catch (err) {
      const message = err instanceof Error ? err.message : 'An unknown error occurred.';
      setError(err instanceof Error ? err : new Error(message));
      showSnackbar(message, 'error');
    } finally {
      setLoading(false);
    }
  }, [id, showSnackbar]);

  useEffect(() => {
    void fetch();
  }, [fetch]);

  return { accommodationDetails, loading, error };
};

export default useAccommodationDetails;
