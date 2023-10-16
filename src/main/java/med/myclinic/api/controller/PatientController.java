package med.myclinic.api.controller;

import jakarta.validation.Valid;
import med.myclinic.api.patient.*;
import med.myclinic.api.patient.interfaces.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
public class PatientController {

    private final IPatientRepository repository;

    @Autowired
    public PatientController(IPatientRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PatientsDetailsData> record(@RequestBody @Valid PatientsDataRecord data,
                                                      UriComponentsBuilder uriBuilder) {
        var patient = new Patient(data);

        repository.save(patient);

        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientsDetailsData(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientsDataList>> listPatients(@PageableDefault(size = 100, sort = {"name"}) Pageable pagination) {
        var page = repository.findAllByStatusTrue(pagination).map(PatientsDataList::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientsDetailsData> detailPatients(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);

        return ResponseEntity.ok(new PatientsDetailsData(patient));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PatientsDetailsData> update(@RequestBody @Valid PatientsDataUpdate data) {
        var patient = repository.getReferenceById(data.id());
        patient.updateData(data);

        return ResponseEntity.ok(new PatientsDetailsData(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.delete();

        return ResponseEntity.noContent().build();
    }
}
