package cn.edu.ecnu.projectmanager.service;

import cn.edu.ecnu.projectmanager.entity.Project;
import cn.edu.ecnu.projectmanager.entity.Student;
import cn.edu.ecnu.projectmanager.entity.Pro_stu;

import java.util.List;

public interface StudentService {
    Student login(String username, String password)throws Exception;
    int saveOrUpdate(Student student)throws Exception;
    int add(Student student)throws Exception;

    //Student findByUsername(String username)throws Exception;
    Student findById(Integer id)throws Exception;
    List<Student> findByName(String name);
    List<Student> listAll();
    List<Pro_stu> getTeam(Integer studentId);
    boolean isExisted(String username);

    int deleteById(Integer id)throws Exception;
    //int deleteByUsername(String username) throws Exception;
    void joinTeam(Integer studentId, String teamname)throws Exception;
    List<Project> listProject(Integer studentId)throws Exception;
}
