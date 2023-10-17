package med.myclinic.api.domain.patient;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.myclinic.api.domain.address.AddressDataRecord;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PatientsDataRecord(
        @NotBlank @JsonAlias("nome") String name,
        @NotBlank @Email @JsonAlias("email") String email,
        @NotBlank @JsonAlias("telefone") String phone,
        @NotBlank @Pattern(regexp = "\\d{11}", message = "Field cpf need has 11 characters.") @JsonAlias("cpf") String cpf,
        @NotNull @Valid @JsonAlias("endereco") AddressDataRecord addressDataRecord) {
}
