package med.myclinic.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.myclinic.api.domain.doctor.*;
import med.myclinic.api.domain.doctor.interfaces.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    private final IDoctorRepository repository;

    @Autowired
    public DoctorController(IDoctorRepository repository) {
        this.repository = repository;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<DoctorsDetailsData> record(@RequestBody @Valid DoctorsDataRecords data, UriComponentsBuilder uriBuilder) {
        var doctor = new Doctor(data);

        repository.save(doctor);

        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorsDetailsData(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorsDataList>> listDoctors(@PageableDefault(size = 100, sort = {"id"}) Pageable pagination) {
        var page = repository.findAllByStatusTrue(pagination).map(DoctorsDataList::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorsDetailsData> detailDoctors(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);

        return ResponseEntity.ok(new DoctorsDetailsData(doctor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorsDetailsData> update(@RequestBody @Valid DoctorsDataUpdate data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);

        return ResponseEntity.ok(new DoctorsDetailsData(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();

        return ResponseEntity.noContent().build();
    }
}
