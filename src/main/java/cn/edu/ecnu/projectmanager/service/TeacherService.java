package cn.edu.ecnu.projectmanager.service;

import cn.edu.ecnu.projectmanager.entity.Project;
import cn.edu.ecnu.projectmanager.entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher login(String username, String password)throws Exception;
    int saveOrUpdate(Teacher teacher);
    int add(Teacher teacher); // Add teacher to database

    Teacher findByUsername(String username);
    Teacher findById(Integer id);
    List<Teacher> findByName(String name);
    List<Teacher> listAll();
    boolean isExisted(String username);

    int deleteById(Integer id);
    int deleteByUsername(String username);
    List<Project> getProjectList(Integer id);
}