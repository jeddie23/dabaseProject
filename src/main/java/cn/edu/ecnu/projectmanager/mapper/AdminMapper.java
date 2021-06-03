package cn.edu.ecnu.projectmanager.mapper;

import cn.edu.ecnu.projectmanager.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {
    // Create
    // Admin cannot create through this program

    // Update
    @Update("update admin set username=#{username},password=#{password},name=#{name},phone=#{phone} where id=#{id};")
    int updateAdmin(Admin admin);

    // Delete
    // Admin cannot delete through this program

    //Retrieve

    @ResultType(Admin.class)
    @Select("SELECT * FROM admin WHERE id=#{id};")
    Admin getAdminById(@Param("id") Integer id);


    @ResultType(Admin.class)
    @Select("SELECT * FROM admin WHERE username=#{username};")
    Admin getAdminByUsername(@Param("username") String username);

    @ResultType(Admin.class)
    @Select("SELECT * FROM admin WHERE name=#{name};")
    List<Admin> getAdminByName(@Param("name") String name);


    @ResultType(Admin.class)
    @Select("SELECT * FROM admin WHERE username=#{username} and password=#{username};")
    Admin getAdminByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    @Select("SELECT * FROM admin")
    List<Admin> getAllAdmin();
}
