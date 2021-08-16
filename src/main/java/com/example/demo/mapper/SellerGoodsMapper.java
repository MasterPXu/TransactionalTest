package com.example.demo.mapper;

import com.example.demo.vo.SellerGoods;
import com.example.demo.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2021/8/16 14:03
 */
@Mapper
@Repository
public interface SellerGoodsMapper {
    void inserteSeller(SellerGoods seller);
    void deleteSellerById(Long id);
}
