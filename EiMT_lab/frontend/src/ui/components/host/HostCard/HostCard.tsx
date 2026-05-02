import { Box, Button, Card, CardActions, CardContent, Typography } from '@mui/material';
import InfoIcon from '@mui/icons-material/Info';
import { useNavigate } from 'react-router';
import type { Host } from '../../../../api/types/host';

interface HostCardProps {
  host: Host;
}

const HostCard = ({ host }: HostCardProps) => {
  const navigate = useNavigate();

  return (
    <Card sx={{ maxWidth: 300 }}>
      <CardContent>
        <Typography variant='h5'>{host.name}</Typography>
        <Typography variant='subtitle1' sx={{ mb: 1 }}>{host.email}</Typography>
        <Typography variant='body2' sx={{ textAlign: 'left' }}>📞 {host.phoneNumber}</Typography>
      </CardContent>
      <CardActions sx={{ justifyContent: 'flex-start' }}>
        <Button
          startIcon={<InfoIcon/>}
          onClick={() => navigate(`/hosts/${host.id}`)}
        >
          Details
        </Button>
      </CardActions>
    </Card>
  );
};

export default HostCard;

