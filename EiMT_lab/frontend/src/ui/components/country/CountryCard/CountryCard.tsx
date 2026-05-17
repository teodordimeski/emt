import { Box, Button, Card, CardActions, CardContent, Typography } from '@mui/material';
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import { useNavigate } from 'react-router';
import { useState } from 'react';
import type { Country } from '../../../../api/types/country';
import AddOrEditCountryDialog from '../AddOrEditCountryDialog/AddOrEditCountryDialog';
import DeleteCountryDialog from '../DeleteCountryDialog/DeleteCountryDialog';
import useAuth from '../../../../hooks/useAuth';

interface CountryCardProps {
  country: Country;
}

const CountryCard = ({ country }: CountryCardProps) => {
  const { user } = useAuth();
  const isAdmin = user?.roles.includes('ROLE_ADMINISTRATOR') ?? false;
  const navigate = useNavigate();

  const [editDialogOpen, setEditDialogOpen] = useState<boolean>(false);
  const [deleteDialogOpen, setDeleteDialogOpen] = useState<boolean>(false);

  return (
    <>
      <Card sx={{ maxWidth: 300, height: '100%', display: 'flex', flexDirection: 'column' }}>
        <CardContent sx={{ flexGrow: 1, display: 'flex', flexDirection: 'column' }}>
          <Typography variant='h5'>{country.name}</Typography>
          <Typography variant='body2' sx={{ textAlign: 'left' }}>Continent: {country.continent}</Typography>
        </CardContent>
        <CardActions sx={{ justifyContent: 'space-between' }}>
          <Button
            startIcon={<InfoIcon/>}
            onClick={() => navigate(`/countries/${country.id}`)}
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
      <AddOrEditCountryDialog
        country={country}
        open={editDialogOpen}
        onClose={() => setEditDialogOpen(false)}
      />
      <DeleteCountryDialog
        country={country}
        open={deleteDialogOpen}
        onClose={() => setDeleteDialogOpen(false)}
      />
    </>
  );
};

export default CountryCard;

