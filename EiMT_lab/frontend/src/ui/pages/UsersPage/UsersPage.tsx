import './UsersPage.css';
import useUsers from '../../../hooks/useUsers';
import { Box, CircularProgress } from '@mui/material';
import UserGrid from '../../components/user/UserGrid/UserGrid';

const UsersPage = () => {
  const { users, loading } = useUsers();

  return (
    <Box className='users-box'>
      {loading && (
        <Box className='progress-box'>
          <CircularProgress/>
        </Box>
      )}
      {!loading && <UserGrid users={users}/>}
    </Box>
  );
};

export default UsersPage;


