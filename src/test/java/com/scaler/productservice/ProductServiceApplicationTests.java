package com.scaler.productservice;

import com.scaler.productservice.inheritanceExample.joinedTable.JTMentorRepository;
import com.scaler.productservice.inheritanceExample.joinedTable.JTUserRepository;
import com.scaler.productservice.inheritanceExample.joinedTable.Mentor;
import com.scaler.productservice.inheritanceExample.joinedTable.User;
import com.scaler.productservice.inheritanceExample.mappedSuperClass.MSMentorRepository;
import com.scaler.productservice.inheritanceExample.singleClass.STMentorRepository;
import com.scaler.productservice.inheritanceExample.singleClass.STUserRepository;
import com.scaler.productservice.inheritanceExample.tablePerClass.TPCMentorRepository;
import com.scaler.productservice.inheritanceExample.tablePerClass.TPCUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private JTUserRepository jtUserRepository;

    @Autowired
    private JTMentorRepository jtMentorRepository;

    @Autowired
    private MSMentorRepository msmentorRepository;

    @Autowired
    private STUserRepository stUserRepository;

    @Autowired
    private STMentorRepository stMentorRepository;

    @Autowired
    private TPCUserRepository tpcUserRepository;

    @Autowired
    private TPCMentorRepository tpcMentorRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testDifferentInheritanceJT(){
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassWord("Password");
        jtUserRepository.save(user);

        Mentor mentor = new Mentor();
        mentor.setEmail("test01@gmail.com");
        mentor.setPassWord("Pwd");
        mentor.setNumberOfMentees(4);
        mentor.setNumberOfSessions(50);
        jtMentorRepository.save(mentor);
    }

    @Test
    void testDifferentInheritanceMS(){
        com.scaler.productservice.inheritanceExample.mappedSuperClass.Mentor mentor = new com.scaler.productservice.inheritanceExample.mappedSuperClass.Mentor();
        mentor.setEmail("testSuperClass@gmail.com");
        mentor.setPassWord("PwdSuperClass");
        mentor.setNumberOfMentees(4);
        mentor.setNumberOfSessions(50);
        msmentorRepository.save(mentor);
    }

    @Test
    void testDifferentInheritanceST(){

        com.scaler.productservice.inheritanceExample.singleClass.User user = new com.scaler.productservice.inheritanceExample.singleClass.User();
        user.setEmail("testSingleClass@gmail.com");
        user.setPassWord("PasswordSingleClass");
        stUserRepository.save(user);

        com.scaler.productservice.inheritanceExample.singleClass.Mentor mentor = new com.scaler.productservice.inheritanceExample.singleClass.Mentor();
        mentor.setEmail("test01@gmail.com");
        mentor.setPassWord("Pwd");
        mentor.setNumberOfMentees(4);
        mentor.setNumberOfSessions(50);
        stMentorRepository.save(mentor);
    }
    @Test

    void testDifferentInheritanceTPC(){
        com.scaler.productservice.inheritanceExample.tablePerClass.User user = new com.scaler.productservice.inheritanceExample.tablePerClass.User();
        user.setEmail("testSingleClass@gmail.com");
        user.setPassWord("PasswordSingleClass");
        tpcUserRepository.save(user);

        com.scaler.productservice.inheritanceExample.tablePerClass.Mentor mentor = new com.scaler.productservice.inheritanceExample.tablePerClass.Mentor();
        mentor.setEmail("test01@gmail.com");
        mentor.setPassWord("Pwd");
        mentor.setNumberOfMentees(4);
        mentor.setNumberOfSessions(50);
        tpcMentorRepository.save(mentor);
    }

}
