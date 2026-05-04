import type { Role } from '../../enums/role';

export interface User {
  id: number;
  username: string;
  role: Role;
  createdAt: string;
}


