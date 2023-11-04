package med.myclinic.api.controller;

import med.myclinic.api.domain.appointment.AppointmentData;
import med.myclinic.api.domain.appointment.AppointmentDetails;
import med.myclinic.api.domain.appointment.ScheduleAppointments;
import med.myclinic.api.domain.doctor.Specialty;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<AppointmentData> appointmentDataJson;
    @Autowired
    private JacksonTester<AppointmentDetails> appointmentDetailsJson;
    @MockBean
    private ScheduleAppointments scheduleAppointments;

    @Test
    @DisplayName("Should return the http code 400 when the fields are invalid")
    void scheduleScenario1() throws Exception {
        var response = mockMvc.perform(post("/appointments"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return the http code 200 when fields are valid")
    void scheduleScenario2() throws Exception {
        var date = LocalDateTime.now().plusHours(2);
        var dataDetails = new AppointmentDetails(null, 2L, 5L, date);

        when(scheduleAppointments.schedule(any())).thenReturn(dataDetails);

        var response = mockMvc
                .perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentDataJson
                                .write(new AppointmentData(2L, Specialty.CARDIOLOGY, 5L, date)).getJson())
                )
                .andReturn().getResponse();

        var expectedJson = appointmentDetailsJson
                .write(dataDetails).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}