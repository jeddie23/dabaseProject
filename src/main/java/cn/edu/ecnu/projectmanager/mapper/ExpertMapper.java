package cn.edu.ecnu.projectmanager.mapper;

import cn.edu.ecnu.projectmanager.entity.Admin;
import cn.edu.ecnu.projectmanager.entity.Expert;
import cn.edu.ecnu.projectmanager.entity.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExpertMapper {
    // Create
    @Insert("insert into expert(id,username,password,name,email) values(#{id},#{username},#{password},#{name},#{email});")
    int addExpert(Expert expert);

    // Update
    @Update("update expert set username=#{username},password=#{password},name=#{name},email=#{email} where id=#{id};")
    int updateExpert(Expert expert);

    // Delete
    @Delete("delete from expert where id=#{id};")
    int deleteExpertById(@Param("id") Integer id);
    @Delete("delete from expert where username=#{username};")
    int deleteExpertByUsername(@Param("username") String username);

    // Retrieve
    @ResultType(Expert.class)
    @Select("select * from expert where id=#{id};")
    Expert findExpertById(@Param("id") Integer id);

    @ResultType(Expert.class)
    @Select("select * from expert where username=#{username};")
    Expert findExpertByUsername(@Param("username") String username);

    @ResultType(Expert.class)
    @Select("select * from expert where name=#{name};")
    List<Expert> getExpertByName(@Param("name") String name);

    @ResultType(Expert.class)
    @Select("select * from expert")
    List<Expert> listAllExpert();

    @ResultType(Expert.class)
    @Select("SELECT * FROM expert WHERE username=#{username} and password=#{username};")
    Admin getExpertByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    // Count
    @Select("select count(*) from expert;")
    int countExpert();


    //根据expertID查看所有project
    @ResultType(Project.class)
    @Select("select * from project where expert_id=#{id}")
    List<Project> findProjectByExpertId(@Param("id") Integer id);


}
