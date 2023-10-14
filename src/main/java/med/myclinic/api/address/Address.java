package med.myclinic.api.address;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Address(@JsonAlias("logradouro") String publicPlace,
                      @JsonAlias("bairro") String Neighborhood,
                      @JsonAlias("cep") String zipCode,
                      @JsonAlias("cidade") String city,
                      @JsonAlias("uf") String uf,
                      @JsonAlias("numero") String number,
                      @JsonAlias("complemento") String complement) {
}
