package test.mapper;

import org.apache.ibatis.annotations.*;
import test.Entity.Stock;

import java.util.List;

/**
 * @author Michael
 * @date 2022--2023:02
 */
@Mapper
public interface ConcernMapper {
    @Select("select * from concern where uid = #{uid}")
    List<Stock> getStocksByUid(int uid);
    @Insert("insert into concern(uid,code,name,market) values(#{uid},#{code},#{name},#{market})")
    int addStock(Stock stock);
    @Delete("delete * from concern where uid = #{uid} and code = #{code}")
    int deleteStock(int uid,String code);
}
