package med.myclinic.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String publicPlace;
    private String neighborhood;
    private String zipCode;
    private String city;
    private String uf;
    private String number;
    private String complement;

    public Address(AddressDataRecord addressDataRecord) {
        this.publicPlace = addressDataRecord.publicPlace();
        this.neighborhood = addressDataRecord.neighborhood();
        this.zipCode = addressDataRecord.zipCode();
        this.city = addressDataRecord.city();
        this.uf = addressDataRecord.uf();
        this.number = addressDataRecord.number();
        this.complement = addressDataRecord.complement();
    }
}
