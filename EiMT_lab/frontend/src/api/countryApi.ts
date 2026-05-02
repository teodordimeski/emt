import axiosInstance from '../axios/axios';
import type { Country } from './types/country';

const countryApi = {
  findAll: async () => {
    return await axiosInstance.get<Country[]>('/countries');
  },
  findById: async (id: string) => {
    return await axiosInstance.get<Country>(`/countries/${id}`);
  }
};

export default countryApi;

