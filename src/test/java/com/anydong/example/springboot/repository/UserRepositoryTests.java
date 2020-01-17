package com.anydong.example.springboot.repository;

import com.alibaba.fastjson.JSON;
import com.anydong.example.springboot.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaAuditing
public class UserRepositoryTests {
    private UserRepository userRepository;
    private User user;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepositoryTests() {
        GeometryFactory geometryFactory = new GeometryFactory();
        Point location = geometryFactory.createPoint(new Coordinate(113, 22));
        User.MobilePhone phone = new User.MobilePhone("+86", "17080952312");
        this.user = new User();
        this.user.setEmail("liuzhaowei55@gmial.com");
        this.user.setPassword("123456");
        this.user.setNickname("Where");
        this.user.setAvatar("https://static.moorper.com/avatar.png");
        this.user.setPhone(phone);
        this.user.setLocation(location);
    }

    @Test
    public void countTest() {
        this.userRepository.save(this.user);
        long total = this.userRepository.count();
        Assert.assertEquals(1, total);
    }

    @Test
    public void insertTest() {
        user = this.userRepository.save(this.user);
        Assert.assertEquals(36, user.getId().length());
    }

    @Test
    public void getTest() {
        this.userRepository.save(this.user);
        User user = this.userRepository.findFirstByIdIsNotNull();
        System.out.println(JSON.toJSONString(user));
        Assert.assertEquals(36, user.getId().length());
    }
}
