package cn.edu.ecnu.projectmanager.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pro_stu")
public class Pro_stu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private Integer pro_id;

    @Column(name = "stu_id", nullable = false)
    private Integer stu_id;
}