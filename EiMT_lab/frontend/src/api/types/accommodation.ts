export type Category = 'ROOM' | 'HOUSE' | 'FLAT' | 'APARTMENT' | 'HOTEL' | 'MOTEL';
export type Condition = 'GOOD' | 'BAD';

export interface Accommodation {
  id: number;
  name: string;
  category: Category;
  condition: Condition;
  hostId: number;
  hostFullName: string;
  numRooms: number;
  rented: boolean;
}

export interface AccommodationDetails {
  id: number;
  name: string;
  category: Category;
  condition: Condition;
  hostId: number;
  hostFullName: string;
  numRooms: number;
  rented: boolean;
}

export interface CreateAccommodationRequest {
  name: string;
  category: Category;
  hostId: number;
  condition: Condition;
  numRooms: number;
}
