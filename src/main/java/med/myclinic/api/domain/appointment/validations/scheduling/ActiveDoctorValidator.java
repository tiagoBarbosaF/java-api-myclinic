package med.myclinic.api.domain.appointment.validations.scheduling;

import med.myclinic.api.domain.appointment.AppointmentData;
import med.myclinic.api.domain.appointment.interfaces.IAppointmentSchedulingValidator;
import med.myclinic.api.domain.doctor.interfaces.IDoctorRepository;
import med.myclinic.api.exceptions.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidator implements IAppointmentSchedulingValidator {

    private IDoctorRepository doctorRepository;

    @Autowired
    public ActiveDoctorValidator(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void validate(AppointmentData data){
        if (data.idDoctor() == null){
            return;
        }

        var doctorIsActive = doctorRepository.findStatusById(data.idDoctor());

        if (!doctorIsActive){
            throw new ValidException("Consultation cannot be scheduled with an inactive doctor.");
        }
    }
}
