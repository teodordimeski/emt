import { useParams } from 'react-router';
import useCountryDetails from '../../../hooks/useCountryDetails';
import { Container, Typography, CircularProgress, Card, CardContent, Box } from '@mui/material';

const CountryDetailsPage = () => {
  const { id } = useParams<{ id: string }>();
  const { countryDetails, loading } = useCountryDetails(id);

  return (
    <Container maxWidth='sm' sx={{ mt: 3, py: 3 }}>
      {loading && (
        <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: 300 }}>
          <CircularProgress/>
        </Box>
      )}
      {!loading && countryDetails && (
        <Card>
          <CardContent>
            <Typography variant='h4' gutterBottom>{countryDetails.name}</Typography>
            <Typography variant='h6' sx={{ mb: 1 }}>Code</Typography>
            <Typography variant='body1' sx={{ mb: 2 }}>{countryDetails.code}</Typography>
            <Typography variant='h6' sx={{ mb: 1 }}>Region</Typography>
            <Typography variant='body1'>{countryDetails.region}</Typography>
          </CardContent>
        </Card>
      )}
    </Container>
  );
};

export default CountryDetailsPage;

