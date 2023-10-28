package med.myclinic.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.myclinic.api.domain.doctor.Specialty;

import java.time.LocalDateTime;

public record AppointmentData(
        @JsonAlias("medico_id") Long idDoctor,
        @JsonAlias("especialidade") Specialty specialty,
        @NotNull @JsonAlias("paciente_id") Long idPatient,
        @NotNull @Future @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime data) {
}
