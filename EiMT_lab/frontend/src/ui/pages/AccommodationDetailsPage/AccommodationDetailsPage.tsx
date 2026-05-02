import { useParams } from 'react-router';
import useAccommodationDetails from '../../../hooks/useAccommodationDetails';
import { Box, Container, Typography, CircularProgress, Grid, Card, CardContent } from '@mui/material';

const AccommodationDetailsPage = () => {
  const { id } = useParams<{ id: string }>();
  const { accommodationDetails, loading } = useAccommodationDetails(id);

  return (
    <Container maxWidth='lg' sx={{ mt: 3, py: 3 }}>
      {loading && (
        <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: 300 }}>
          <CircularProgress/>
        </Box>
      )}
      {!loading && accommodationDetails && (
        <Grid container spacing={3}>
          <Grid item xs={12} md={8}>
            <Card>
              <CardContent>
                <Typography variant='h4' gutterBottom>{accommodationDetails.name}</Typography>
                <Typography variant='body1' sx={{ mb: 2 }}>{accommodationDetails.description}</Typography>
                <Typography variant='h6'>Price per Night: ${accommodationDetails.pricePerNight}</Typography>
                <Typography variant='h6'>Max Guests: {accommodationDetails.maxGuests}</Typography>
              </CardContent>
            </Card>
          </Grid>
          <Grid item xs={12} md={4}>
            <Card>
              <CardContent>
                <Typography variant='h6' gutterBottom>Host Information</Typography>
                <Typography variant='body2'>Name: {accommodationDetails.host.name}</Typography>
                <Typography variant='body2'>Email: {accommodationDetails.host.email}</Typography>
                <Typography variant='body2'>Phone: {accommodationDetails.host.phoneNumber}</Typography>
              </CardContent>
            </Card>
            <Card sx={{ mt: 2 }}>
              <CardContent>
                <Typography variant='h6' gutterBottom>Country</Typography>
                <Typography variant='body2'>Name: {accommodationDetails.country.name}</Typography>
                <Typography variant='body2'>Code: {accommodationDetails.country.code}</Typography>
                <Typography variant='body2'>Region: {accommodationDetails.country.region}</Typography>
              </CardContent>
            </Card>
          </Grid>
        </Grid>
      )}
    </Container>
  );
};

export default AccommodationDetailsPage;

