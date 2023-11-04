package med.myclinic.api.controller;

import med.myclinic.api.domain.address.Address;
import med.myclinic.api.domain.address.AddressDataRecord;
import med.myclinic.api.domain.doctor.Doctor;
import med.myclinic.api.domain.doctor.DoctorsDataRecords;
import med.myclinic.api.domain.doctor.DoctorsDetailsData;
import med.myclinic.api.domain.doctor.Specialty;
import med.myclinic.api.domain.doctor.interfaces.IDoctorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<DoctorsDataRecords> doctorsDataRecordsJson;
    @Autowired
    private JacksonTester<DoctorsDetailsData> doctorsDetailsJson;
    @MockBean
    private IDoctorRepository doctorRepository;

    @Test
    @DisplayName("Should return the http code 400 when the fields are invalid")
    void recordDoctorScenario1() throws Exception {
        var response = mockMvc.perform(post("/doctors"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return the http code 200 when the fields are valid")
    void recordDoctorScenario2() throws Exception {
        var dataDetails = new DoctorsDetailsData(null, "Doctor", "doctor@email.com", "123456", "11999993333",
                Specialty.CARDIOLOGY, true, new Address(addressDataRecordTest()));
        var dataRecord = new DoctorsDataRecords("Doctor", "doctor@email.com", "123456", "11999993333",
                Specialty.CARDIOLOGY, addressDataRecordTest());

        when(doctorRepository.save(any())).thenReturn(new Doctor(dataRecord));

        var response = mockMvc.perform(post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(doctorsDataRecordsJson
                                .write(dataRecord).getJson()))
                .andReturn().getResponse();

        var expectedJson = doctorsDetailsJson
                .write(dataDetails).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }

    private AddressDataRecord addressDataRecordTest() {
        return new AddressDataRecord("Street 1", "Neighborhood", "00000000", "City 1", "C1", null, null);
    }
}