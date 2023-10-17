package med.myclinic.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.myclinic.api.domain.address.Address;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    @Embedded
    private Address address;
    private Boolean status;

    public Patient(PatientsDataRecord patientsDataRecord) {
        this.name = patientsDataRecord.name();
        this.email = patientsDataRecord.email();
        this.phone = patientsDataRecord.phone();
        this.cpf = patientsDataRecord.cpf();
        this.address = new Address(patientsDataRecord.addressDataRecord());
        this.status = true;
    }

    public void updateData(PatientsDataUpdate data) {
        if (data.name() != null) {
            this.name = data.name();
        }

        if (data.phone() != null) {
            this.phone = data.phone();
        }

        if (data.addressDataRecord() != null) {
            this.address.updateData(data.addressDataRecord());
        }
    }

    public void delete() {
        this.status = false;
    }
}
