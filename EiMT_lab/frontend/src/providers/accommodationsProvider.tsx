import { useCallback, useEffect, useMemo, useState } from 'react';
import * as React from 'react';
import accommodationApi from '../api/accommodationApi';
import type { Accommodation, CreateAccommodationRequest } from '../api/types/accommodation';
import AccommodationsContext from '../contexts/accommodationsContext';
import useSnackbar from '../hooks/useSnackbar';

const AccommodationsProvider = ({ children }: { children: React.ReactNode }) => {
  const { showSnackbar } = useSnackbar();

  const [accommodations, setAccommodations] = useState<Accommodation[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  const fetch = useCallback(async () => {
    setLoading(true);

    try {
      const response = await accommodationApi.findAll();
      setAccommodations(response.data);
    } catch (err) {
      showSnackbar(err instanceof Error ? err.message : 'Failed to load accommodations.', 'error');
    } finally {
      setLoading(false);
    }
  }, [showSnackbar]);

  const onAdd = useCallback(async (data: CreateAccommodationRequest) => {
    try {
      await accommodationApi.add(data);
      await fetch();
    } catch (err) {
      showSnackbar(err instanceof Error ? err.message : 'Failed to add accommodation.', 'error');
    }
  }, [fetch, showSnackbar]);

  const onEdit = useCallback(async (id: number, data: CreateAccommodationRequest) => {
    try {
      await accommodationApi.edit(id.toString(), data);
      await fetch();
    } catch (err) {
      showSnackbar(err instanceof Error ? err.message : 'Failed to edit accommodation.', 'error');
    }
  }, [fetch, showSnackbar]);

  const onDelete = useCallback(async (id: number) => {
    try {
      await accommodationApi.delete(id.toString());
      await fetch();
    } catch (err) {
      showSnackbar(err instanceof Error ? err.message : 'Failed to delete accommodation.', 'error');
    }
  }, [fetch, showSnackbar]);

  useEffect(() => {
    void fetch();
  }, [fetch]);

  const value = useMemo(
    () => ({ accommodations, loading, onAdd, onEdit, onDelete }),
    [accommodations, loading, onAdd, onEdit, onDelete]
  );

    return <AccommodationsContext.Provider value={value}>{children}</AccommodationsContext.Provider>;
};

export default AccommodationsProvider;

