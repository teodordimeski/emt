import { Box, Button, Card, CardActions, CardContent, Typography } from '@mui/material';
import InfoIcon from '@mui/icons-material/Info';
import { useNavigate } from 'react-router';
import type { Country } from '../../../../api/types/country';

interface CountryCardProps {
  country: Country;
}

const CountryCard = ({ country }: CountryCardProps) => {
  const navigate = useNavigate();

  return (
    <Card sx={{ maxWidth: 300 }}>
      <CardContent>
        <Typography variant='h5'>{country.name}</Typography>
        <Typography variant='subtitle1' sx={{ mb: 1 }}>Code: {country.code}</Typography>
        <Typography variant='body2' sx={{ textAlign: 'left' }}>Region: {country.region}</Typography>
      </CardContent>
      <CardActions sx={{ justifyContent: 'flex-start' }}>
        <Button
          startIcon={<InfoIcon/>}
          onClick={() => navigate(`/countries/${country.id}`)}
        >
          Details
        </Button>
      </CardActions>
    </Card>
  );
};

export default CountryCard;

