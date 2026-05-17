import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  type SelectChangeEvent,
  TextField
} from '@mui/material';
import { useState } from 'react';
import * as React from 'react';
import useHosts from '../../../../hooks/useHosts';
import useAccommodations from '../../../../hooks/useAccommodations';
import type { Accommodation, Category, Condition, CreateAccommodationRequest } from '../../../../api/types/accommodation';

interface FormData {
  name: string;
  category: Category | '';
  condition: Condition | '';
  numRooms: string;
  hostId: string;
}

const emptyFormData: FormData = {
  name: '',
  category: '',
  condition: '',
  numRooms: '',
  hostId: ''
};

const accommodationToFormData = (accommodation: Accommodation): FormData => ({
  name: accommodation.name,
  category: accommodation.category,
  condition: accommodation.condition,
  numRooms: accommodation.numRooms.toString(),
  hostId: accommodation.hostId.toString()
});

const categories: Category[] = ['ROOM', 'HOUSE', 'FLAT', 'APARTMENT', 'HOTEL', 'MOTEL'];
const conditions: Condition[] = ['GOOD', 'BAD'];

interface AccommodationFormDialogProps {
  open: boolean;
  onClose: () => void;
  accommodation?: Accommodation;
}

const AddOrEditAccommodationDialog = ({ open, onClose, accommodation }: AccommodationFormDialogProps) => {
  const { hosts } = useHosts();
  const { onAdd, onEdit } = useAccommodations();

  const isEdit = accommodation !== undefined;

  const [formData, setFormData] = useState<FormData>(
    accommodation ? accommodationToFormData(accommodation) : emptyFormData
  );

  const handleChange = (
    event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement> | SelectChangeEvent
  ) => {
    const { name, value } = event.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async () => {
    if (!formData.category || !formData.condition) {
      return;
    }

    const payload: CreateAccommodationRequest = {
      name: formData.name.trim(),
      category: formData.category,
      condition: formData.condition,
      numRooms: Number(formData.numRooms),
      hostId: Number(formData.hostId)
    };

    if (isEdit && accommodation) {
      await onEdit(accommodation.id, payload);
    } else {
      await onAdd(payload);
      setFormData(emptyFormData);
    }
    onClose();
  };

  return (
    <Dialog open={open} onClose={onClose} fullWidth maxWidth='sm'>
      <DialogTitle>{isEdit ? 'Edit Accommodation' : 'Add Accommodation'}</DialogTitle>
      <DialogContent>
        <TextField
          margin='dense'
          label='Name'
          name='name'
          value={formData.name}
          onChange={handleChange}
          fullWidth
        />
        <FormControl margin='dense' fullWidth>
          <InputLabel>Category</InputLabel>
          <Select
            label='Category'
            name='category'
            value={formData.category}
            onChange={handleChange}
            variant='outlined'
          >
            {categories.map((category) => (
              <MenuItem key={category} value={category}>{category}</MenuItem>
            ))}
          </Select>
        </FormControl>
        <FormControl margin='dense' fullWidth>
          <InputLabel>Condition</InputLabel>
          <Select
            label='Condition'
            name='condition'
            value={formData.condition}
            onChange={handleChange}
            variant='outlined'
          >
            {conditions.map((condition) => (
              <MenuItem key={condition} value={condition}>{condition}</MenuItem>
            ))}
          </Select>
        </FormControl>
        <TextField
          margin='dense'
          label='Rooms'
          name='numRooms'
          value={formData.numRooms}
          onChange={handleChange}
          type='number'
          fullWidth
        />
        <FormControl margin='dense' fullWidth>
          <InputLabel>Host</InputLabel>
          <Select
            label='Host'
            name='hostId'
            value={formData.hostId}
            onChange={handleChange}
            variant='outlined'
          >
            {hosts.map((host) => (
              <MenuItem key={host.id} value={host.id}>
                {host.name} {host.surname}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose}>Cancel</Button>
        <Button onClick={handleSubmit} variant='contained' color='primary'>
          {isEdit ? 'Edit' : 'Add'}
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default AddOrEditAccommodationDialog;

