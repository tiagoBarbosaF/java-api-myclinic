package med.myclinic.api.controller;

import jakarta.validation.Valid;
import med.myclinic.api.domain.appointment.AppointmentData;
import med.myclinic.api.domain.appointment.AppointmentDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentDetails> schedule(@RequestBody @Valid AppointmentData data){
        System.out.println(data);
        return ResponseEntity.ok(new AppointmentDetails(null, null, null, null));
    }
}
