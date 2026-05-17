import axiosInstance from '../axios/axios';
import type { CreateHostRequest, Host } from './types/host';

const hostApi = {
  findAll: async () => {
    return await axiosInstance.get<Host[]>('/hosts');
  },
  findById: async (id: string) => {
    return await axiosInstance.get<Host>(`/hosts/${id}`);
  },
  add: async (data: CreateHostRequest) => {
    return await axiosInstance.post<Host>('/hosts/add', data);
  },
  edit: async (id: string, data: CreateHostRequest) => {
    return await axiosInstance.put<Host>(`/hosts/${id}/edit`, data);
  },
  delete: async (id: string) => {
    return await axiosInstance.delete<Host>(`/hosts/${id}/delete`);
  }
};

export default hostApi;
