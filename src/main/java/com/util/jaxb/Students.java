package com.util.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 11:12
 */
@XmlRootElement(name = "list")
public class Students {
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    @XmlElement(name = "student")
    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
