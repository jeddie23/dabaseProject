package cn.edu.ecnu.projectmanager.service;

import cn.edu.ecnu.projectmanager.entity.Student;
import cn.edu.ecnu.projectmanager.entity.Team;
import cn.edu.ecnu.projectmanager.entity.Pro_stu;

import java.util.List;

public interface TeamService {
    int add(Pro_stu team) throws Exception;
    Pro_stu getById(Integer id) throws Exception;
    //Team getByName(String name);
    //List<Team> getTeamList(int pageSize, int pageNumber);
    List<Pro_stu> listAll();
    List<Student> getAllMember(Integer teamId);
    int count();
    void addMember(Integer id, Student student)throws Exception;
    //boolean isExist(String teamname);

    int deleteById(int id)throws Exception;
    void deleteMember(String teamname, Integer studentId) throws Exception;
}
