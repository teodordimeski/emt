import { useEffect, useState, useCallback } from 'react';
import hostApi from '../api/hostApi';
import type { Host } from '../api/types/host';

const useHosts = () => {
  const [hosts, setHosts] = useState<Host[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<Error | null>(null);

  const fetch = useCallback(async () => {
    setLoading(true);

    try {
      const response = await hostApi.findAll();
      setHosts(response.data);
      setError(null);
    } catch (err) {
      setError(err instanceof Error ? err : new Error('An unknown error occurred.'));
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    void fetch();
  }, [fetch]);

  return { hosts, loading, error, fetch };
};

export default useHosts;

