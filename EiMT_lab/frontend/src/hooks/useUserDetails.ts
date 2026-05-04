import { useCallback, useEffect, useState } from 'react';
import userApi from '../api/userApi';
import type { User } from '../api/types/user';

const useUserDetails = (id?: string) => {
  const [userDetails, setUserDetails] = useState<User | null>(null);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<Error | null>(null);

  const fetch = useCallback(async () => {
    if (!id) {
      return;
    }

    setLoading(true);

    try {
      const response = await userApi.findById(id);
      setUserDetails(response.data);
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

  return { userDetails, loading, error };
};

export default useUserDetails;

