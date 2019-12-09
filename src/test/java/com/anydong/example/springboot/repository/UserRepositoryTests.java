package com.anydong.example.springboot.repository;

import com.anydong.example.springboot.domain.UserDO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTests {
    private UserRepository userRepository;
    private GeometryFactory geometryFactory;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepositoryTests() {
        this.geometryFactory = new GeometryFactory();
    }

    @Before
    public void init() {
        Point location = this.geometryFactory.createPoint(new Coordinate(113, 22));
        UserDO.MobilePhone phone = new UserDO.MobilePhone("+86", "17080952312");
        UserDO userDO = new UserDO();
        userDO.setEmail("liuzhaowei55@gmial.com");
        userDO.setPassword("123456");
        userDO.setNickname("Where");
        userDO.setAvatar("https://static.moorper.com/avatar.png");
        userDO.setPhone(phone);
        userDO.setLocation(location);
        this.userRepository.save(userDO);
    }


    @Test
    public void countTest() {
        long total = this.userRepository.count();
        Assert.assertEquals(0, total);

    }

    @Test
    public void addTest() {
        Point location = this.geometryFactory.createPoint(new Coordinate(113, 22));
        UserDO.MobilePhone phone = new UserDO.MobilePhone("+86", "17080952312");
        UserDO userDO = new UserDO();
        userDO.setEmail("liuzhaowei55@gmial.com");
        userDO.setPassword("123456");
        userDO.setNickname("Where");
        userDO.setAvatar("https://static.moorper.com/avatar.png");
        userDO.setPhone(phone);
        userDO.setLocation(location);
        this.userRepository.save(userDO);
    }

    @Test
    public void getTest() {
        UserDO userDO = this.userRepository.findFirstByIdIsNotNull();
        System.out.println(userDO);
    }
}
