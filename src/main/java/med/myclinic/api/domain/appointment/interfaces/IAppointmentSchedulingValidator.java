package med.myclinic.api.domain.appointment.interfaces;

import med.myclinic.api.domain.appointment.AppointmentData;

public interface IAppointmentSchedulingValidator {
    void validate(AppointmentData data);
}
