import './Header.css';
import {
  AppBar, Box, Button, Drawer, IconButton, List, ListItem, ListItemButton, ListItemText, Toolbar, Typography
} from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import { Link } from 'react-router';
import { useState } from 'react';
import { tokenService } from '../../../../services/tokenService';

const pages = [
  { path: '/', name: 'Home' },
  { path: '/accommodations', name: 'Accommodations' },
  { path: '/hosts', name: 'Hosts' },
  { path: '/countries', name: 'Countries' }
];

const Header = () => {
  const [drawerOpen, setDrawerOpen] = useState(false);
  const isLoggedIn = tokenService.isTokenValid();

  const handleLogout = () => {
    tokenService.removeToken();
    window.location.href = '/login';
  };

  return (
    <Box>
      <AppBar position='static'>
        <Toolbar>
          <IconButton
            size='large'
            edge='start'
            color='inherit'
            aria-label='menu'
            sx={{ mr: 2, display: { md: 'none' } }}
            onClick={() => setDrawerOpen(true)}
          >
            <MenuIcon/>
          </IconButton>

          <Typography variant='h6' component='div' sx={{ mr: 3 }}>
            🏨 ACCOMMODATIONS
          </Typography>

          <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
            {pages.map((page) => (
              <Link key={page.name} to={page.path} style={{ textDecoration: 'none' }}>
                <Button className='header-button' sx={{ my: 2, color: 'white', display: 'block' }}>
                  {page.name}
                </Button>
              </Link>
            ))}
          </Box>

          {isLoggedIn ? (
            <Button color='inherit' onClick={handleLogout} sx={{ ml: 'auto' }}>
              Logout
            </Button>
          ) : (
            <Link to='/login' style={{ textDecoration: 'none' }}>
              <Button color='inherit' sx={{ ml: 'auto' }}>
                Login
              </Button>
            </Link>
          )}
        </Toolbar>
      </AppBar>

      <Drawer anchor='left' open={drawerOpen} onClose={() => setDrawerOpen(false)}>
        <Box sx={{ width: 240 }} role='presentation' onClick={() => setDrawerOpen(false)}>
          <List>
            {pages.map((page) => (
              <ListItem key={page.name} disablePadding>
                <ListItemButton component={Link} to={page.path}>
                  <ListItemText primary={page.name}/>
                </ListItemButton>
              </ListItem>
            ))}
          </List>
        </Box>
      </Drawer>
    </Box>
  );
};

export default Header;
