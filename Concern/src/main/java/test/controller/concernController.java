package test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
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
@Api(tags = "自选股api", description = "增删查")
@RequestMapping("/stock")
public class concernController {
    @Resource
    ConcernService service;
    @ApiOperation("查 逻辑已写死")
    @RequestMapping(value = "/concern",method = RequestMethod.GET)
    public String getStockInfo() throws IOException {return service.getStocksByUid(1);}

    @ApiOperation("增")
    @RequestMapping(value = "/concern/post",method = RequestMethod.POST)
    public int post( @RequestBody Stock stock){return service.addStock(stock);}

    @ApiOperation("删")
    @RequestMapping(value = "/concern/delete/{uid}/{code}",method = RequestMethod.GET)
    public int delete(@Param("uid")int uid,@Param("code")String code){return service.deleteStock(uid, code);}

}
