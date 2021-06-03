package cn.edu.ecnu.projectmanager.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "username", nullable = false)
    private String username; // login id
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "sex")
    private String sex;
    @Column(name = "birth")
    private String birth;
    @Column(name = "phone")
    private String phone;
    @Column(name = "grade")
    private String grade;
    @Column(name = "major")
    private String major;
    @Column(name = "name")
    private String name;
}
