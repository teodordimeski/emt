import { createContext } from 'react';
import type { CreateHostRequest, Host } from '../api/types/host';

export interface HostsContextType {
  hosts: Host[];
  loading: boolean;
  onAdd: (data: CreateHostRequest) => Promise<void>;
  onEdit: (id: number, data: CreateHostRequest) => Promise<void>;
  onDelete: (id: number) => Promise<void>;
}

const HostsContext = createContext<HostsContextType>({} as HostsContextType);

export default HostsContext;

