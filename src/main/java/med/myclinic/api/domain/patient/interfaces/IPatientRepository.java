package med.myclinic.api.domain.patient.interfaces;

import med.myclinic.api.domain.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByStatusTrue(Pageable pagination);

    @Query("""
            select p.status
            from Patient p
            where
            p.id = :idPatient
            """)
    Boolean findStatusById(Long idPatient);
}
