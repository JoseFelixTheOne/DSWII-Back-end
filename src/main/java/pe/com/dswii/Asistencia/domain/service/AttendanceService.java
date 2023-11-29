package pe.com.dswii.Asistencia.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.com.dswii.Asistencia.domain.Attendance;
import pe.com.dswii.Asistencia.domain.AttendanceDetail;
import pe.com.dswii.Asistencia.domain.dto.AttendanceDTO;
import pe.com.dswii.Asistencia.domain.dto.ScheduleDetailDTO;
import pe.com.dswii.Asistencia.domain.repository.AttendanceRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final ScheduleService scheduleService;

    @Transactional
    public Attendance save(AttendanceDTO attendanceDTO){
        Attendance attendance = new Attendance();
        BeanUtils.copyProperties(attendanceDTO, attendance);
        List<AttendanceDetail> details = new ArrayList<>();
        List<Integer> missingList = Arrays.asList(attendanceDTO.getMissing());
        List<ScheduleDetailDTO> sdDTOs = scheduleService.getByScheduleId(attendance.getScheduleId());
        for (int i=0; i < sdDTOs.size(); i++){
            ScheduleDetailDTO dto = sdDTOs.get(i);
            if (missingList.contains(dto.getStudentId())) {
                AttendanceDetail detail = new AttendanceDetail();
                detail.setDetailId((i+1));
                detail.setStudentId(dto.getStudentId());
                detail.setMissed(true);
                details.add(detail);
            } else {
                AttendanceDetail detail = new AttendanceDetail();
                detail.setDetailId((i+1));
                detail.setStudentId(dto.getStudentId());
                details.add(detail);
            }
        }
        attendance.setDetails(details);
        return attendanceRepository.save(attendance);
    }
}
