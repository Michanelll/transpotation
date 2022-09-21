package test.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import test.Entity.Stock;
import test.service.ConcernService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author Michael
 * @date 2022-9-2023:28
 */
@RestController
@RequestMapping("/stock")
public class concernController {
    @Resource
    ConcernService service;
    @RequestMapping(value = "/concern",method = RequestMethod.GET)
    public List getStockInfo() throws IOException {return service.getStocksByUid(1);}

    @RequestMapping(value = "/concern/post",method = RequestMethod.POST)
    public int post( @RequestBody Stock stock){
        return service.addStock(stock);
    };
}
