package med.myclinic.api.domain.appointment.validations.scheduling;

import med.myclinic.api.domain.appointment.AppointmentData;
import med.myclinic.api.domain.appointment.interfaces.IAppointmentRepository;
import med.myclinic.api.domain.appointment.interfaces.IAppointmentSchedulingValidator;
import med.myclinic.api.exceptions.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorWithAnotherAppointmentAtTheSameTimeValidator implements IAppointmentSchedulingValidator {

    private IAppointmentRepository appointmentRepository;

    @Autowired
    public DoctorWithAnotherAppointmentAtTheSameTimeValidator(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void validate(AppointmentData data) {
        var doctorHasAnotherAppointmentAtTheSameTime = appointmentRepository.existsByDoctorIdAndDataAndCancellationReasonIsNull(data.idDoctor(),
                data.data());

        if (doctorHasAnotherAppointmentAtTheSameTime) {
            throw new ValidException("Doctor already has another appointment scheduled at the same time.");
        }
    }
}
