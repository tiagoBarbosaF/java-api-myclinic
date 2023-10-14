package med.myclinic.api.patient.interfaces;

import med.myclinic.api.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository<Patient, Long> {
}
