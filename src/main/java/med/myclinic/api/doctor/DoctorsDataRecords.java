package med.myclinic.api.doctor;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import med.myclinic.api.address.Address;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DoctorsDataRecords(@JsonAlias("nome") String name,
                                 @JsonAlias("email") String email,
                                 @JsonAlias("crm") String crm,
                                 @JsonAlias("especialidade") Specialty specialty,
                                 @JsonAlias("endereco") Address address) {
}
