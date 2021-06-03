package cn.edu.ecnu.projectmanager.mapper;

import cn.edu.ecnu.projectmanager.entity.Project;
import cn.edu.ecnu.projectmanager.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    @ResultType(Student.class)
    @Select("select * from student where id=#{id};")
    Student findById(Integer id);

    @ResultType(Student.class)
    @Select("select * from student where username=#{username};")
    Student findByUsername(@Param("username") String username);

    @ResultType(Student.class)
    @Select("select * from student where name=#{name}")
    List<Student> findByName(@Param("name") String name);

    @ResultType(Student.class)
    @Select("select * from student where username=#{username} and password=#{password}")
    Student findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    //通过studentID查看他的所有项目
    @ResultType(Project.class)
    @Select("select * from project where team_id in (select team_id from team_students where student_id=#{id});")
    List<Project> findProjectByStudentId(@Param("id") Integer id);


    // Create
    @Insert("insert into student(id,username,password,sex,birth,phone,grade,major,name)" +
            " values(#{id},#{username},#{password},#{sex},#{birth},#{phone},#{grade},#{major}, #{name});")
    int addStudent(Student student);

    // Update
    @Update("update student set username=#{username},password=#{password},sex=#{sex},birth=#{birth},phone=#{phone},grade=#{grade},major=#{major},name=#{name} where username=#{username}")
    int updateStudent(Student student);

    // Delete
    @Delete("delete from student where id=#{id};")
    int deleteStudentById(@Param("id") Integer id);

    @Delete("delete from student where username=#{username};")
    int deleteStudentByUsername(@Param("username") String username);

    @Select("select * from student")
    List<Student> getAllStudent();
}
