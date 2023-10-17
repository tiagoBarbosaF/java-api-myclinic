package med.myclinic.api.domain.patient.interfaces;

import med.myclinic.api.domain.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByStatusTrue(Pageable pagination);
}
