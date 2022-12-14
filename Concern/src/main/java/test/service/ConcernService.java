package test.service;

import org.apache.ibatis.annotations.Param;
import test.Entity.Stock;

import java.util.List;

/**
 * @author Michael
 * @date 2022--2023:01
 */
public interface ConcernService {
    int addStock(Stock stock);
    String getStocksByUid(int uid);
    int deleteStock(int uid,String code);
}
