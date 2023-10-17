package med.myclinic.api.domain.doctor.interfaces;

import med.myclinic.api.domain.doctor.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByStatusTrue(Pageable pagination);
}
