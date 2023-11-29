package pe.com.dswii.Asistencia.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dswii.Asistencia.domain.Schedule;
import pe.com.dswii.Asistencia.domain.ScheduleDTO;
import pe.com.dswii.Asistencia.domain.ScheduleDetail;
import pe.com.dswii.Asistencia.domain.ScheduleDetailDTO;
import pe.com.dswii.Asistencia.domain.service.ScheduleService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/schedules")
@AllArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Schedule save(@RequestBody @Valid ScheduleDTO scheduleDTO) {
        return  scheduleService.save(scheduleDTO);
    }

    @GetMapping("teacher/{teacherId}")
    public ResponseEntity<List<Schedule>> getByProfessorId(@PathVariable Integer teacherId) {
        return ResponseEntity.of(scheduleService.getByProfessorId(teacherId));
    }

    @GetMapping("{id}")
    public ResponseEntity<Schedule> getById(@PathVariable Integer id) {
        return ResponseEntity.of(scheduleService.getById(id));
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Schedule> getAll() {
        return scheduleService.getAll();
    }

    @GetMapping("/detail/{scheduleId}")
    public ResponseEntity<List<ScheduleDetailDTO>> getDetailByScheduleId(@PathVariable int scheduleId) {
        List<ScheduleDetail> scheduleDetail = scheduleService.getByScheduleId(scheduleId);
        List<ScheduleDetailDTO> scheduleDetailDTO = new ArrayList<>();

        for (ScheduleDetail detail : scheduleDetail) {
            ScheduleDetailDTO detailDTO = new ScheduleDetailDTO();
            detailDTO.setId(detail.getDetailId());
            detailDTO.setStudentId(detail.getStudentId());
            detailDTO.setPersonEmail(detail.getStudent().getPersonEmail());
            detailDTO.setPersonName(detail.getStudent().getPersonName());
            detailDTO.setPersonLastname1(detail.getStudent().getPersonLastname1());
            detailDTO.setPersonLastname2(detail.getStudent().getPersonLastname2());
            scheduleDetailDTO.add(detailDTO);
        }
        return new ResponseEntity(scheduleDetailDTO, HttpStatus.OK);
    }
}
