package med.myclinic.api.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "email", "cpf"})
public record PatientsDataList(
        @JsonProperty("nome") String name,
        String email,
        String cpf) {

    public PatientsDataList(Patient patient) {
        this(patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
