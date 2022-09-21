package com.test.service.impl;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.test.service.StockService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author Michael
 * @date 2022-9-0610:36
 */
@Service
public class StockServiceImpl implements StockService {

    @Override
    public String getKlineInfo(int market,String code) throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        StringBuilder url1 = new StringBuilder("http://42.push2his.eastmoney.com/api/qt/stock/kline/get?secid=");
        url1.append(market).append(".").append(code).append("&fields1=f1,f2,f3,f4,f5,f6&fields2" +
                "=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61&klt=101&fqt=1&end=20500101&lmt=120&_=1661569974454");
        String url = url1.toString();
      //  System.out.println(url);
        Page page = webClient.getPage(url);
        WebResponse response = page.getWebResponse();
        String json = response.getContentAsString();
        JSONObject jsonObject = new JSONObject(json);
      //  System.out.println(json);
        return simplifyJson(jsonObject);
    }

    @Override
    public String getInfo() throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        String url = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/" +
                "Market_Center.getHQNodeData?page=1&num=100&sort=symbol&asc=1&node=sz_a&symbol=&_s_r_a=init";
        Page page = webClient.getPage(url);
        WebResponse response = page.getWebResponse();
        String json = response.getContentAsString();
        JSONArray resultjson = new JSONArray(json);
        return resultjson.toString();
    }

    @Override
    public String getWeekKlineInfo(int market, String code) throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        StringBuilder url1 = new StringBuilder("http://22.push2his.eastmoney.com/api/qt/stock/kline/get?secid=");
        url1.append(market).append(".").append(code).append("&fields1=f1,f2,f3,f4,f5,f6&fields2" +
                "=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61&klt=102&fqt=1&end=20500101&lmt=287&_=1662882887771");
        String url = url1.toString();
        //  System.out.println(url);
        Page page = webClient.getPage(url);
        WebResponse response = page.getWebResponse();
        String json = response.getContentAsString();
        JSONObject jsonObject = new JSONObject(json);
        //  System.out.println(json);
        return simplifyJson(jsonObject);
    }

    @Override
    public String getMonthKlineInfo(int market, String code) throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        StringBuilder url1 = new StringBuilder("http://73.push2his.eastmoney.com/api/qt/stock/kline/get?secid=");
        url1.append(market).append(".").append(code).append("&fields1=f1,f2,f3,f4,f5,f6&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61&klt=102&fqt=1&beg=0&end=20500101&smplmt=460&lmt=1000000&_=1662882887890");
        String url = url1.toString();
        //  System.out.println(url);
        Page page = webClient.getPage(url);
        WebResponse response = page.getWebResponse();
        String json = response.getContentAsString();
        JSONObject jsonObject = new JSONObject(json);
        // System.out.println(json);
        return simplifyJson(jsonObject);
    }

    @Override
    public String getTrends(int market, String code, int day) throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        StringBuilder url1 = new StringBuilder("http://push2his.eastmoney.com/api/qt/stock/trends2/get?fields1=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13&fields2=f51,f52,f53,f54,f55,f56,f57,f58&secid=");
        url1.append(market).append(".").append(code).append("&nday=").append(day);
        String url =url1.toString();
        Page page = webClient.getPage(url);
        WebResponse response = page.getWebResponse();
        String json = response.getContentAsString();
        JSONObject jsonObject = new JSONObject(json);
        JSONObject mydata = jsonObject.getJSONObject("data");
        JSONObject bigObject = new JSONObject().put("name", mydata.get("name")).put("prePrice",mydata.get("prePrice"));
        JSONArray jsonArray = new JSONArray();
        JSONArray myjsonarry = mydata.getJSONArray("trends");
        List list = myjsonarry.toList();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i).toString();
            String[] strArray = null;
            strArray = str.split(",");
            JSONObject object = new JSONObject();
            object.put("time", strArray[0]);
            object.put("price", strArray[7]);
            object.put("volume", strArray[5]);
            jsonArray.put(i, object);
        }
        bigObject.put("trends", jsonArray);
        return bigObject.toString();
    }

    @Override
    public String getFreshPrice(int market, String code) throws IOException {
        String object=getTrends(market,code,1);
        JSONObject jsonObject=new JSONObject(object);
        JSONArray jsonArray=jsonObject.getJSONArray("trends");
        List list = jsonArray.toList();
        jsonObject=jsonArray.getJSONObject(list.size()-1);
        return jsonObject.get("price").toString();

    }

    private String simplifyJson(JSONObject jsonObject) {
        JSONObject mydata = jsonObject.getJSONObject("data");
        JSONObject bigObject = new JSONObject().put("name",mydata.get("name"));
        JSONArray jsonArray=new JSONArray();
        JSONArray myjsonarry = mydata.getJSONArray("klines");
        List list=myjsonarry.toList();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i).toString();
            String[] strArray = null;
            strArray = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray
            //   System.out.println(strArray[0]);
            JSONObject object=new JSONObject();
            object.put("time",strArray[0]);
            object.put("opening",strArray[1]);
            object.put("closing",strArray[2]);
            object.put("highest",strArray[3]);
            object.put("lowest",strArray[4]);
            object.put("volume",strArray[5]);
            object.put("deal",strArray[6]);
            object.put("amplitude",strArray[7]);
            object.put("Chg",strArray[8]);
            object.put("Iad",strArray[9]);
            object.put("turnoverRate",strArray[10]);
            jsonArray.put(i,object);
        }
        bigObject.put("klines",jsonArray);
        return bigObject.toString();
    }
}
