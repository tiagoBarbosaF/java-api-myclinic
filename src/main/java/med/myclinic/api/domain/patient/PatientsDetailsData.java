package med.myclinic.api.domain.patient;

import med.myclinic.api.domain.address.Address;

public record PatientsDetailsData(
        Long id,
        String name,
        String email,
        String phone,
        String cpf,
        Boolean status,
        Address address) {

    public PatientsDetailsData(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getCpf(), patient.getStatus(), patient.getAddress());
    }
}
