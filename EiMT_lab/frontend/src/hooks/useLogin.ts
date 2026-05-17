import { useState } from 'react';
import { useNavigate } from 'react-router';
import type { LoginRequest } from '../api/types/user';
import userApi from '../api/userApi';
import useAuth from './useAuth';
import useSnackbar from './useSnackbar';

const useLogin = () => {
  const navigate = useNavigate();
  const { login: authLogin } = useAuth();
  const { showSnackbar } = useSnackbar();
  const [loading, setLoading] = useState<boolean>(false);

  const login = async (data: LoginRequest) => {
    setLoading(true);

    try {
      const response = await userApi.login(data);
      authLogin(response.data.token);
      navigate('/');
    } catch (err) {
      showSnackbar(err instanceof Error ? err.message : 'Login failed. Please try again!', 'error');
    } finally {
      setLoading(false);
    }
  };

  return { loading, login };
};

export default useLogin;

