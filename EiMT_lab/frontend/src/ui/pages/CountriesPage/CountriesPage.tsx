import './CountriesPage.css';
import useCountries from '../../../hooks/useCountries';
import { Box, Button, CircularProgress } from '@mui/material';
import CountryGrid from '../../components/country/CountryGrid/CountryGrid.tsx';
import { useState } from 'react';
import AddOrEditCountryDialog from '../../components/country/AddOrEditCountryDialog/AddOrEditCountryDialog.tsx';
import useAuth from '../../../hooks/useAuth';

const CountriesPage = () => {
  const { user } = useAuth();
  const isAdmin = user?.roles.includes('ROLE_ADMINISTRATOR') ?? false;

  const { countries, loading } = useCountries();
  const [addDialogOpen, setAddDialogOpen] = useState<boolean>(false);

  return (
    <Box className='countries-box'>
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
                Add Country
              </Button>
            </Box>
          )}
          <CountryGrid countries={countries}/>
          <AddOrEditCountryDialog
            open={addDialogOpen}
            onClose={() => setAddDialogOpen(false)}
          />
        </>
      )}
    </Box>
  );
};

export default CountriesPage;
