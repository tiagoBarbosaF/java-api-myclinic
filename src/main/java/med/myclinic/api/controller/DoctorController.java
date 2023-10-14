package med.myclinic.api.controller;

import jakarta.validation.Valid;
import med.myclinic.api.doctor.Doctor;
import med.myclinic.api.doctor.DoctorsDataRecords;
import med.myclinic.api.doctor.interfaces.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private IDoctorRepository repository;

    @PostMapping
    @Transactional
    public void record(@RequestBody @Valid DoctorsDataRecords data){
        repository.save(new Doctor(data));
    }
}
