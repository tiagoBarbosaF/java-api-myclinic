package med.myclinic.api.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.myclinic.api.address.Address;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String crm;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;

    public Doctor(DoctorsDataRecords doctorsDataRecords) {
        this.name = doctorsDataRecords.name();
        this.email = doctorsDataRecords.email();
        this.crm = doctorsDataRecords.crm();
        this.phone = doctorsDataRecords.phone();
        this.specialty = doctorsDataRecords.specialty();
        this.address = new Address(doctorsDataRecords.addressDataRecord());
    }
}
