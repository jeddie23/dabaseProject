package cn.edu.ecnu.projectmanager.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name; // filename
    @Column(name = "url", nullable = false)
    private String url;  // file path

    @Column(name="project_id",nullable = false)
    private Integer project_id;//project_id

    public File(){
    }
    public File(String name, String url){
        this.name = name;
        this.url = url;
    }
}
