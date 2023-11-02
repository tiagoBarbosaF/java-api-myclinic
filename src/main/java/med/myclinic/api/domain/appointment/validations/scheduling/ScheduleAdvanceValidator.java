package med.myclinic.api.domain.appointment.validations.scheduling;

import med.myclinic.api.domain.appointment.AppointmentData;
import med.myclinic.api.domain.appointment.interfaces.IAppointmentSchedulingValidator;
import med.myclinic.api.exceptions.ValidException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ScheduleAdvanceValidatorAppointment")
public class ScheduleAdvanceValidator implements IAppointmentSchedulingValidator {

    public void validate(AppointmentData data){
        var appointmentDate = data.data();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now,appointmentDate).toMinutes();

        if (differenceInMinutes < 30){
            throw new ValidException("Consultation must be scheduled at least 30 minutes in advance.");
        }
    }
}
