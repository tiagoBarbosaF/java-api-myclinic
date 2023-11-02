package med.myclinic.api.domain.appointment.validations.scheduling;

import med.myclinic.api.domain.appointment.AppointmentData;
import med.myclinic.api.domain.appointment.interfaces.IAppointmentSchedulingValidator;
import med.myclinic.api.domain.patient.interfaces.IPatientRepository;
import med.myclinic.api.exceptions.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements IAppointmentSchedulingValidator {

    private IPatientRepository patientRepository;

    @Autowired
    public ActivePatientValidator(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void validate(AppointmentData data){
        var patientIsActive = patientRepository.findStatusById(data.idPatient());

        if (!patientIsActive){
            throw new ValidException("Consultation cannot be schedule for an inactive patient.");
        }
    }
}
