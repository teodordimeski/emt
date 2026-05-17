import './AccommodationsPage.css';
import useAccommodations from '../../../hooks/useAccommodations';
import { Box, Button, CircularProgress } from '@mui/material';
import AccommodationGrid from '../../components/accommodation/AccommodationGrid/AccommodationGrid.tsx';
import { useState } from 'react';
import AddOrEditAccommodationDialog from '../../components/accommodation/AddOrEditAccommodationDialog/AddOrEditAccommodationDialog.tsx';
import useAuth from '../../../hooks/useAuth';

const AccommodationsPage = () => {
  const { user } = useAuth();
  const isAdmin = user?.roles.includes('ROLE_ADMINISTRATOR') ?? false;

  const { accommodations, loading } = useAccommodations();
  const [addDialogOpen, setAddDialogOpen] = useState<boolean>(false);

  return (
    <Box className='accommodations-box'>
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
                Add Accommodation
              </Button>
            </Box>
          )}
          <AccommodationGrid accommodations={accommodations}/>
          <AddOrEditAccommodationDialog
            open={addDialogOpen}
            onClose={() => setAddDialogOpen(false)}
          />
        </>
      )}
    </Box>
  );
};

export default AccommodationsPage;
