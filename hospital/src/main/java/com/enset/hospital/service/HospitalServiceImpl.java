package com.enset.hospital.service;


import com.enset.hospital.entities.Consultation;
import com.enset.hospital.entities.Medecin;
import com.enset.hospital.entities.Patient;
import com.enset.hospital.entities.RendezVous;
import com.enset.hospital.repositories.ConsultationRepository;
import com.enset.hospital.repositories.MedecinRepository;
import com.enset.hospital.repositories.PatientRepository;
import com.enset.hospital.repositories.RendezVousRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class HospitalServiceImpl implements IHospitalService {
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
        rendezVous.setId(UUID.randomUUID().toString());
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}
