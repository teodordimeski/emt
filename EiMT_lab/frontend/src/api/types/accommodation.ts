import type { Host } from './host';
import type { Country } from './country';

export interface Accommodation {
  id: number;
  name: string;
  description: string;
  category: string;
  condition: string;
  numRooms: number;
}

export interface AccommodationDetails {
  id: number;
  name: string;
  description: string;
  pricePerNight: number;
  maxGuests: number;
  host: Host;
  country: Country;
}

