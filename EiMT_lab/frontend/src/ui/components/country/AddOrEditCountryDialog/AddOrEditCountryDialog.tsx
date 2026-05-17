import { Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField } from '@mui/material';
import { useState } from 'react';
import * as React from 'react';
import useCountries from '../../../../hooks/useCountries';
import type { Country, CreateCountryRequest } from '../../../../api/types/country';

interface FormData {
  name: string;
  continent: string;
}

const emptyFormData: FormData = {
  name: '',
  continent: ''
};

const countryToFormData = (country: Country): FormData => ({
  name: country.name,
  continent: country.continent
});

interface CountryFormDialogProps {
  open: boolean;
  onClose: () => void;
  country?: Country;
}

const AddOrEditCountryDialog = ({ open, onClose, country }: CountryFormDialogProps) => {
  const { onAdd, onEdit } = useCountries();
  const isEdit = country !== undefined;

  const [formData, setFormData] = useState<FormData>(
    country ? countryToFormData(country) : emptyFormData
  );

  const handleChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = event.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async () => {
    const payload: CreateCountryRequest = {
      name: formData.name.trim(),
      continent: formData.continent.trim()
    };

    if (isEdit && country) {
      await onEdit(country.id, payload);
    } else {
      await onAdd(payload);
      setFormData(emptyFormData);
    }
    onClose();
  };

  return (
    <Dialog open={open} onClose={onClose} fullWidth maxWidth='sm'>
      <DialogTitle>{isEdit ? 'Edit Country' : 'Add Country'}</DialogTitle>
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
          label='Continent'
          name='continent'
          value={formData.continent}
          onChange={handleChange}
          fullWidth
        />
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

export default AddOrEditCountryDialog;

