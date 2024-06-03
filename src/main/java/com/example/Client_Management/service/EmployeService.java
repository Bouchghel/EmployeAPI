package com.example.Client_Management.service;

import com.example.Client_Management.entity.Employe;
import com.example.Client_Management.repo.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepository employeRepository;

    public Employe saveEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    public Employe getEmployeById(Long id) {
        Optional<Employe> optionalEmploye = employeRepository.findById(id);
        return optionalEmploye.orElse(null);
    }
}
