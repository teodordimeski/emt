import type { JwtPayload } from 'jwt-decode';
import type { Role } from '../../enums/role';

export interface RegisterRequest {
  username: string;
  password: string;
}

export interface RegisterResponse {
  username: string;
  role: Role;
}

export interface LoginRequest {
  username: string;
  password: string;
}

export interface LoginResponse {
  token: string;
}

export interface UserPayload extends JwtPayload {
  username: string;
  roles: string[];
}

export interface User {
  id: number;
  username: string;
  role: Role;
  createdAt: string;
}
