package med.myclinic.api.domain.doctor.interfaces;

import med.myclinic.api.domain.address.AddressDataRecord;
import med.myclinic.api.domain.appointment.Appointment;
import med.myclinic.api.domain.doctor.Doctor;
import med.myclinic.api.domain.doctor.DoctorsDataRecords;
import med.myclinic.api.domain.doctor.Specialty;
import med.myclinic.api.domain.patient.Patient;
import med.myclinic.api.domain.patient.PatientsDataRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class IDoctorRepositoryTest {

    @Autowired
    private IDoctorRepository doctorRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("It should return null when the only registered doctor is not available on the date.")
    void choiceRandomDoctorFreeOnDateScenario1() {
        var nextMondayAt10 = appointmentNextMondayAt10();

        var doctor = recordDoctorTest("Doctor", "doctor@email.com", "123445", Specialty.CARDIOLOGY);
        var patient = recordPatientTest("Patient", "patient@email.com", "12345678900");
        recordAppointmentTest(doctor, patient, nextMondayAt10);

        var freeDoctor = doctorRepository.choiceRandomDoctorFreeOnDate(Specialty.CARDIOLOGY, nextMondayAt10);
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("Should return doctor when he is available on the date.")
    void choiceRandomDoctorFreeOnDateScenario2() {
        var nextMondayAt10 = appointmentNextMondayAt10();

        var doctor = recordDoctorTest("Doctor", "doctor@email.com", "123456", Specialty.CARDIOLOGY);
        var freeDoctor = doctorRepository.choiceRandomDoctorFreeOnDate(Specialty.CARDIOLOGY, nextMondayAt10);

        assertThat(freeDoctor).isEqualTo(doctor);
    }

    private static LocalDateTime appointmentNextMondayAt10() {
        return LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
    }

    private void recordAppointmentTest(Doctor doctor, Patient patient, LocalDateTime dateTime) {
        entityManager.persist(new Appointment(null, doctor, patient, dateTime, null));
    }

    private Doctor recordDoctorTest(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(doctorsDataRecordsTest(name, email, crm, specialty));
        entityManager.persist(doctor);
        return doctor;
    }

    private Patient recordPatientTest(String name, String email, String cpf) {
        var patient = new Patient(patientsDataRecordTest(name, email, cpf));
        entityManager.persist(patient);
        return patient;
    }

    private DoctorsDataRecords doctorsDataRecordsTest(String name, String email, String crm, Specialty specialty) {
        return new DoctorsDataRecords(name, email, crm, "82999992222", specialty, addressDataRecordTest());
    }

    private PatientsDataRecord patientsDataRecordTest(String name, String email, String cpf) {
        return new PatientsDataRecord(name, email, "88999991111", cpf, addressDataRecordTest());
    }

    private AddressDataRecord addressDataRecordTest() {
        return new AddressDataRecord("Street 1", "Neighborhood", "00000000", "City 1", "C1", null, null);
    }
}