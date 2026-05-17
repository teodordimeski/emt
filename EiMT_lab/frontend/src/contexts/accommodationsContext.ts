import { createContext } from 'react';
import type { Accommodation, CreateAccommodationRequest } from '../api/types/accommodation';

export interface AccommodationsContextType {
  accommodations: Accommodation[];
  loading: boolean;
  onAdd: (data: CreateAccommodationRequest) => Promise<void>;
  onEdit: (id: number, data: CreateAccommodationRequest) => Promise<void>;
  onDelete: (id: number) => Promise<void>;
}

const AccommodationsContext = createContext<AccommodationsContextType>({} as AccommodationsContextType);

export default AccommodationsContext;

