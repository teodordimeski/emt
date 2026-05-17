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
import useCountries from '../../../../hooks/useCountries';
import useHosts from '../../../../hooks/useHosts';
import type { CreateHostRequest, Host } from '../../../../api/types/host';

interface FormData {
  name: string;
  surname: string;
  countryId: string;
}

const emptyFormData: FormData = {
  name: '',
  surname: '',
  countryId: ''
};

const hostToFormData = (host: Host): FormData => ({
  name: host.name,
  surname: host.surname,
  countryId: host.country_id.toString()
});

interface HostFormDialogProps {
  open: boolean;
  onClose: () => void;
  host?: Host;
}

const AddOrEditHostDialog = ({ open, onClose, host }: HostFormDialogProps) => {
  const { countries } = useCountries();
  const { onAdd, onEdit } = useHosts();

  const isEdit = host !== undefined;

  const [formData, setFormData] = useState<FormData>(
    host ? hostToFormData(host) : emptyFormData
  );

  const handleChange = (
    event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement> | SelectChangeEvent
  ) => {
    const { name, value } = event.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async () => {
    const payload: CreateHostRequest = {
      name: formData.name.trim(),
      surname: formData.surname.trim(),
      countryId: Number(formData.countryId)
    };

    if (isEdit && host) {
      await onEdit(host.id, payload);
    } else {
      await onAdd(payload);
      setFormData(emptyFormData);
    }
    onClose();
  };

  return (
    <Dialog open={open} onClose={onClose} fullWidth maxWidth='sm'>
      <DialogTitle>{isEdit ? 'Edit Host' : 'Add Host'}</DialogTitle>
      <DialogContent>
        <TextField
          margin='dense'
          label='Name'
          name='name'
          value={formData.name}
          onChange={handleChange}
          fullWidth
        />
        <TextField
          margin='dense'
          label='Surname'
          name='surname'
          value={formData.surname}
          onChange={handleChange}
          fullWidth
        />
        <FormControl margin='dense' fullWidth>
          <InputLabel>Country</InputLabel>
          <Select
            label='Country'
            name='countryId'
            value={formData.countryId}
            onChange={handleChange}
            variant='outlined'
          >
            {countries.map((country) => (
              <MenuItem key={country.id} value={country.id}>
                {country.name}
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

export default AddOrEditHostDialog;

