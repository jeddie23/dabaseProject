package cn.edu.ecnu.projectmanager.mapper;

import cn.edu.ecnu.projectmanager.entity.Student;
import cn.edu.ecnu.projectmanager.entity.Team;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeamMapper {
    // Create
    @Insert("insert into team(id,name,leader_id) values(#{id},#{name},#{leader_id});")
    int addTeam(Team team);
    @Select("insert into team_students(team_id,student_id) values(#{teamId},#{studentId});")
    Integer addMember(Integer teamId, Integer studentId);

    // Update
    @Update("update team set name=#{name},leader_id=#{leader_id} where id=#{id};")
    int updateProject(Team team);


    // Retrieve
    @ResultType(Team.class)
    @Select("select * from team where id=#{id};")
    Team findTeamById(@Param("id") Integer id);

    @Select("select * from team where name=#{name};")
    Team findTeamByName(@Param("name") String name);

    //通过小组ID查小组成员信息
    @ResultType(Student.class)
    @Select("select * from student where id in (select student_id from team_students where team_id=#{id});")
    List<Student> findStudentByTeamId(@Param("id") Integer id);
    // 通过学生ID获取学生所在小组
    @Select("select * from team where id in (select team_id from team_students where student_id=#{id});")
    List<Team> findTeamByStudentId(@Param("id") Integer id);

    // Delete
    @Delete("delete from team where id=#{id};")
    int deleteTeamById(@Param("id") Integer id);

    @Delete("delete from team where name=#{name};")
    int deleteTeamByName(@Param("name") String name);

    @Delete("delete from team_students where student_id=#{studentId} and team_id=#{teamId};")
    int deleteMember(Integer teamId, Integer studentId);

    @Select("select count(*) from team;")
    int count();

    @Select("select * from team limit #{pageSize},#{pageNumber};")
    List<Team> getTeamList(int pageSize, int pageNumber);
    @Select("select * from team")
    List<Team> listAllTeam();
}