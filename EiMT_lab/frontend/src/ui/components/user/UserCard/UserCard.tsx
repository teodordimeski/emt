import { Button, Card, CardActions, CardContent, Typography } from '@mui/material';
import InfoIcon from '@mui/icons-material/Info';
import { useNavigate } from 'react-router';
import type { User } from '../../../../api/types/user';

interface UserCardProps {
  user: User;
}

const UserCard = ({ user }: UserCardProps) => {
  const navigate = useNavigate();

  return (
    <Card sx={{ maxWidth: 300 }}>
      <CardContent>
        <Typography variant='h5'>{user.username}</Typography>
      </CardContent>
      <CardActions sx={{ justifyContent: 'flex-start' }}>
        <Button
          startIcon={<InfoIcon/>}
          onClick={() => navigate(`/users/${user.id}`)}
        >
          Details
        </Button>
      </CardActions>
    </Card>
  );
};

export default UserCard;

