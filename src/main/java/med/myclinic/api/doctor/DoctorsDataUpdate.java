package med.myclinic.api.doctor;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import med.myclinic.api.address.AddressDataRecord;

public record DoctorsDataUpdate(
        @NotNull Long id,
        @JsonProperty("nome") String name,
        @JsonProperty("telefone") String phone,
        @JsonProperty("endereco") AddressDataRecord addressDataRecord) {
}
