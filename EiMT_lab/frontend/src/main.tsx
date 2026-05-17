import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App.tsx';
import AuthProvider from './providers/authProvider.tsx';
import SnackbarProvider from './providers/snackbarProvider.tsx';

const root = ReactDOM.createRoot(document.getElementById('root')!);
root.render(
  <React.StrictMode>
    <AuthProvider>
      <SnackbarProvider>
        <App/>
      </SnackbarProvider>
    </AuthProvider>
  </React.StrictMode>
);

