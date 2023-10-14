package med.myclinic.api.controller;

import med.myclinic.api.doctor.DoctorsDataRecords;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @PostMapping
    public void record(@RequestBody DoctorsDataRecords data){
        System.out.println(data);
    }
}
