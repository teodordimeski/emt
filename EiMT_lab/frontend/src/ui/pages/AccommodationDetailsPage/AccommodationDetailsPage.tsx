import { useParams } from 'react-router';
import useAccommodationDetails from '../../../hooks/useAccommodationDetails';
import { Alert, Box, Container, Typography, CircularProgress, Card, CardContent } from '@mui/material';

const AccommodationDetailsPage = () => {
  const { id } = useParams<{ id: string }>();
  const { accommodationDetails, loading, error } = useAccommodationDetails(id);

  const getRentedStatus = (rented: boolean) => {
    return rented ? '✅ Rented' : '❌ Available';
  };

  return (
    <Container maxWidth='sm' sx={{ mt: 3, py: 3 }}>
      {loading && (
        <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: 300 }}>
          <CircularProgress/>
        </Box>
      )}
      {!loading && error && (
        <Alert severity='error'>
          Failed to load accommodation details: {error.message}
        </Alert>
      )}
      {!loading && !accommodationDetails && !error && (
        <Alert severity='info'>
          No accommodation details found.
        </Alert>
      )}
      {!loading && accommodationDetails && (
        <Card>
          <CardContent>
            <Typography variant='h4' gutterBottom>{accommodationDetails.name}</Typography>
            
            <Typography variant='h6' sx={{ mb: 1 }}>Category</Typography>
            <Typography variant='body1' sx={{ mb: 2 }}>{accommodationDetails.category}</Typography>
            
            <Typography variant='h6' sx={{ mb: 1 }}>Condition</Typography>
            <Typography variant='body1' sx={{ mb: 2 }}>{accommodationDetails.condition}</Typography>
            
            <Typography variant='h6' sx={{ mb: 1 }}>Number of Rooms</Typography>
            <Typography variant='body1' sx={{ mb: 2 }}>{accommodationDetails.numRooms}</Typography>
            
            <Typography variant='h6' sx={{ mb: 1 }}>Host</Typography>
            <Typography variant='body1' sx={{ mb: 2 }}>{accommodationDetails.hostFullName}</Typography>
            
            <Typography variant='h6' sx={{ mb: 1 }}>Status</Typography>
            <Typography variant='body1'>{getRentedStatus(accommodationDetails.rented)}</Typography>
          </CardContent>
        </Card>
      )}
    </Container>
  );
};

export default AccommodationDetailsPage;

