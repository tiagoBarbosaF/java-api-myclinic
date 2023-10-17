package med.myclinic.api.domain.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import med.myclinic.api.domain.address.AddressDataRecord;

public record PatientsDataUpdate(
        @NotNull Long id,
        @JsonProperty("nome") String name,
        @JsonProperty("telefone") String phone,
        @JsonProperty("endereco") AddressDataRecord addressDataRecord) {
}
