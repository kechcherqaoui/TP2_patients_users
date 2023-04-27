package com.enset.hospital;

import com.enset.hospital.entities.Patient;
import com.enset.hospital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PatientApplication implements CommandLineRunner {

	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Saving Patients...");
		Patient patient = new Patient();
		patient.setNom("Yassin");
		patient.setDateNaissance(new Date());
		patient.setScore(15);
		patientRepository.save(patient);

		Patient patient2 = new Patient();
		patient2.setNom("Ayoub");
		patient2.setDateNaissance(new Date());
		patient2.setScore(13);
		patientRepository.save(patient2);


		Patient patient3 = new Patient();
		patient3.setNom("Amine");
		patient3.setDateNaissance(new Date());
		patient3.setScore(19);
		patientRepository.save(patient3);

		System.out.println("Getting patients...");
		List<Patient> patients = patientRepository.findAll();
		patients.forEach(System.out::println);

		System.out.println("Getting patient with id: 1");
		Patient chosenPatient = patientRepository.findById(1L).orElse(null);
		System.out.println("Patient: " + chosenPatient);

		System.out.println("Updating patient with id 1");
		chosenPatient.setNom("Anas");
		chosenPatient.setScore(17);
		chosenPatient.setMalade(true);
		patientRepository.save(chosenPatient);

		System.out.println("Removing patient with id 3");
		Patient chosenPatient2 = patientRepository.findById(3L).orElse(null);
		if (chosenPatient2 != null)
			patientRepository.delete(chosenPatient2);

		System.out.println("Getting patients...");
		List<Patient> patients2 = patientRepository.findAll();
		patients2.forEach(System.out::println);
	}
}
