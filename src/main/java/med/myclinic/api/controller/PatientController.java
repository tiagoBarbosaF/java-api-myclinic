package med.myclinic.api.controller;

import jakarta.validation.Valid;
import med.myclinic.api.patient.Patient;
import med.myclinic.api.patient.PatientsDataList;
import med.myclinic.api.patient.PatientsDataRecord;
import med.myclinic.api.patient.interfaces.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public Page<PatientsDataList> listPatients(@PageableDefault(size = 10, sort = {"name"})Pageable pagination){
        return repository.findAll(pagination).map(PatientsDataList::new);
    }
}
