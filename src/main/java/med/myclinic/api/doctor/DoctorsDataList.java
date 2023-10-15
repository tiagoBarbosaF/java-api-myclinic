package med.myclinic.api.doctor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "name", "email", "crm", "specialty"})
public record DoctorsDataList(
        Long id,
        @JsonProperty("nome") String name,
        String email,
        String crm,
        @JsonProperty("especialidade") Specialty specialty) {

    public DoctorsDataList(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
