import { Box, Button, Card, CardActions, CardContent, Typography } from '@mui/material';
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import { useNavigate } from 'react-router';
import { useState } from 'react';
import type { Accommodation } from '../../../../api/types/accommodation';
import AddOrEditAccommodationDialog from '../AddOrEditAccommodationDialog/AddOrEditAccommodationDialog';
import DeleteAccommodationDialog from '../DeleteAccommodationDialog/DeleteAccommodationDialog';
import useAuth from '../../../../hooks/useAuth';

interface AccommodationCardProps {
  accommodation: Accommodation;
}

const AccommodationCard = ({ accommodation }: AccommodationCardProps) => {
  const { user } = useAuth();
  const isAdmin = user?.roles.includes('ROLE_ADMINISTRATOR') ?? false;
  const navigate = useNavigate();

  const [editDialogOpen, setEditDialogOpen] = useState<boolean>(false);
  const [deleteDialogOpen, setDeleteDialogOpen] = useState<boolean>(false);

  return (
    <>
      <Card sx={{ maxWidth: 300, height: '100%', display: 'flex', flexDirection: 'column' }}>
        <CardContent sx={{ flexGrow: 1, display: 'flex', flexDirection: 'column' }}>
          <Typography variant='h5'>{accommodation.name}</Typography>
          <Typography variant='body2' sx={{ textAlign: 'left' }}>Category: {accommodation.category}</Typography>
          <Typography variant='body2' sx={{ textAlign: 'left' }}>Condition: {accommodation.condition}</Typography>
          <Typography variant='body2' sx={{ textAlign: 'left' }}>Rooms: {accommodation.numRooms}</Typography>
          <Typography variant='body2' sx={{ textAlign: 'left' }}>Host: {accommodation.hostFullName}</Typography>
        </CardContent>
        <CardActions sx={{ justifyContent: 'space-between' }}>
          <Button
            startIcon={<InfoIcon/>}
            onClick={() => navigate(`/accommodations/${accommodation.id}`)}
          >
            Details
          </Button>
          <Box>
            {isAdmin && (
              <Button
                startIcon={<EditIcon/>}
                color='warning'
                onClick={() => setEditDialogOpen(true)}
              >
                Edit
              </Button>
            )}
            {isAdmin && (
              <Button
                startIcon={<DeleteIcon/>}
                color='error'
                onClick={() => setDeleteDialogOpen(true)}
              >
                Delete
              </Button>
            )}
          </Box>
        </CardActions>
      </Card>
      <AddOrEditAccommodationDialog
        accommodation={accommodation}
        open={editDialogOpen}
        onClose={() => setEditDialogOpen(false)}
      />
      <DeleteAccommodationDialog
        accommodation={accommodation}
        open={deleteDialogOpen}
        onClose={() => setDeleteDialogOpen(false)}
      />
    </>
  );
};

export default AccommodationCard;
