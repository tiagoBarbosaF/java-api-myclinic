package med.myclinic.api.domain.appointment.interfaces;

import med.myclinic.api.domain.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndDataAndCancellationReasonIsNull(Long idDoctor, LocalDateTime date);

    boolean existsByPatientIdAndDataBetween(Long idPatient, LocalDateTime firstHour, LocalDateTime lastHour);
}
