package cn.edu.ecnu.projectmanager.service.impl;

import cn.edu.ecnu.projectmanager.entity.Student;
import cn.edu.ecnu.projectmanager.entity.Pro_stu;
import cn.edu.ecnu.projectmanager.mapper.StudentMapper;
import cn.edu.ecnu.projectmanager.mapper.TeamMapper;
import cn.edu.ecnu.projectmanager.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    StudentMapper studentMapper;
    @Override
    public int add(Pro_stu team) throws Exception{
        verifyTeam(team);
        if(teamMapper.findTeamByName(team.getName()) != null){
            throw new Exception("队伍名称已被占用");
        }
        teamMapper.addTeam(team);
        int primaryKey = teamMapper.findTeamByName(team.getName()).getId();
                // 建队以后队长自动入队
        addMember(primaryKey, studentMapper.findById(team.getLeader_id()));
        return primaryKey;
    }

    @Override
    public List<Student> getAllMember(Integer teamId) {
        return teamMapper.findStudentByTeamId(teamId);
    }

    @Override
    public Pro_stu getById(Integer id) throws Exception{
        Pro_stu team = teamMapper.findTeamById(id);
        if(team == null){
            throw new Exception("队伍不存在");
        }
        return team;
    }

    @Override
    public Pro_stu getByName(String name){
        Pro_stu team = teamMapper.findTeamByName(name);
        return team;
    }

    @Override
    public List<Pro_stu> getTeamList(int pageSize, int pageNumber) {
        return teamMapper.getTeamList(pageNumber*pageSize, pageSize);
    }

    @Override
    public int count() {
        return teamMapper.count();
    }

    @Override
    public int deleteById(int id) throws Exception{
        if(id < 0){
            throw new Exception("ID不能为负");
        }
        if(teamMapper.findTeamById(id) == null){
            throw new Exception("队伍不存在");
        }
        return teamMapper.deleteTeamById(id);
    }

    @Override
    public boolean isExist(String teamname){
        return false;
    }

    @Override
    public void addMember(Integer id, Student student) throws Exception{
        verifyTeam(teamMapper.findTeamById(id));
        teamMapper.addMember(id, student.getId());
    }
    @Override
    public void deleteMember(String teamname, Integer studentId)throws Exception{
        Pro_stu team = teamMapper.findTeamByName(teamname);
        verifyTeam(team);
        teamMapper.deleteMember(team.getId(), studentId);
    }
    private void verifyTeam(Pro_stu team)throws Exception{
        if(team == null){
            throw new Exception("队伍不存在");
        }
        if(team.getName().trim().isEmpty()){
            throw new Exception("队伍名字不得为空");
        }
        if(team.getLeader_id() == null){
            throw new Exception("未指定队长");
        }
        Student leaderFound = studentMapper.findById(team.getLeader_id());
        if(leaderFound == null){
            throw new Exception("队长不存在");
        }
    }

    @Override
    public List<Pro_stu> listAll() {
        return teamMapper.listAllTeam();
    }

}
