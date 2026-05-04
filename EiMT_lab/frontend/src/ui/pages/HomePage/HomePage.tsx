import { Box, Container, Typography } from '@mui/material';

const HomePage = () => {
  return (
    <Box sx={{ m: 0, p: 0 }}>
      <Container maxWidth='xl' sx={{ mt: 3, py: 3 }}>
        <Typography variant='h4' gutterBottom>
          Welcome to Accommodations Platform!
        </Typography>
        <Typography variant='body1' sx={{ mb: 4 }}>
          Discover amazing accommodations, meet our hosts, and explore countries.
        </Typography>
      </Container>
    </Box>
  );
};

export default HomePage;

