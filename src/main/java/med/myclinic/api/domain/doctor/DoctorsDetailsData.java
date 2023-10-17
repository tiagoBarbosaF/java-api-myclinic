package med.myclinic.api.domain.doctor;

import med.myclinic.api.domain.address.Address;

public record DoctorsDetailsData(
        Long id,
        String name,
        String email,
        String crm,
        String phone,
        Specialty specialty,
        Boolean status,
        Address address) {

    public DoctorsDetailsData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getPhone(),
                doctor.getSpecialty(), doctor.getStatus(), doctor.getAddress());
    }
}
