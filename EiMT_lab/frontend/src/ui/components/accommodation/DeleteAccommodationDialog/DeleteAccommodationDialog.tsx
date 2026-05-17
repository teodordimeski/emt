import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@mui/material';
import type { Accommodation } from '../../../../api/types/accommodation';
import useAccommodations from '../../../../hooks/useAccommodations';

interface DeleteAccommodationDialogProps {
  accommodation: Accommodation;
  open: boolean;
  onClose: () => void;
}

const DeleteAccommodationDialog = ({ accommodation, open, onClose }: DeleteAccommodationDialogProps) => {
  const { onDelete } = useAccommodations();

  const handleSubmit = async () => {
    await onDelete(accommodation.id);
    onClose();
  };

  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle>Delete Accommodation</DialogTitle>
      <DialogContent>
        <DialogContentText>
          Are you sure you want to delete <strong>{accommodation.name}</strong>? This action cannot be undone.
        </DialogContentText>
        <DialogActions>
          <Button onClick={onClose}>Cancel</Button>
          <Button onClick={handleSubmit} color='error' variant='contained'>Delete</Button>
        </DialogActions>
      </DialogContent>
    </Dialog>
  );
};

export default DeleteAccommodationDialog;

