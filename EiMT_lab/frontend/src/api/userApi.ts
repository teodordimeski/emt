import axiosInstance from '../axios/axios';

interface LoginResponse {
  token: string;
}

const userApi = {
  login: async (username: string, password: string) => {
    const response = await axiosInstance.post<LoginResponse>('/user/login', {
      username,
      password
    });
    return response.data.token;
  },
  register: async (username: string, password: string) => {
    return await axiosInstance.post('/user/register', {
      username,
      password
    });
  }
};

export default userApi;
