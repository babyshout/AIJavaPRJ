package kopo.poly.service.impl;


import kopo.poly.dto.StudentDTO;
import kopo.poly.persistance.mapper.IStudentMapper;
import kopo.poly.service.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService implements IStudentService {
    
    private final IStudentMapper studentMapper;  //오라클 DB와 연결된 Mapper
    
    @Override
    public List<StudentDTO> insertStudent(StudentDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".insertStudent Strat!");
        
        //Student 테이블에 등록된 학생 아이디가 존재하는지 체크하기 위해 DB 조회하기
        Optional<StudentDTO> res = Optional.ofNullable(
                studentMapper.getStudent(pDTO)
        );
        
        if (!res.isPresent()) { //DB 조회 결과로 회원아이디가 존재하지 않는다면...
            studentMapper.insertStudent(pDTO);  //학생 등록 SQL 실행하기
        }
        
        //학생 테이블 전체 조회하기
        List<StudentDTO> rList = Optional.ofNullable(
                studentMapper.getStudentList()
        ).orElseGet(ArrayList::new);
        
        log.info(this.getClass().getName() + ".insertStudent End!");
        
        return rList;
    }
    
    @Override
    public void insertStudentList(List<StudentDTO> pList) throws Exception {
        log.info(this.getClass().getName() + ".insertStudentList Start!");
        
        pList.forEach(dto -> {
            try {
                this.insertStudent(dto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        
//        pList.forEach(dto -> {
//            try {
//                Optional<StudentDTO> res = Optional.ofNullable(
//                        studentMapper.getStudent(dto)
//                );
//
//                if(res.isPresent()) {
//                    pList.remove(dto);
//                }
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//
//        });
//
//        pList.forEach(dto -> {
//            try {
//                studentMapper.insertStudent(dto);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        });
        
        
        log.info(this.getClass().getName() + ".insertStudentList End!");
    }
    
    @Override
    public void deleteStudent(StudentDTO pDTO) throws Exception {
        
        log.info(this.getClass().getName() + ".deleteStudent Start!");
        
        Optional<StudentDTO> res = Optional.ofNullable(
                studentMapper.getStudent(pDTO)
        );
        
        if(res.isPresent()){
            studentMapper.deleteStudent(pDTO);
            log.info(pDTO.getUserId() + "is deleted");
        } else {
            log.info(pDTO.getUserId() + "is not deleted");
        }
        
        
        
        log.info(this.getClass().getName() + ".deleteStudent is end!");
        
    }
    
    @Override
    public void deleteStudentList(List<StudentDTO> pList) throws Exception {
        log.info(this.getClass().getName() + ".deleteStudent Start!");
        
        pList.forEach(dto -> {
            try {
                this.deleteStudent(dto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
//        pList.forEach(dto -> {
//            try {
//                Optional<StudentDTO> res = Optional.ofNullable(
//                        studentMapper.getStudent(dto)
//                );
//
//                if(!res.isPresent()) {
//                    pList.remove(dto);
//                }
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//
//
//        });
//
//        pList.forEach(dto -> {
//            try {
//                studentMapper.deleteStudent(dto);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        });
        
        
        log.info(this.getClass().getName() + ".deleteStudent is end!");
    }
    
    @Override
    public List<StudentDTO> getStudentList() throws Exception {
        
        log.info(this.getClass().getName() + ".getStudentList Start!");
        
        
        List<StudentDTO> rList = Optional.ofNullable(
                studentMapper.getStudentList()
        ).orElseGet(ArrayList::new);
        
        
        log.info(this.getClass().getName() + ".getStudentList End!");
        return rList;
    }
    
    @Override
    public void updateStudent(StudentDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".updateStudent Start!");
        Optional<StudentDTO> res = Optional.ofNullable(
                studentMapper.getStudent(pDTO)
        );
        
        if (res.isPresent()) {
            studentMapper.updateStudent(pDTO);
            log.info(this.getClass().getName() + ".updateStudent updating Student is success!");
        } else {
            log.info(this.getClass().getName() + ".updateStudent updating Student is Failed!");
        }
        
        
        log.info(this.getClass().getName() + ".updateStudent End!");
    }
    
    @Override
    public void updateStudentList(List<StudentDTO> pList) throws Exception {
        log.info(this.getClass().getName() + ".updateStudentList Start!");
        
        pList.forEach(dto -> {
            try {
                this.updateStudent(dto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
//        pList.forEach(dto -> {
//            try {
//                Optional<StudentDTO> res = Optional.ofNullable(
//                        studentMapper.getStudent(dto)
//                );
//
//                if(!res.isPresent()) {
//                    pList.remove(dto);
//                }
//
//
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        pList.forEach(dto -> {
//            try {
//                studentMapper.updateStudent(dto);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        });
        
        
        
        
        
        
        log.info(this.getClass().getName() + ".updateStudentList End!");
    }
}
