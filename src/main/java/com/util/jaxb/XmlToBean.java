package com.util.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: AlbertXe
 * @create: 2022-05-12 16:36
 */
public class XmlToBean {
    public static Object xmlToBean(String xmlPath, Class<?> load) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(load);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(new File(xmlPath));
        return object;
    }

    public static void main(String[] args) throws IOException, JAXBException {
        String xmlPath = "D:/testConfig.xml";
        Object object = XmlToBean.xmlToBean(xmlPath, Students.class);
        Students students = (Students) object;
        List<Student> studentList = students.getStudents();

        for (int i = 0; i < studentList.size(); i++) {
            System.out.println(studentList.get(i).getName());
            System.out.println(studentList.get(i).getSex());
            System.out.println(studentList.get(i).getNumber());
            System.out.println(studentList.get(i).getClassName());
            for (String str : studentList.get(i).getHobby()) {
                System.out.print(str + " ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
}
