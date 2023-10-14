package med.myclinic.api.address;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressDataRecord(
        @NotBlank @JsonAlias("logradouro") String publicPlace,
        @NotBlank @JsonAlias("bairro") String neighborhood,
        @NotBlank @Pattern(regexp = "\\d{8}") @JsonAlias("cep") String zipCode,
        @NotBlank @JsonAlias("cidade") String city,
        @NotBlank @JsonAlias("uf") String uf,
        @JsonAlias("numero") String number,
        @JsonAlias("complemento") String complement) {
}
