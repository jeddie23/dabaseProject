package cn.edu.ecnu.projectmanager.mapper;

import cn.edu.ecnu.projectmanager.entity.Project;
import cn.edu.ecnu.projectmanager.entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherMapper {
    // Create
    @Insert("insert into teacher(id,username,password,sex,phone,name) values(#{id},#{username},#{password},#{sex},#{phone}, #{name};")
    int addTeacher(Teacher teacher);

    // Update
    @Update("update teacher set username=#{username},password=#{password},sex=#{sex},phone=#{phone}, name=#{name} where id=#{id};")
    int updateTeacher(Teacher teacher);

    // Retrieve
    @Select("select * from teacher where id=#{id};")
    Teacher findTeacherById(@Param("id") Integer id);
    @Select(("select * from teacher"))
    List<Teacher> listAll();

    @Select("select * from teacher where username=#{username};")
    Teacher findTeacherByUsername(@Param("username") String username);

    @Select("select * from teacher where name=#{name};")
    List<Teacher> findTeacherByName(@Param("name") String name);

    @Select("select * from teacher where username=#{username} and id=#{id};")
    Teacher findTeacherByUsernameAndId(@Param("username") String username, @Param("id") Integer id);

    //通过teacher id查看他的所有项目
    @Select("select * from project where teacher_id=#{id};")
    List<Project> findProjectByTeacherId(@Param("id") Integer teacher);

    // Delete
    @Delete("delete from teacher where id=#{id};")
    int deleteTeacherById(@Param("id") Integer id);

    @Delete("delete from teacher where username=#{username};")
    int deleteTeacherByUsername(@Param("username") String username);
}
