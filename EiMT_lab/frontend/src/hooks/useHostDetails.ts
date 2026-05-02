import { useCallback, useEffect, useState } from 'react';
import hostApi from '../api/hostApi';
import type { Host } from '../api/types/host';

const useHostDetails = (id?: string) => {
  const [hostDetails, setHostDetails] = useState<Host | null>(null);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<Error | null>(null);

  const fetch = useCallback(async () => {
    if (!id) {
      return;
    }

    setLoading(true);

    try {
      const response = await hostApi.findById(id);
      setHostDetails(response.data);
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

  return { hostDetails, loading, error };
};

export default useHostDetails;

