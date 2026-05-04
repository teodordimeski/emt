import axiosInstance from '../axios/axios';
import type { User } from './types/user';

interface LoginResponse {
    token: string;
}

const userApi = {
    findAll: async () => {
        return await axiosInstance.get<User[]>('/users');
    },

    findById: async (id: string) => {
        return await axiosInstance.get<User>(`/users/${id}`);
    },

    login: async (username: string, password: string) => {
        const response = await axiosInstance.post<LoginResponse>('/users/login', {
            username,
            password
        });
        return response.data.token;
    },

    register: async (username: string, password: string) => {
        return await axiosInstance.post('/users/register', {
            username,
            password
        });
    }
};

export default userApi;