package com.test.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @author Michael
 * @date 2022-9-0610:34
 */
public interface StockService {
    String getKlineInfo(int market,String code) throws IOException;
    String getInfo() throws IOException;
    String getWeekKlineInfo(int market,String code) throws IOException;
    String getMonthKlineInfo(int market,String code) throws IOException;
    String getTrends(int market,String code,int day) throws IOException;
    String getFreshPrice(int market,String code) throws IOException;
}
