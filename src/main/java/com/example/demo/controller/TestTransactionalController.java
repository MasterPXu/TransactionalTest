package com.example.demo.controller;

import com.example.demo.mapper.UserMappper;
import com.example.demo.service.TestTransactionalServiceImpl;
import com.example.demo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2021/8/16 13:59
 */
@RestController
@RequestMapping("/test")
public class TestTransactionalController {
    @Autowired
    TestTransactionalServiceImpl service;
    /**
     *	查询所有用户信息
     */
    @GetMapping("/transactional")
    public void transactional() throws Exception {
         List<Connection> list = service.transactions();
         int i = 0;
         for(Connection con : list){
            System.out.println(i + "connection is Closed" + con.isClosed());
         }
    }
}
