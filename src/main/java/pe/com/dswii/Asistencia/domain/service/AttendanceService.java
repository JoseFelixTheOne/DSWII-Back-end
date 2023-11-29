package pe.com.dswii.Asistencia.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.com.dswii.Asistencia.domain.Attendance;
import pe.com.dswii.Asistencia.domain.repository.AttendanceRepository;

@Service
@AllArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    @Transactional
    public Attendance save(Attendance attendance){
        return attendanceRepository.save(attendance);
    }
}
