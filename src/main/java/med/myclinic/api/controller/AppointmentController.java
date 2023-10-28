package med.myclinic.api.controller;

import jakarta.validation.Valid;
import med.myclinic.api.domain.appointment.AppointmentData;
import med.myclinic.api.domain.appointment.AppointmentDetails;
import med.myclinic.api.domain.appointment.CancelSchedule;
import med.myclinic.api.domain.appointment.ScheduleAppointments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    private ScheduleAppointments scheduleAppointments;

    @Autowired
    public AppointmentController(ScheduleAppointments scheduleAppointments) {
        this.scheduleAppointments = scheduleAppointments;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentDetails> schedule(@RequestBody @Valid AppointmentData data) {

        return ResponseEntity.ok(new AppointmentDetails(null, null, null, null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<AppointmentDetails> cancel(@RequestBody @Valid CancelSchedule data){
        scheduleAppointments.cancel(data);
        return ResponseEntity.noContent().build();
    }
}
