import { createContext } from 'react';
import type { Country, CreateCountryRequest } from '../api/types/country';

export interface CountriesContextType {
  countries: Country[];
  loading: boolean;
  onAdd: (data: CreateCountryRequest) => Promise<void>;
  onEdit: (id: number, data: CreateCountryRequest) => Promise<void>;
  onDelete: (id: number) => Promise<void>;
}

const CountriesContext = createContext<CountriesContextType>({} as CountriesContextType);

export default CountriesContext;

