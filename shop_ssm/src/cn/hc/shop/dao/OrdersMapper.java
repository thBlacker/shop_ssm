package cn.hc.shop.dao;

import cn.hc.shop.entities.Orders;
import cn.hc.shop.entities.OrdersExample;
import cn.hc.shop.entities.User;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrdersMapper {
    int countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Integer oid);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Integer oid);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
    
    List<Orders> getOrderItemsbyUid(@Param("uid")int uid, @Param("begin")int begin, @Param("limit")int limit);

	int getCountByUid(int uid);

	void remove(int oid);

	int getMaxId();

	void insertAndGetId(Orders order);

}