package med.myclinic.api.domain.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "name", "email", "cpf"})
public record PatientsDataList(
        Long id,
        @JsonProperty("nome") String name,
        String email,
        String cpf) {

    public PatientsDataList(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
