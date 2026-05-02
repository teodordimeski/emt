import './AccommodationsPage.css';
import useAccommodations from '../../../hooks/useAccommodations';
import { Box, CircularProgress } from '@mui/material';
import AccommodationGrid from '../../components/accommodation/AccommodationGrid/AccommodationGrid.tsx';

const AccommodationsPage = () => {
  const { accommodations, loading } = useAccommodations();

  return (
    <Box className='accommodations-box'>
      {loading && (
        <Box className='progress-box'>
          <CircularProgress/>
        </Box>
      )}
      {!loading && <AccommodationGrid accommodations={accommodations}/>}
    </Box>
  );
};

export default AccommodationsPage;

