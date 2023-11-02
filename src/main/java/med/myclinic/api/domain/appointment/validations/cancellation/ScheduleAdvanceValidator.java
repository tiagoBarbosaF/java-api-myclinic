package med.myclinic.api.domain.appointment.validations.cancellation;

import med.myclinic.api.domain.appointment.CancelSchedule;
import med.myclinic.api.domain.appointment.interfaces.IAppointmentCancellationValidator;
import med.myclinic.api.domain.appointment.interfaces.IAppointmentRepository;
import med.myclinic.api.exceptions.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ScheduleAdvanceValidatorCancellation")
public class ScheduleAdvanceValidator implements IAppointmentCancellationValidator {

    private IAppointmentRepository appointmentRepository;

    @Autowired
    public ScheduleAdvanceValidator(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void validate(CancelSchedule cancellation) {
        var appointment = appointmentRepository.getReferenceById(cancellation.idAppointment());
        var now = LocalDateTime.now();
        var differenceInHours = Duration.between(now, appointment.getData()).toHours();

        if (differenceInHours < 24) {
            throw new ValidException("Appointment must be cancelled with advanced minimum of 24 hours.");
        }
    }
}
