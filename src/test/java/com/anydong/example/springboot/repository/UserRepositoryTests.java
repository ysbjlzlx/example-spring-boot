package com.anydong.example.springboot.repository;

import com.alibaba.fastjson.JSON;
import com.anydong.example.springboot.domain.UserDO;
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
    private UserDO userDO;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepositoryTests() {
        GeometryFactory geometryFactory = new GeometryFactory();
        Point location = geometryFactory.createPoint(new Coordinate(113, 22));
        UserDO.MobilePhone phone = new UserDO.MobilePhone("+86", "17080952312");
        this.userDO = new UserDO();
        this.userDO.setEmail("liuzhaowei55@gmial.com");
        this.userDO.setPassword("123456");
        this.userDO.setNickname("Where");
        this.userDO.setAvatar("https://static.moorper.com/avatar.png");
        this.userDO.setPhone(phone);
        this.userDO.setLocation(location);
    }

    @Test
    public void countTest() {
        this.userRepository.save(this.userDO);
        long total = this.userRepository.count();
        Assert.assertEquals(1, total);
    }

    @Test
    public void insertTest() {
        userDO = this.userRepository.save(this.userDO);
        Assert.assertEquals(36, userDO.getId().length());
    }

    @Test
    public void getTest() {
        this.userRepository.save(this.userDO);
        UserDO userDO = this.userRepository.findFirstByIdIsNotNull();
        System.out.println(JSON.toJSONString(userDO));
        Assert.assertEquals(36, userDO.getId().length());
    }
}
