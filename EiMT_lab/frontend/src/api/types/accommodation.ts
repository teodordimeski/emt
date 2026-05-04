export interface Accommodation {
  id: number;
  name: string;
  category: string;
  condition: string;
  numRooms: number;
  rented: boolean;
  hostFullName?: string;
}

export interface AccommodationDetails {
  id: number;
  name: string;
  category: string;
  condition: string;
  numRooms: number;
  rented: boolean;
  hostId: number;
  hostFullName: string;
}

