import axiosInstance from '../axios/axios';
import type { Host } from './types/host';

const hostApi = {
  findAll: async () => {
    return await axiosInstance.get<Host[]>('/hosts');
  },
  findById: async (id: string) => {
    return await axiosInstance.get<Host>(`/hosts/${id}`);
  }
};

export default hostApi;

