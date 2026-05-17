import { useCallback, useEffect, useMemo, useState } from 'react';
import * as React from 'react';
import countryApi from '../api/countryApi';
import type { Country, CreateCountryRequest } from '../api/types/country';
import CountriesContext from '../contexts/countriesContext';
import useSnackbar from '../hooks/useSnackbar';

const CountriesProvider = ({ children }: { children: React.ReactNode }) => {
  const { showSnackbar } = useSnackbar();

  const [countries, setCountries] = useState<Country[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  const fetch = useCallback(async () => {
    setLoading(true);

    try {
      const response = await countryApi.findAll();
      setCountries(response.data);
    } catch (err) {
      showSnackbar(err instanceof Error ? err.message : 'Failed to load countries.', 'error');
    } finally {
      setLoading(false);
    }
  }, [showSnackbar]);

  const onAdd = useCallback(async (data: CreateCountryRequest) => {
    try {
      await countryApi.add(data);
      await fetch();
    } catch (err) {
      showSnackbar(err instanceof Error ? err.message : 'Failed to add country.', 'error');
    }
  }, [fetch, showSnackbar]);

  const onEdit = useCallback(async (id: number, data: CreateCountryRequest) => {
    try {
      await countryApi.edit(id.toString(), data);
      await fetch();
    } catch (err) {
      showSnackbar(err instanceof Error ? err.message : 'Failed to edit country.', 'error');
    }
  }, [fetch, showSnackbar]);

  const onDelete = useCallback(async (id: number) => {
    try {
      await countryApi.delete(id.toString());
      await fetch();
    } catch (err) {
      showSnackbar(err instanceof Error ? err.message : 'Failed to delete country.', 'error');
    }
  }, [fetch, showSnackbar]);

  useEffect(() => {
    void fetch();
  }, [fetch]);

  const value = useMemo(
    () => ({ countries, loading, onAdd, onEdit, onDelete }),
    [countries, loading, onAdd, onEdit, onDelete]
  );

  return <CountriesContext value={value}>{children}</CountriesContext>;
};

export default CountriesProvider;

