package med.myclinic.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

@JsonPropertyOrder({"id","idDoctor","idPatient","data"})
public record AppointmentDetails(
        Long id,
        @JsonProperty("medico_id") Long idDoctor,
        @JsonProperty("paciente_id") Long idPatient,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime data) {
}
