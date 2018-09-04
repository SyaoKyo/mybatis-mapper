package me.syaokyo.test;

import me.syaokyo.entity.Student;
import me.syaokyo.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by SyaoKyo on 2018/9/4.
 */
public class TestMode {
    public static void main(String[] args) throws IOException {

        // 1.读取SqlMapConfig.xml配置文件
        String path = "SqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(path);
        // 创建会话工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 获得会话
        SqlSession session = factory.openSession();
        //mybatis自动生成mapper代理对象
        StudentMapper sm = session.getMapper(StudentMapper.class);

        //selectStuById
        System.out.println(sm.selectStuById("201801010001").getStuName());
        System.out.println(sm.selectStuById("201801010002").getStuName());
        System.out.println("\nadd:");

        //addStudent
        Student s=new Student("201801010007","张白","男",18);
        sm.addStudent(s);

        List<Student> list1 = sm.selectByFirstName("张");
        int listSize = list1.size();
        for (int i=0;i<listSize;i++){
            System.out.println(list1.get(i).getStuNo()+'\t'+
                    list1.get(i).getStuName()+ '\t'+
                    list1.get(i).getStuSex()+'\t'+
                    list1.get(i).getStuAge());
        }
        System.out.println("\ndelete:");

        //deleteStudent
        sm.deleteStudentById("201801010007");

        list1 = sm.selectByFirstName("张");
        listSize = list1.size();
        for (int i=0;i<listSize;i++){
            System.out.println(list1.get(i).getStuNo()+'\t'+
                    list1.get(i).getStuName()+ '\t'+
                    list1.get(i).getStuSex()+'\t'+
                    list1.get(i).getStuAge());
        }
        System.out.println("\nupdate:");

        //updateStudent
        Student stu=new Student("201801010001","张三","女",17);
        sm.updateStudentById(stu);

        list1 = sm.selectByFirstName("张");
        listSize = list1.size();
        for (int i=0;i<listSize;i++){
            System.out.println(list1.get(i).getStuNo()+'\t'+
                    list1.get(i).getStuName()+ '\t'+
                    list1.get(i).getStuSex()+'\t'+
                    list1.get(i).getStuAge());
        }
    }
}
