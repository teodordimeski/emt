// Store and manage JWT tokens in localStorage
export const tokenService = {
  getToken: () => localStorage.getItem('token'),
  setToken: (token: string) => localStorage.setItem('token', token),
  removeToken: () => localStorage.removeItem('token'),
  isTokenValid: () => !!localStorage.getItem('token')
};

