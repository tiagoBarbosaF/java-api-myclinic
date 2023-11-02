package med.myclinic.api.domain.appointment.validations.scheduling;

import med.myclinic.api.domain.appointment.AppointmentData;
import med.myclinic.api.domain.appointment.interfaces.IAppointmentSchedulingValidator;
import med.myclinic.api.exceptions.ValidException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ClinicOperationHoursValidator implements IAppointmentSchedulingValidator {

    public void validate(AppointmentData data){
        var appointmentDate = data.data();
        var sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var hourBeforeOpeningClinic = appointmentDate.getHour() < 7;
        var hourAfterEndingClinic = appointmentDate.getHour() > 18;

        if (sunday || hourBeforeOpeningClinic || hourAfterEndingClinic){
            throw new ValidException("Appointment out of hours of clinic operation.");
        }
    }
}
