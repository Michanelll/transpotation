package test.service.Impl;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import test.Entity.Stock;
import test.mapper.ConcernMapper;
import test.service.ConcernService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Michael
 * @date 2022--2023:17
 */
@Service
public class ConcernServiceImpl implements ConcernService {
    @Resource
    ConcernMapper mapper;

    @Override
    public int addStock(Stock stock) {
        return mapper.addStock(stock);
    }


    @Override
    public List<Stock> getStocksByUid(int uid) {
        List<Stock> stocks = mapper.getStocksByUid(uid);
        System.out.println(stocks);
        stocks.forEach(System.out::println);
        RestTemplate template=new RestTemplate();
        List maps = stocks.
                stream().
                map(stock ->stock.getCode()+stock.getName()+template.getForObject("http://10.122.252.243:9090/stock/trends/"+stock.getMarket()+"/"
                        +stock.getCode(), String.class)).
                collect(Collectors.toList());
        System.out.println(maps);
        return maps;
    }
}
