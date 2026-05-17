import axiosInstance from '../axios/axios';
import type { Accommodation, AccommodationDetails, CreateAccommodationRequest } from './types/accommodation';

const accommodationApi = {
  findAll: async () => {
    return await axiosInstance.get<Accommodation[]>('/accommodations');
  },
  findById: async (id: string) => {
    return await axiosInstance.get<AccommodationDetails>(`/accommodations/${id}`);
  },
  findWithDetailsById: async (id: string) => {
    return await axiosInstance.get<AccommodationDetails>(`/accommodations/${id}/with-host-and-country`);
  },
  add: async (data: CreateAccommodationRequest) => {
    return await axiosInstance.post<Accommodation>('/accommodations/add', data);
  },
  edit: async (id: string, data: CreateAccommodationRequest) => {
    return await axiosInstance.put<Accommodation>(`/accommodations/${id}/edit`, data);
  },
  delete: async (id: string) => {
    return await axiosInstance.delete<Accommodation>(`/accommodations/${id}/delete`);
  }
};

export default accommodationApi;
