package med.myclinic.api.domain.appointment.interfaces;

import med.myclinic.api.domain.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
}
