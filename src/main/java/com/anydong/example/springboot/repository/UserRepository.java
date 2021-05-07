package com.anydong.example.springboot.repository;

import com.anydong.example.springboot.domain.UserDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Where
 */
@Repository
public interface UserRepository extends CrudRepository<UserDO, String> {
    /**
     * 不带条件，统计所有的用户
     *
     * @return long 用户总数
     */
    @Override
    long count();

    /**
     * 随机获取一条数据
     *
     * @return User
     */
    UserDO findFirstByIdIsNotNull();


}
