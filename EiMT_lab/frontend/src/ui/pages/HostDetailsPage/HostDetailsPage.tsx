import { useParams } from 'react-router';
import useHostDetails from '../../../hooks/useHostDetails';
import { Container, Typography, CircularProgress, Card, CardContent, Box } from '@mui/material';

const HostDetailsPage = () => {
  const { id } = useParams<{ id: string }>();
  const { hostDetails, loading } = useHostDetails(id);

  return (
    <Container maxWidth='sm' sx={{ mt: 3, py: 3 }}>
      {loading && (
        <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: 300 }}>
          <CircularProgress/>
        </Box>
      )}
      {!loading && hostDetails && (
        <Card>
          <CardContent>
            <Typography variant='h4' gutterBottom>{hostDetails.name}</Typography>
            <Typography variant='h6' sx={{ mb: 1 }}>Email</Typography>
            <Typography variant='body1' sx={{ mb: 2 }}>{hostDetails.email}</Typography>
            <Typography variant='h6' sx={{ mb: 1 }}>Phone Number</Typography>
            <Typography variant='body1'>{hostDetails.phoneNumber}</Typography>
          </CardContent>
        </Card>
      )}
    </Container>
  );
};

export default HostDetailsPage;

