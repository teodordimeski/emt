import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@mui/material';
import type { Host } from '../../../../api/types/host';
import useHosts from '../../../../hooks/useHosts';

interface DeleteHostDialogProps {
  host: Host;
  open: boolean;
  onClose: () => void;
}

const DeleteHostDialog = ({ host, open, onClose }: DeleteHostDialogProps) => {
  const { onDelete } = useHosts();

  const handleSubmit = async () => {
    await onDelete(host.id);
    onClose();
  };

  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle>Delete Host</DialogTitle>
      <DialogContent>
        <DialogContentText>
          Are you sure you want to delete <strong>{host.name} {host.surname}</strong>? This action cannot be undone.
        </DialogContentText>
        <DialogActions>
          <Button onClick={onClose}>Cancel</Button>
          <Button onClick={handleSubmit} color='error' variant='contained'>Delete</Button>
        </DialogActions>
      </DialogContent>
    </Dialog>
  );
};

export default DeleteHostDialog;

