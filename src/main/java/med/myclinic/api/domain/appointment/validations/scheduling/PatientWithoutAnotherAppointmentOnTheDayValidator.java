package med.myclinic.api.domain.appointment.validations.scheduling;

import med.myclinic.api.domain.appointment.AppointmentData;
import med.myclinic.api.domain.appointment.interfaces.IAppointmentRepository;
import med.myclinic.api.domain.appointment.interfaces.IAppointmentSchedulingValidator;
import med.myclinic.api.exceptions.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientWithoutAnotherAppointmentOnTheDayValidator implements IAppointmentSchedulingValidator {

    private IAppointmentRepository appointmentRepository;

    @Autowired
    public PatientWithoutAnotherAppointmentOnTheDayValidator(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void validate(AppointmentData data){
        var firstHour = data.data().withHour(7);
        var lastHour = data.data().withHour(18);
        var patientHasAnotherAppointmentOnTHeDay =
                appointmentRepository.existsByPatientIdAndDataBetween(data.idPatient(),firstHour,lastHour);

        if (patientHasAnotherAppointmentOnTHeDay){
            throw new ValidException("Patient already has an appointment scheduled for that day.");
        }
    }
}
