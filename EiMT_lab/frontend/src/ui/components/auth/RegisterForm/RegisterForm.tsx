import { Box, Button, Container, Paper, TextField, Typography } from '@mui/material';
import { useState } from 'react';
import * as React from 'react';
import useRegister from '../../../../hooks/useRegister';

interface FormData {
  username: string;
  password: string;
}

const initialFormData: FormData = {
  username: '',
  password: ''
};

const RegisterForm = () => {
  const [formData, setFormData] = useState<FormData>(initialFormData);
  const { register } = useRegister();

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async () => {
    await register(formData);
  };

  return (
    <Container maxWidth='sm'>
      <Paper elevation={3} sx={{ padding: 4, mt: 4 }}>
        <Typography variant='h5' align='center' gutterBottom>Register</Typography>
        <Box>
          <TextField
            fullWidth
            label='Username'
            name='username'
            margin='normal'
            required
            value={formData.username}
            onChange={handleChange}
          />
          <TextField
            fullWidth
            label='Password'
            name='password'
            type='password'
            margin='normal'
            required
            value={formData.password}
            onChange={handleChange}
          />
          <Button fullWidth variant='contained' type='submit' sx={{ mt: 2 }} onClick={handleSubmit}>
            Register
          </Button>
        </Box>
      </Paper>
    </Container>
  );
};

export default RegisterForm;

