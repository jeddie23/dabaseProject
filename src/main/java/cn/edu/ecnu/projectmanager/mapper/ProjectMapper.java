package cn.edu.ecnu.projectmanager.mapper;

import cn.edu.ecnu.projectmanager.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectMapper {
    // Create
    @Insert("insert into project(id,name,type,grades,status,teacher_comment,expert_comment,leader_id,expert_id,teacher_id,team_id)" +
            "values(#{id},#{name},#{type},#{grades},#{status},#{teacher_comment},#{expert_comment},#{leader_id},#{expert_id},#{teacher_id},#{team_id});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addProject(Project project);

    // Update
    @Update("update project set name=#{name},type=#{type},grades=#{grades},status=#{status},teacher_comment=#{teacher_comment},expert_comment=#{expert_comment},leader_id=#{leader_id},expert_id=#{expert_id},teacher_id=#{teacher_id},team_id=#{team_id} where id=#{id}")
    int updateProject(Project project);

    // Retrieve
    @ResultType(Project.class)
    @Select("select * from project where id=#{id};")
    Project findProjectById(@Param("id") Integer id);
@ResultType(Project.class)
    @Select("select * from project where name=#{name};")
    Project findProjectByName(@Param("name") String name);

    @Select("select distinct type from project")
    List<String> getAllProjectType();
    @Select("select * from project")
    List<Project> getAllProject();
    @Select("select count(*) from project")
    int count();
    //通过小组Id查项目
    @ResultType(Project.class)
    @Select("select * from project where team_id=#{team_id};")
    Project findProjectByTeamId(@Param("team_id") Integer team_id);

    //通过小组名查项目
    @ResultType(Project.class)
    @Select("select * from project where team_id in (select id from team where name=#{name});")
    Project findProjectByTeamName(@Param("name") String name);

    //根据项目ID查看所有file
    @ResultType(File.class)
    @Select("select * from file where project_id=#{project_id};")
    List<File> findFileByProjectId(@Param("project_id") Integer project_id);

    //通过leader_id查看小组信息
    @ResultType(Team.class)
    @Select("select * from Team where leader_id=#{leader_id};")
    Team findTeamByLeader_id(@Param("leader_id") Integer leader_id);

    //通过teacherID获得老师信息
    @ResultType(Teacher.class)
    @Select("select * from teacher where id=#{teacher_id};")
    Teacher findTeacherByTeacher(@Param("teacher_id") Integer teacher_id);

    //通过expertID获得专家信息
    @ResultType(Expert.class)
    @Select("select * from teacher where id=#{expert_id};")
    Expert findExpertByExpert(@Param("expert_id") Integer expert_id);


    // Delete
    @Delete("delete from project where id=#{id};")
    int deleteProjectById(@Param("id") Integer id);

    @Delete("delete from project where name=#{name};")
    int deleteProjectByName(@Param("name") String name);
}
