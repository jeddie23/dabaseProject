package cn.edu.ecnu.projectmanager.service.impl;

import cn.edu.ecnu.projectmanager.common.PageJson;
import cn.edu.ecnu.projectmanager.entity.Expert;
import cn.edu.ecnu.projectmanager.entity.Project;
import cn.edu.ecnu.projectmanager.mapper.ExpertMapper;
import cn.edu.ecnu.projectmanager.mapper.ProjectMapper;
import cn.edu.ecnu.projectmanager.service.ExpertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ExpertServiceImpl implements ExpertService {
    @Autowired
    private ExpertMapper expertMapper;
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Expert login(String username, String password) throws Exception {
        if(username.isEmpty()){
            throw new Exception("用户名不得为空");
        }
        if(password.isEmpty()){
            throw new Exception("密码不得为空");
        }
        Expert user = expertMapper.findExpertByUsername(username);
        if(user == null){
            log.error("expert: {} doesn't exist.", username);
            throw new Exception("用户不存在");
        }else if(!user.getPassword().equals(password)){
            log.error("expert: {} password wrong.", username);
            throw new Exception("密码错误");
        }
        return user;
    }
    @Override
    public int saveOrUpdate(Expert expert) throws Exception{
        if(isExisted(expert.getUsername())){
            verify(expert);
            return expertMapper.updateExpert(expert);
        }else {
            return add(expert);
        }
    }

    @Override
    public int add(Expert expert) throws Exception{
        if(findByUsername(expert.getUsername())!= null){
            throw new Exception("用户名被占用");
        }
        verify(expert);
        return expertMapper.addExpert(expert);
    }

    @Override
    public Expert findByUsername(String username) throws Exception {
       Expert expert =  expertMapper.findExpertByUsername(username);
       if(expert == null){
           throw new Exception("用户不存在");
       }
       return expert;
    }

    @Override
    public Expert findById(Integer id) {
        return expertMapper.findExpertById(id);
    }

    @Override
    public List<Expert> findByName(String name) {
        return expertMapper.getExpertByName(name);
    }

    @Override
    public List<Expert> listAll(){
        return expertMapper.listAllExpert();
    }

    @Override
    public boolean isExisted(String username) {
        return expertMapper.findExpertByUsername(username) != null;
    }

    @Override
    public int deleteById(Integer id)throws Exception {
        return expertMapper.deleteExpertById(id);
    }

    @Override
    public int deleteByUsername(String username) throws Exception{
        return expertMapper.deleteExpertByUsername(username);
    }

    @Override
    public void comment(Project project, Boolean confirm, String comment, Integer grades) throws Exception{
        if(project.getStatus().trim().equals("等待指导导师确认")){
            throw new Exception("项目尚未通过指导老师确认");
        }else if(project.getStatus().trim().equals("等待项目审核与立项") && confirm){
            project.setStatus("正在中期答辩");
        }else if(project.getStatus().trim().equals("正在中期答辩") && confirm){
            project.setStatus("项目中期审核");
        }else if(project.getStatus().trim().equals("项目中期审核") && confirm){
            project.setStatus("项目等待终期评审和确定成绩");
        }else if(project.getStatus().trim().equals("项目等待终期评审和确定成绩") && confirm){
            project.setStatus("项目完成");
        }else if(project.getStatus().trim().equals("项目完成")){
            throw new Exception("项目已结束");
        }
        if(grades == null){
            throw new Exception("未打分");
        }
        project.setGrades(grades);
        project.setExpert_comment(comment);
        projectMapper.updateProject(project);
    }

    @Override
    public List<Project> listProject(Integer expertId) throws Exception{
        return expertMapper.findProjectByExpertId(expertId);
    }

    @Override
    public int update(Expert e) throws Exception{
        return expertMapper.updateExpert(e);
    }

    private void verify(Expert expert)throws Exception{
        if(expert.getName().isEmpty()){
            throw new Exception("名字不得为空");
        }
        if(expert.getPassword().isEmpty()){
            throw new Exception("密码不能为空");
        }
        if(expert.getUsername().isEmpty()){
            throw new Exception("用户名不能为空");
        }
    }
}
