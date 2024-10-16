package com.sample.springboot;

import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class PatientController {
    int lastPatientId = 0;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/getAllPatients")
    List<PatientInfo> all() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("patients.txt"));
        String line;
        List<PatientInfo> list = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            //System.out.println(line);

            String[] fields = line.split("\\|\\|");
            PatientInfo patientInfo = new PatientInfo();
            patientInfo.id = Integer.parseInt(fields[0]);
            patientInfo.name = fields[1];
            patientInfo.age = Integer.parseInt(fields[2]);
            patientInfo.allergies = fields[3];

            System.out.println(patientInfo.toString());

            list.add(patientInfo);
        }
        return list;
    }

    @GetMapping("/addPatient")
    void addPatient(@RequestParam String name, @RequestParam int age, @RequestParam String allergies) throws Exception {
        Random rand = new Random();
        int randomId = rand.nextInt(10000);

        String data = randomId + "||" + name + "||" + age + "||" + allergies;
        File file = new File("patients.txt");
        FileWriter fr = new FileWriter(file, true);
        PrintWriter pr = new PrintWriter(fr);
        pr.println(data);
        pr.close();
        fr.close();

        System.out.println("Added a new patient");
    }

    @GetMapping("/patients/{id}")
    PatientInfo getPatient(@PathVariable Integer id) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("patients.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);

            String[] fields = line.split("\\|\\|");
            int idField = Integer.getInteger(fields[0]);

            if(id == idField) {
                PatientInfo patientInfo = new PatientInfo();
                patientInfo.id = Integer.getInteger(fields[0]);
                patientInfo.name = fields[1];
                patientInfo.age = Integer.getInteger(fields[2]);
                patientInfo.allergies = fields[3];
                return patientInfo;
            }
        }
        return null;
    }

}