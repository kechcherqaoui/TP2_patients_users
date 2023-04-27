package com.enset.hospital;

import com.enset.hospital.entities.Consultation;
import com.enset.hospital.entities.Medecin;
import com.enset.hospital.entities.Patient;
import com.enset.hospital.entities.RendezVous;
import com.enset.hospital.entities.StatusRDV;
import com.enset.hospital.repositories.MedecinRepository;
import com.enset.hospital.repositories.PatientRepository;
import com.enset.hospital.repositories.RendezVousRepository;
import com.enset.hospital.service.IHospitalService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication2 {


	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication2.class, args);
	}

	@Bean
	CommandLineRunner start(IHospitalService hospitalService,
							PatientRepository patientRepository,
							MedecinRepository medecinRepository){
		return args -> {
			System.out.println("Sving patients");
			Stream.of("Mohamed", "Amine", "Najat")
					.forEach(name->{
						Patient patient = new Patient();
						patient.setNom(name);
						patient.setDateNaissance(new Date());
						hospitalService.savePatient(patient);
			});

			System.out.println("Saving medecins");
			Stream.of("Ayman", "Hanane", "Yassine")
					.forEach(name->{
						Medecin medecin = new Medecin();
						medecin.setNom(name);
						medecin.setEmail(name + "@gmail.com");
						medecin.setSpecialite(Math.random() > 0.5 ? "Cardio":"Dentiste");
						hospitalService.saveMedecin(medecin);
					});

			Patient patient = patientRepository.findById(1L).orElse(null);

			Medecin medecin = medecinRepository.findByNom("Yasmine");

			System.out.println("Saving rendez vous");
			RendezVous rendezVous = new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			RendezVous savedRendezvous = hospitalService.saveRendezVous(rendezVous);

			System.out.println("Saving consultation");
			Consultation consultation = new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(savedRendezvous);
			consultation.setRapport("Rapport de la consultation...");
			hospitalService.saveConsultation(consultation);
		};
	}
}
