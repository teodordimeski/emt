import './App.css';
import { BrowserRouter, Outlet, Route, Routes } from 'react-router';
import Layout from './ui/components/layout/Layout/Layout.tsx';
import HomePage from './ui/pages/HomePage/HomePage.tsx';
import AccommodationsPage from './ui/pages/AccommodationsPage/AccommodationsPage.tsx';
import HostsPage from './ui/pages/HostsPage/HostsPage.tsx';
import CountriesPage from './ui/pages/CountriesPage/CountriesPage.tsx';
import AccommodationDetailsPage from './ui/pages/AccommodationDetailsPage/AccommodationDetailsPage.tsx';
import HostDetailsPage from './ui/pages/HostDetailsPage/HostDetailsPage.tsx';
import CountryDetailsPage from './ui/pages/CountryDetailsPage/CountryDetailsPage.tsx';
import LoginPage from './ui/pages/LoginPage/LoginPage.tsx';
import RegisterPage from './ui/pages/RegisterPage/RegisterPage.tsx';
import HostsProvider from './providers/hostsProvider.tsx';
import AccommodationsProvider from './providers/accommodationsProvider.tsx';
import CountriesProvider from './providers/countriesProvider.tsx';
import ProtectedRoute from './ui/components/routing/ProtectedRoute/ProtectedRoute.tsx';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/register' element={<RegisterPage/>}/>
        <Route path='/login' element={<LoginPage/>}/>
        <Route path='/' element={<Layout/>}>
          <Route index element={<HomePage/>}/>
          <Route element={<ProtectedRoute/>}>
            <Route
              element={(
                <HostsProvider>
                  <CountriesProvider>
                    <AccommodationsProvider>
                      <Outlet/>
                    </AccommodationsProvider>
                  </CountriesProvider>
                </HostsProvider>
              )}
            >
              <Route path='accommodations' element={<AccommodationsPage/>}/>
              <Route path='accommodations/:id' element={<AccommodationDetailsPage/>}/>
              <Route path='hosts' element={<HostsPage/>}/>
              <Route path='hosts/:id' element={<HostDetailsPage/>}/>
              <Route path='countries' element={<CountriesPage/>}/>
              <Route path='countries/:id' element={<CountryDetailsPage/>}/>
            </Route>
          </Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
