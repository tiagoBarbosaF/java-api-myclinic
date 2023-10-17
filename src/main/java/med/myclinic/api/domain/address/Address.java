package med.myclinic.api.domain.address;

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

    public void updateData(AddressDataRecord addressDataRecord) {
        if (addressDataRecord.publicPlace() != null) {
            this.publicPlace = addressDataRecord.publicPlace();
        }

        if (addressDataRecord.neighborhood() != null) {
            this.neighborhood = addressDataRecord.neighborhood();
        }

        if (addressDataRecord.zipCode() != null) {
            this.zipCode = addressDataRecord.zipCode();
        }

        if (addressDataRecord.city() != null) {
            this.city = addressDataRecord.city();
        }

        if (addressDataRecord.uf() != null) {
            this.uf = addressDataRecord.uf();
        }

        if (addressDataRecord.number() != null) {
            this.number = addressDataRecord.number();
        }

        if (addressDataRecord.complement() != null) {
            this.complement = addressDataRecord.complement();
        }
    }
}
