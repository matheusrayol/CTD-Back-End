package com.digitalhouse.clinicaodontologicag6.service.impl;

import com.digitalhouse.clinicaodontologicag6.repository.IDentistaRepository;
import com.digitalhouse.clinicaodontologicag6.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private IPacienteRepository pacienteRepository;
    @Autowired
    private IDentistaRepository dentistaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (pacienteRepository.findByUsername(username).isPresent()) {
            return pacienteRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("userService: Paciente não encontrado"));
        }
        if (dentistaRepository.findByUsername(username).isPresent()) {
            return dentistaRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("userService: Dentista não encontrado"));
        }
        return null;
    }
}
