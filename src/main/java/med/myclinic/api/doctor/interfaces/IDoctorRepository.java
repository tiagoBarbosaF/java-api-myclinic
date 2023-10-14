package med.myclinic.api.doctor.interfaces;

import med.myclinic.api.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
}
