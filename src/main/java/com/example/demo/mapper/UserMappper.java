package com.example.demo.mapper;

import com.example.demo.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2021/8/16 14:01
 */
@Mapper
@Repository
public interface UserMappper {
    void inserteUser(User user);
    void deleteUserById(Long id);
}
