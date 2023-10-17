package med.myclinic.api.domain.doctor;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.myclinic.api.domain.address.AddressDataRecord;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DoctorsDataRecords(
        @NotBlank @JsonAlias("nome") String name,
        @NotBlank @Email @JsonAlias("email") String email,
        @NotBlank @Pattern(regexp = "\\d{4,6}", message = "Field needs to have 4 to 6 characters.") @JsonAlias("crm") String crm,
        @NotBlank @JsonAlias("telefone") String phone,
        @NotNull @JsonAlias("especialidade") Specialty specialty,
        @NotNull @Valid @JsonAlias("endereco") AddressDataRecord addressDataRecord) {
}
