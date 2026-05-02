import axiosInstance from '../axios/axios';
import type { Accommodation, AccommodationDetails } from './types/accommodation';

const accommodationApi = {
  findAll: async () => {
    return await axiosInstance.get<Accommodation[]>('/accommodations');
  },
  findById: async (id: string) => {
    return await axiosInstance.get<AccommodationDetails>(`/accommodations/${id}`);
  }
};

export default accommodationApi;

