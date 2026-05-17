import './HostsPage.css';
import useHosts from '../../../hooks/useHosts';
import { Box, Button, CircularProgress } from '@mui/material';
import HostGrid from '../../components/host/HostGrid/HostGrid.tsx';
import { useState } from 'react';
import AddOrEditHostDialog from '../../components/host/AddOrEditHostDialog/AddOrEditHostDialog.tsx';
import useAuth from '../../../hooks/useAuth';

const HostsPage = () => {
  const { user } = useAuth();
  const isAdmin = user?.roles.includes('ROLE_ADMINISTRATOR') ?? false;

  const { hosts, loading } = useHosts();
  const [addDialogOpen, setAddDialogOpen] = useState<boolean>(false);

  return (
    <Box className='hosts-box'>
      {loading && (
        <Box className='progress-box'>
          <CircularProgress/>
        </Box>
      )}
      {!loading && (
        <>
          {isAdmin && (
            <Box sx={{ display: 'flex', justifyContent: 'flex-end', mb: 2 }}>
              <Button variant='contained' color='primary' onClick={() => setAddDialogOpen(true)}>
                Add Host
              </Button>
            </Box>
          )}
          <HostGrid hosts={hosts}/>
          <AddOrEditHostDialog
            open={addDialogOpen}
            onClose={() => setAddDialogOpen(false)}
          />
        </>
      )}
    </Box>
  );
};

export default HostsPage;
