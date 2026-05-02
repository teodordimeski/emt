import './HostsPage.css';
import useHosts from '../../../hooks/useHosts';
import { Box, CircularProgress } from '@mui/material';
import HostGrid from '../../components/host/HostGrid/HostGrid.tsx';

const HostsPage = () => {
  const { hosts, loading } = useHosts();

  return (
    <Box className='hosts-box'>
      {loading && (
        <Box className='progress-box'>
          <CircularProgress/>
        </Box>
      )}
      {!loading && <HostGrid hosts={hosts}/>}
    </Box>
  );
};

export default HostsPage;

