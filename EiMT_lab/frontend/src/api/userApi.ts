import axiosInstance from '../axios/axios';
import type { LoginRequest, LoginResponse, RegisterRequest, RegisterResponse, User } from './types/user';

const userApi = {
  findAll: async () => {
    return await axiosInstance.get<User[]>('/users');
  },

  findById: async (id: string) => {
    return await axiosInstance.get<User>(`/users/${id}`);
  },

  login: async (data: LoginRequest) => {
    return await axiosInstance.post<LoginResponse>('/users/login', data);
  },

  register: async (data: RegisterRequest) => {
    return await axiosInstance.post<RegisterResponse>('/users/register', data);
  }
};

export default userApi;