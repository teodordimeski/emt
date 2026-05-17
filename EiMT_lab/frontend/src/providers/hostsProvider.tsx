import { useCallback, useEffect, useMemo, useState } from 'react';
import * as React from 'react';
import hostApi from '../api/hostApi';
import type { CreateHostRequest, Host } from '../api/types/host';
import HostsContext from '../contexts/hostsContext';
import useSnackbar from '../hooks/useSnackbar';

const HostsProvider = ({ children }: { children: React.ReactNode }) => {
  const { showSnackbar } = useSnackbar();

  const [hosts, setHosts] = useState<Host[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  const fetch = useCallback(async () => {
    setLoading(true);

    try {
      const response = await hostApi.findAll();
      setHosts(response.data);
    } catch (err) {
      showSnackbar(err instanceof Error ? err.message : 'Failed to load hosts.', 'error');
    } finally {
      setLoading(false);
    }
  }, [showSnackbar]);

  const onAdd = useCallback(async (data: CreateHostRequest) => {
    try {
      await hostApi.add(data);
      await fetch();
    } catch (err) {
      showSnackbar(err instanceof Error ? err.message : 'Failed to add host.', 'error');
    }
  }, [fetch, showSnackbar]);

  const onEdit = useCallback(async (id: number, data: CreateHostRequest) => {
    try {
      await hostApi.edit(id.toString(), data);
      await fetch();
    } catch (err) {
      showSnackbar(err instanceof Error ? err.message : 'Failed to edit host.', 'error');
    }
  }, [fetch, showSnackbar]);

  const onDelete = useCallback(async (id: number) => {
    try {
      await hostApi.delete(id.toString());
      await fetch();
    } catch (err) {
      showSnackbar(err instanceof Error ? err.message : 'Failed to delete host.', 'error');
    }
  }, [fetch, showSnackbar]);

  useEffect(() => {
    void fetch();
  }, [fetch]);

  const value = useMemo(
    () => ({ hosts, loading, onAdd, onEdit, onDelete }),
    [hosts, loading, onAdd, onEdit, onDelete]
  );

    return <HostsContext.Provider value={value}>{children}</HostsContext.Provider>;
};

export default HostsProvider;

