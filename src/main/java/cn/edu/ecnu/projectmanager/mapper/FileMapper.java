package cn.edu.ecnu.projectmanager.mapper;

import cn.edu.ecnu.projectmanager.entity.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    // Create
    @Insert("insert into file(id,name,url,project_id) values(#{id},#{name},#{url},#{project_id});")
    int addFile(File file);

    // Retrieve
    @ResultType(File.class)
    @Select("select * from file where id=#{id};")
    File findFileById(@Param("id") Integer id);

    @Update("update file set name=#{name}, project_id=#{project_id}, url=#{url} where id=#{id}")
    int updateFile(File file);

    @ResultType(File.class)
    @Select("select * from file where name=#{name};")
    File findFileByName(@Param("name") String name);

    @ResultType(File.class)
    @Select("select * from file limit #{begin},#{length};")
    List<File> getFile(@Param("length") Integer length, @Param("begin") Integer begin);

    // Delete
    @Delete("delete from file where id=#{id};")
    int deleteFileById(@Param("id") Integer id);

    // Count
    @Select("select count(*) from file")
    int countFiles();
}
