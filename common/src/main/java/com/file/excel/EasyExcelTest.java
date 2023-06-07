package com.file.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 学习文档
 * https://easyexcel.opensource.alibaba.com/docs/current/quickstart/read
 * @description:
 * @author: AlbertXe
 * @create: 2023-06-06 21:34
 */
public class EasyExcelTest {
    private String fileName = "D:\\idea_study2\\common-util\\common\\src\\main\\java\\com\\file\\excel\\student.xlsx";
    private String writefileName = "D:\\idea_study2\\common-util\\common\\src\\main\\java\\com\\file\\excel\\student2.xlsx";


    @Test
    public void test() {
        EasyExcel.read(fileName, Student.class, new AnalysisEventListener<Student>() {
            @Override
            public void invoke(Student student, AnalysisContext analysisContext) {
                System.out.println("student="+student);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();
    }

    @Test
    public void testAllSheet() {
        EasyExcel.read(fileName, Student.class, new AnalysisEventListener<Student>() {
            @Override
            public void invoke(Student student, AnalysisContext analysisContext) {
                System.out.println("student="+student);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }

            @Override
            public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
                System.out.println("head"+headMap);
            }
        }).doReadAll();
    }

    @Test
    public void testWrite() {
        List<Student> students = generateData();
        EasyExcel.write(writefileName,Student.class).sheet("sheet1")
                .doWrite(students);

        EasyExcel.write(writefileName).sheet().doWrite(students);
    }

    @Test
    public void testWriteNoHead() {
        List<Student> students = generateData();
        EasyExcel.write(writefileName).sheet().doWrite(students);
    }

    private List<Student> generateData() {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student stu = new Student("test" + i,i, "address" + i, new BigDecimal(i));
            students.add(stu);
        }
        return students;
    }



}
