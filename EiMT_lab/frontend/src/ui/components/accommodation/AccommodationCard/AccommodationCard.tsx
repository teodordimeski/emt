import { Box, Button, Card, CardActions, CardContent, Typography } from '@mui/material';
import InfoIcon from '@mui/icons-material/Info';
import { useNavigate } from 'react-router';
import type { Accommodation } from '../../../../api/types/accommodation';

interface AccommodationCardProps {
  accommodation: Accommodation;
}

const AccommodationCard = ({ accommodation }: AccommodationCardProps) => {
  const navigate = useNavigate();

  return (
    <Card sx={{ maxWidth: 300 }}>
      <CardContent>
        <Typography variant='h5'>{accommodation.name}</Typography>
        <Typography variant='subtitle1' sx={{ mb: 1 }}>{accommodation.description}</Typography>
        <Typography variant='body2' sx={{ textAlign: 'left' }}>Category: {accommodation.category}</Typography>
        <Typography variant='body2' sx={{ textAlign: 'left' }}>Condition: {accommodation.condition}</Typography>
        <Typography variant='body2' sx={{ textAlign: 'left' }}>Rooms: {accommodation.numRooms}</Typography>
      </CardContent>
      <CardActions sx={{ justifyContent: 'flex-start' }}>
        <Button
          startIcon={<InfoIcon/>}
          onClick={() => navigate(`/accommodations/${accommodation.id}`)}
        >
          Details
        </Button>
      </CardActions>
    </Card>
  );
};

export default AccommodationCard;

