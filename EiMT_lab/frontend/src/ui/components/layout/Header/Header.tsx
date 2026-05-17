import './Header.css';
import {
  AppBar, Box, Button, Drawer, IconButton, List, ListItem, ListItemButton, ListItemText, Toolbar, Typography
} from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import { Link } from 'react-router';
import { useState } from 'react';
import AuthToggle from '../../auth/AuthToggle/AuthToggle';
import useAuth from '../../../../hooks/useAuth';

const pages = [
  { path: '/', name: 'Home', authenticated: false },
  { path: '/accommodations', name: 'Accommodations', authenticated: true },
  { path: '/hosts', name: 'Hosts', authenticated: true },
  { path: '/countries', name: 'Countries', authenticated: true }
];

const Header = () => {
  const [drawerOpen, setDrawerOpen] = useState(false);
  const { isLoggedIn } = useAuth();

  const visiblePages = pages.filter((page) => !page.authenticated || isLoggedIn);

  return (
    <Box>
      <AppBar position='static'>
        <Toolbar sx={{ display: 'flex' }}>
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
             ACCOMMODATIONS
          </Typography>

          <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
            {visiblePages.map((page) => (
              <Link key={page.name} to={page.path} style={{ textDecoration: 'none' }}>
                <Button className='header-button' sx={{ my: 2, color: 'white', display: 'block' }}>
                  {page.name}
                </Button>
              </Link>
            ))}
          </Box>

          <Box sx={{ flexGrow: 1, display: 'flex', justifyContent: 'flex-end' }}>
            <AuthToggle/>
          </Box>
        </Toolbar>
      </AppBar>

      <Drawer anchor='left' open={drawerOpen} onClose={() => setDrawerOpen(false)}>
        <Box sx={{ width: 240 }} role='presentation' onClick={() => setDrawerOpen(false)}>
          <List>
            {visiblePages.map((page) => (
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
