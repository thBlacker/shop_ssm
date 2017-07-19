package cn.hc.shop.dao;

import cn.hc.shop.entities.Product;
import cn.hc.shop.entities.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    int countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

	List<Product> getHot();

	List<Product> getNew();

	List<Product> getProductsByCid(@Param("cid") Integer cid, @Param("begin") int begin, @Param("limit") int limit);

	int getCount(Integer cid);

	List<Product> getProductByCsid(@Param("csid")Integer csid, @Param("begin") int begin, @Param("limit") int limit);

	int getCountByCsid(Integer csid);

	List<Product> findProductBySearch(@Param("lprice")Double lprice, @Param("hprice")Double hprice, @Param("prname")String prname, @Param("begin")int begin, @Param("limit")int limit);

	int getCounts(@Param("lprice")Double lprice, @Param("hprice")Double hprice, @Param("prname") String prname);
}