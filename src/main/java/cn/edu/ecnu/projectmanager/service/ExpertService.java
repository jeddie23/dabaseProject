package cn.edu.ecnu.projectmanager.service;

import cn.edu.ecnu.projectmanager.entity.Expert;
import cn.edu.ecnu.projectmanager.entity.Project;

import java.util.List;

public interface ExpertService {
    Expert login(String username, String password)throws Exception;
    int saveOrUpdate(Expert expert)throws Exception;
    int add(Expert expert)throws Exception;

    Expert findByUsername(String username)throws Exception;
    Expert findById(Integer id);
    List<Expert> findByName(String name);
    List<Expert> listAll();
    boolean isExisted(String username);

    int deleteById(Integer id) throws Exception;
    int update(Expert e)throws Exception;
    int deleteByUsername(String username) throws Exception;
    void comment(Project project, Boolean confirm, String comment, Integer grades)throws Exception;
    List<Project> listProject(Integer expertId) throws Exception;
}
