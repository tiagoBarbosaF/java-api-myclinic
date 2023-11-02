package med.myclinic.api.domain.appointment.interfaces;

import med.myclinic.api.domain.appointment.CancelSchedule;

public interface IAppointmentCancellationValidator {
    void validate(CancelSchedule cancellation);
}
