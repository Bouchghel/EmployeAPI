package com.example.Client_Management;

import com.example.Client_Management.entity.Employe;
import com.example.Client_Management.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ClientManagementApplication implements CommandLineRunner {

	@Autowired
	private EmployeService employeService;

	public static void main(String[] args) {
		SpringApplication.run(ClientManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Chemins vers les fichiers
		File file1 = new File("C:\\Users\\ISMAIL\\Downloads\\Client_Management\\Client_Management\\src\\main\\java\\com\\example\\Client_Management\\EMSI.png");
		File file2 = new File("C:\\Users\\ISMAIL\\Downloads\\Client_Management\\Client_Management\\src\\main\\java\\com\\example\\Client_Management\\EMSI.png");

		// Convertir les fichiers en Base64
		String file1Base64 = FileUtils.encodeFileToBase64(file1);
		String file2Base64 = FileUtils.encodeFileToBase64(file2);

		// Créer des employés avec des fichiers dans les attributs
		Map<String, Object> attributes1 = new HashMap<>();
		attributes1.put("age", 30);
		attributes1.put("department", "Sales");
		attributes1.put("resume", file1);
		attributes1.put("post4l",file1Base64);
		Employe employe1 = new Employe(null, "kol", "JD", attributes1);

		Map<String, Object> attributes2 = new HashMap<>();
		attributes2.put("age", 25);
		attributes2.put("department", "HR");
		attributes2.put("profile_picture", file2Base64);
		Employe employe2 = new Employe(null, "koru", "JS", attributes2);

		// Enregistrer les employés
		employeService.saveEmploye(employe1);
		employeService.saveEmploye(employe2);

		// Récupérer et afficher tous les employés
		employeService.getAllEmployes().forEach(System.out::println);
	}
}
