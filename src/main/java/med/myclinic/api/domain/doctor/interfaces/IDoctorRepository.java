package med.myclinic.api.domain.doctor.interfaces;

import med.myclinic.api.domain.doctor.Doctor;
import med.myclinic.api.domain.doctor.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByStatusTrue(Pageable pagination);

    @Query("""
             select d from Doctor d
             where
             d.status = true
             and d.specialty = :specialty
             and d.id not in (
                 select a.doctor.id from Appointment a
                 where a.data = :data
             )
             order by rand()
             limit 1
            """)
    Doctor choiceRandomDoctorFreeOnDate(Specialty specialty, LocalDateTime data);
}
