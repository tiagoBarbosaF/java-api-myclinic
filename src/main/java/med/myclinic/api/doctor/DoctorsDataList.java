package med.myclinic.api.doctor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "email", "crm", "specialty"})
public record DoctorsDataList(
        @JsonProperty("nome") String name,
        String email,
        String crm,
        @JsonProperty("especialidade") Specialty specialty) {

    public DoctorsDataList(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
