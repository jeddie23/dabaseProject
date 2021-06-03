package cn.edu.ecnu.projectmanager.entity;

import javax.persistence.*;

@Table(name = "team_students")
@Entity
public class TeamStudents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "team_id")
    Integer team_id;
    @Column(name = "student_id")
    Integer student_id;
}
