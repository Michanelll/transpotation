package test.service.Impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import test.Entity.Stock;
import test.mapper.ConcernMapper;
import test.service.ConcernService;

import javax.annotation.Resource;
import java.util.HashMap;
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
    public String getStocksByUid(int uid) {
        List<Stock> stocks = mapper.getStocksByUid(uid);
        stocks.forEach(System.out::println);
        RestTemplate template=new RestTemplate();
        JSONArray jsonArray=new JSONArray();
        for (int i = 0; i < stocks.size(); i++) {
            JSONObject object=new JSONObject();
            object.put("code", stocks.get(i).getCode()).put("name", stocks.get(i).getName()).put("price",
                    template.getForObject("http://10.122.252.243:9090/stock/trends/"+ stocks.get(i).getMarket()+"/"
                            + stocks.get(i).getCode(), String.class));
            jsonArray.put(object);
        }
        return jsonArray.toString();
    }

    @Override
    public int deleteStock(int uid, String code) {
        return mapper.deleteStock(uid, code);
    }
}
