import { useParams } from 'react-router';
import useUserDetails from '../../../hooks/useUserDetails';
import { Alert, Box, Container, Typography, CircularProgress, Card, CardContent } from '@mui/material';

const UserDetailsPage = () => {
  const { id } = useParams<{ id: string }>();
  const { userDetails, loading, error } = useUserDetails(id);

  const formatDate = (dateString: string) => {
    return new Date(dateString).toLocaleDateString('en-US', {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
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
          Failed to load user details: {error.message}
        </Alert>
      )}
      {!loading && !userDetails && !error && (
        <Alert severity='info'>
          No user details found.
        </Alert>
      )}
      {!loading && userDetails && (
        <Card>
          <CardContent>
            <Typography variant='h4' gutterBottom>{userDetails.username}</Typography>
            
            <Typography variant='h6' sx={{ mb: 1 }}>Role</Typography>
            <Typography variant='body1' sx={{ mb: 2 }}>{userDetails.role}</Typography>
            
            <Typography variant='h6' sx={{ mb: 1 }}>Created At</Typography>
            <Typography variant='body1'>{formatDate(userDetails.createdAt)}</Typography>
          </CardContent>
        </Card>
      )}
    </Container>
  );
};

export default UserDetailsPage;

