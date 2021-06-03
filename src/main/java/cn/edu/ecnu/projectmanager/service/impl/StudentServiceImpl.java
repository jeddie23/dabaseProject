package cn.edu.ecnu.projectmanager.service.impl;

import cn.edu.ecnu.projectmanager.entity.Project;
import cn.edu.ecnu.projectmanager.entity.Student;
import cn.edu.ecnu.projectmanager.entity.Team;
import cn.edu.ecnu.projectmanager.mapper.StudentMapper;
import cn.edu.ecnu.projectmanager.mapper.TeamMapper;
import cn.edu.ecnu.projectmanager.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeamMapper teamMapper;

    @Override
    public Student login(String username, String password) throws Exception{
        if(username.isEmpty()){
            throw new Exception("用户名不得为空");
        }
        if(password.isEmpty()){
            throw new Exception("密码不得为空");
        }
        Student user = studentMapper.findByUsername(username);
        if(user == null){
            log.error("student: {} doesn't exist.", username);
            throw new Exception("用户不存在");
        }else if(!user.getPassword().equals(password)){
            log.error("student: {} password wrong.", username);
            throw new Exception("密码错误");
        }
        return user;
    }

    @Override
    public int saveOrUpdate(Student student) throws Exception{
        verifyStudent(student);
        if(isExisted(student.getUsername())){
            log.info(student.getUsername()+"update personal information");
            return studentMapper.updateStudent(student);
        }
        log.info("Add user "+student.getUsername());
        return studentMapper.addStudent(student);
    }

    @Override
    public int add(Student student) throws Exception{
        verifyStudent(student);
        if(studentMapper.findByUsername(student.getUsername()) != null){
            log.error("Student duplicate: {} already exists", student.getUsername());
            throw new Exception("用户名已被占用");
        }
        return studentMapper.addStudent(student);
    }

    @Override
    public List<Project> listProject(Integer studentId) throws Exception {
        return studentMapper.findProjectByStudentId(studentId);
    }

    @Override
    public Student findByUsername(String username) throws Exception{
        return studentMapper.findByUsername(username);
    }

    @Override
    public Student findById(Integer id) throws Exception{
        return studentMapper.findById(id);
    }

    @Override
    public List<Student> findByName(String name) {
        return studentMapper.findByName(name);
    }

    @Override
    public List<Student> listAll() {
        return studentMapper.getAllStudent();
    }

    @Override
    public void joinTeam(Integer studentId, String teamname) throws Exception {
        Team team = teamMapper.findTeamByName(teamname);
        if(team == null){
            throw new Exception("队伍不存在");
        }
        List<Student> students = teamMapper.findStudentByTeamId(team.getId());
        for (Student student: students){
            if(student.getId().equals(studentId)){
                throw new Exception("学生已在队伍中, 请勿重复添加");
            }
        }
        teamMapper.addMember(team.getId(), studentId);
    }

    @Override
    public boolean isExisted(String username) {
        Student student = studentMapper.findByUsername(username);
        return student != null;
    }

    @Override
    public int deleteById(Integer id) throws Exception{
        return studentMapper.deleteStudentById(id);
    }

    @Override
    public int deleteByUsername(String username) throws Exception{
        return studentMapper.deleteStudentByUsername(username);
    }
    private void verifyStudent(Student student)throws Exception{
        if(student.getUsername().isEmpty()){
            throw new Exception("用户名不得为空");
        }
        if(student.getPassword().length() < 8 || student.getPassword().length() > 64){
            throw new Exception("密码长度必须为8-64位");
        }
    }

    @Override
    public List<Team> getTeam(Integer studentId) {
        return teamMapper.findTeamByStudentId(studentId);
    }
}
