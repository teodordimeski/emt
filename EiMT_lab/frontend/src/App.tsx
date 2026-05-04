import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router';
import Layout from './ui/components/layout/Layout/Layout.tsx';
import HomePage from './ui/pages/HomePage/HomePage.tsx';
import AccommodationsPage from './ui/pages/AccommodationsPage/AccommodationsPage.tsx';
import HostsPage from './ui/pages/HostsPage/HostsPage.tsx';
import CountriesPage from './ui/pages/CountriesPage/CountriesPage.tsx';
import UsersPage from './ui/pages/UsersPage/UsersPage.tsx';
import AccommodationDetailsPage from './ui/pages/AccommodationDetailsPage/AccommodationDetailsPage.tsx';
import HostDetailsPage from './ui/pages/HostDetailsPage/HostDetailsPage.tsx';
import CountryDetailsPage from './ui/pages/CountryDetailsPage/CountryDetailsPage.tsx';
import UserDetailsPage from './ui/pages/UserDetailsPage/UserDetailsPage.tsx';
import LoginPage from './ui/pages/LoginPage/LoginPage.tsx';
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Layout/>}>
        <Route path="/login" element={<LoginPage />} />
          <Route index element={<HomePage/>}/>
          <Route path='accommodations' element={<AccommodationsPage/>}/>
          <Route path='accommodations/:id' element={<AccommodationDetailsPage/>}/>
          <Route path='hosts' element={<HostsPage/>}/>
          <Route path='hosts/:id' element={<HostDetailsPage/>}/>
          <Route path='countries' element={<CountriesPage/>}/>
          <Route path='countries/:id' element={<CountryDetailsPage/>}/>
          <Route path='users' element={<UsersPage/>}/>
          <Route path='users/:id' element={<UserDetailsPage/>}/>
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;

