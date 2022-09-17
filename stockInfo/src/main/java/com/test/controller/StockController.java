package com.test.controller;

import com.test.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Michael
 * @date 2022-9-0610:42
 */
@Api(tags = "获取数据接口", description = "获取k线与总数据")
@RestController
@RequestMapping("/stock")
public class StockController {
    @Resource
    StockService service;

    @ApiOperation("获取日k线,第一个参数是市场，第二个是六位股票代码")
    @RequestMapping(value = "/dayKline/{market}/{code}",method = RequestMethod.GET)
    public String getKline(@PathVariable("market")int market,@PathVariable("code") String code) throws IOException {return service.getKlineInfo(market,code);}

    @ApiOperation("获取股票信息,应该能选择看是sh还是sz，我再观察观察")
    @RequestMapping(value = "/info",method = RequestMethod.POST)
    public String getStockInfo() throws IOException {return service.getInfo();}

    @ApiOperation("获取周k线,第一个参数是市场，第二个是六位股票代码")
    @RequestMapping(value = "/weekKline/{market}/{code}",method = RequestMethod.GET)
    public String getWeekKline(@PathVariable("market")int market,@PathVariable("code") String code) throws IOException {return service.getWeekKlineInfo(market,code);}

    @ApiOperation("获取月k线，不过会从199x年开始返回,第一个参数是市场，第二个是六位股票代码")
    @RequestMapping(value = "/monthKline/{market}/{code}",method = RequestMethod.GET)
    public String getMonthKline(@PathVariable("market")int market,@PathVariable("code") String code) throws IOException {return service.getMonthKlineInfo(market,code);}

    @ApiOperation("获取股票实时数据，第三位参数指请求几日内的，最大5日内")
    @RequestMapping(value = "/trends/{market}/{code}/{day}",method = RequestMethod.GET)
    public String getTrends(@PathVariable("market")int market,@PathVariable("code") String code,@PathVariable("day") int day) throws IOException {return service.getTrends(market, code, day);}
}
