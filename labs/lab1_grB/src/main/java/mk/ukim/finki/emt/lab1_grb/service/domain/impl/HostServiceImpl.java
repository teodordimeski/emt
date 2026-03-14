package mk.ukim.finki.emt.lab1_grb.service.domain.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.lab1_grb.model.domain.Host;
import mk.ukim.finki.emt.lab1_grb.repository.HostRepository;
import mk.ukim.finki.emt.lab1_grb.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HostServiceImpl implements HostService {

    HostRepository hostRepository;

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Host create(Host host) {
        return hostRepository.save(host);
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return hostRepository.findById(id).map(h -> {
        h.setName(host.getName());
        h.setSurname(host.getSurname());
        h.setCountry(host.getCountry());
        return hostRepository.save(h);
    });

    }

    @Override
    public Optional<Host> deleteById(Long id) {
        Optional<Host>  host= hostRepository.findById(id);
        host.ifPresent(hostRepository::delete);
        return host;
    }
}
