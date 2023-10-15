package med.myclinic.api.controller;

import jakarta.validation.Valid;
import med.myclinic.api.doctor.Doctor;
import med.myclinic.api.doctor.DoctorsDataList;
import med.myclinic.api.doctor.DoctorsDataRecords;
import med.myclinic.api.doctor.DoctorsDataUpdate;
import med.myclinic.api.doctor.interfaces.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private IDoctorRepository repository;

    @PostMapping
    @Transactional
    public void record(@RequestBody @Valid DoctorsDataRecords data) {
        repository.save(new Doctor(data));
    }

    @GetMapping
    public Page<DoctorsDataList> listDoctors(@PageableDefault(size = 100, sort = {"id"}) Pageable pagination) {
        return repository.findAllByStatusTrue(pagination).map(DoctorsDataList::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DoctorsDataUpdate data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();
    }
}
