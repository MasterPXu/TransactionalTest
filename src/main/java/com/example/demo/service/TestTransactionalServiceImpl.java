package com.example.demo.service;

import com.example.demo.mapper.SellerGoodsMapper;
import com.example.demo.mapper.UserMappper;
import com.example.demo.vo.SellerGoods;
import com.example.demo.vo.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2021/8/16 14:19
 */
@Service
public class TestTransactionalServiceImpl {
    @Autowired
    private UserMappper userMappper;
    @Autowired
    private SellerGoodsMapper sellerGoodsMapper;
    @Autowired
    private TestTransactionalServiceImpl service;
    @Autowired
    private SqlSession sqlSession;

    @Transactional(rollbackFor = Exception.class)
    public List<Connection> transactions() throws Exception {
        User user1 = new User();
        user1.setName("ppp");
        user1.setAge(28);
        User user2 = new User();
        user2.setName("xppp");
        user2.setAge(29);
        SellerGoods sellers = new SellerGoods();
        sellers.setSellerName("peixu");
        //1:插入user
        System.out.println("1:插入user");
        Connection con = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
        System.out.println("1:dataSource" + sqlSession.getConfiguration().getEnvironment().getDataSource());
        System.out.println("3:connection 是否一致" + sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection());
        userMappper.inserteUser(user1);
        System.out.println("1:connection 是否关闭" + sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection().isClosed());
        //2:插入seller
        Connection co = insert(sellers,con);
        System.out.println("co.isClosed()" + co.isClosed() + "con.isClosed()" + con.isClosed());
        //3:插入user,抛出异常
        try {
            service.insertThrow(user2,con);
        }catch (Exception e){

        }
        System.out.println("co.isClosed()" + co.isClosed() + "con.isClosed()" + con.isClosed());
        return Arrays.asList(co,con);
    }



    public Connection insert(SellerGoods sellers,Connection con) throws Exception{
        System.out.println("2:插入seller");
        System.out.println( "2:dataSource" + sqlSession.getConfiguration().getEnvironment().getDataSource());
        System.out.println("2:connection 是否一致" + sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection());
        System.out.println("2:connection 是否关闭" + sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection().isClosed());
        sellerGoodsMapper.inserteSeller(sellers);
        return sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void insertThrow(User user,Connection con) throws Exception {
        System.out.println( "3:dataSource" + sqlSession.getConfiguration().getEnvironment().getDataSource());
        System.out.println("3:插入user,抛出异常");
        System.out.println("3:connection 是否一致" + sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection());
        userMappper.inserteUser(user);
        System.out.println("3:connection 是否关闭" + sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection().isClosed());
        throw new Exception();
    }
}
