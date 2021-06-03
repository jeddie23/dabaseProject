package cn.edu.ecnu.projectmanager.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name; // 项目名称
    @Column(name = "type", nullable = false)
    private String type; // the type of the project
    @Column(name = "grades")
    private Integer grades; // 项目分数
    @Column(name = "status", nullable = false)
    private String status; // current status of the project
    @Column(name = "teacher_comment")
    private String teacher_comment;
    @Column(name = "expert_comment")
    private String expert_comment;


    @Column(name = "leader_id")
    private Integer leader_id;
    @Column(name = "expert_id")
    private Integer expert_id;
    @Column(name = "teacher_id")
    private Integer teacher_id;
    @Column(name = "team_id", nullable = false)
    private Integer team_id;
}
