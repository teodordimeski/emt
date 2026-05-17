import axiosInstance from '../axios/axios';
import type { Country, CreateCountryRequest } from './types/country';

const countryApi = {
  findAll: async () => {
    return await axiosInstance.get<Country[]>('/countries');
  },
  findById: async (id: string) => {
    return await axiosInstance.get<Country>(`/countries/${id}`);
  },
  add: async (data: CreateCountryRequest) => {
    return await axiosInstance.post<Country>('/countries/add', data);
  },
  edit: async (id: string, data: CreateCountryRequest) => {
    return await axiosInstance.put<Country>(`/countries/edit/${id}`, data);
  },
  delete: async (id: string) => {
    return await axiosInstance.delete<Country>(`/countries/delete/${id}`);
  }
};

export default countryApi;
