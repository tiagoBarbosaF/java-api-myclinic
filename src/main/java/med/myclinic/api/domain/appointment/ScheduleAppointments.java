package med.myclinic.api.domain.appointment;

import med.myclinic.api.domain.appointment.interfaces.IAppointmentCancellationValidator;
import med.myclinic.api.domain.appointment.interfaces.IAppointmentRepository;
import med.myclinic.api.domain.appointment.interfaces.IAppointmentSchedulingValidator;
import med.myclinic.api.domain.doctor.Doctor;
import med.myclinic.api.domain.doctor.interfaces.IDoctorRepository;
import med.myclinic.api.domain.patient.interfaces.IPatientRepository;
import med.myclinic.api.exceptions.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleAppointments {

    private final IAppointmentRepository repository;
    private final IDoctorRepository doctorRepository;
    private final IPatientRepository patientRepository;
    private List<IAppointmentSchedulingValidator> validators;
    private List<IAppointmentCancellationValidator> cancellationValidators;

    @Autowired
    public ScheduleAppointments(IAppointmentRepository repository,
                                IDoctorRepository doctorRepository,
                                IPatientRepository patientRepository,
                                List<IAppointmentSchedulingValidator> validators,
                                List<IAppointmentCancellationValidator> cancellationValidators) {
        this.repository = repository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.validators = validators;
        this.cancellationValidators = cancellationValidators;
    }

    public AppointmentDetails schedule(AppointmentData data) {
        if (!patientRepository.existsById(data.idPatient())) {
            throw new ValidException("Patient id informed doesn't exist.");
        }

        if (data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())) {
            throw new ValidException("Doctor id informed doesn't exist.");
        }

        validators.forEach(v -> v.validate(data));

        var doctor = choiseDoctor(data);

        if (doctor == null) {
            throw new ValidException("There is no doctor available on this date.");
        }

        var patient = patientRepository.getReferenceById(data.idPatient());
        var appointment = new Appointment(null, doctor, patient, data.data(), null);

        repository.save(appointment);

        return new AppointmentDetails(appointment);
    }

    public void cancel(CancelSchedule data) {
        if (!repository.existsById(data.idAppointment())) {
            throw new ValidException("Appointment id doesn't exists.");
        }

        cancellationValidators.forEach(v -> v.validate(data));

        var appointment = repository.getReferenceById(data.idAppointment());
        appointment.cancel(data.cancellationReason());
    }

    private Doctor choiseDoctor(AppointmentData data) {
        if (data.idDoctor() != null) {
            return doctorRepository.getReferenceById(data.idDoctor());
        }

        if (data.specialty() == null) {
            throw new ValidException("Specialty field is required when doctor is not informed.");
        }


        return doctorRepository.choiceRandomDoctorFreeOnDate(data.specialty(), data.data());
    }
}
