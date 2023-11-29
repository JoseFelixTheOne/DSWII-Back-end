package pe.com.dswii.Asistencia.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.Attendance;
import pe.com.dswii.Asistencia.domain.dto.AttendanceDTO;
import pe.com.dswii.Asistencia.domain.service.AttendanceService;

@RestController
@RequestMapping("attendances")
@AllArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Attendance save(@RequestBody AttendanceDTO attendanceDTO) {
        return attendanceService.save(attendanceDTO);
    }
}
