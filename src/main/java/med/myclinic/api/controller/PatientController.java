package med.myclinic.api.controller;

import jakarta.validation.Valid;
import med.myclinic.api.patient.Patient;
import med.myclinic.api.patient.PatientsDataRecord;
import med.myclinic.api.patient.interfaces.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private IPatientRepository repository;

    @PostMapping
    @Transactional
    public void record(@RequestBody @Valid PatientsDataRecord data) {
        repository.save(new Patient(data));
    }
}
