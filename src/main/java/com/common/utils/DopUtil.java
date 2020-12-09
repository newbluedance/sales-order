package com.common.utils;

import com.alibaba.fastjson.JSON;
import com.deppon.dop.module.sdk.shared.domain.result.ResultDO;
import com.deppon.dop.module.sdk.shared.util.FastJsonUtil;
import com.deppon.dop.module.sdk.shared.util.HttpUtils;
import com.deppon.dop.module.sdk.shared.util.SecurityUtil;
import com.licf.app.entity.dto.DbOrderReq;
import com.licf.app.entity.dto.DbOrderResp;
import org.apache.commons.httpclient.NameValuePair;

import java.util.HashMap;
import java.util.Map;

public class DopUtil {

    public static DbOrderResp addDbOrder(DbOrderReq common) {
        String url = "http://dpsanbox.deppon.com/sandbox-web/dop-standard-ewborder/createOrderNotify.action";
        return JSON.parseObject(dbPost(common, url), DbOrderResp.class);
    }


    public static String traceQuery(Object common) {
        Map<String, String> map = new HashMap<>();
        map.put("mailNo", "DPK210108678562");
        String url = "http://dpsanbox.deppon.com/sandbox-web/standard-order/newTraceQuery.action";
        String s = dbPost(map, url);
        System.out.println(s);
        return s;
    }

    public static String dbPost(Object common, String url) {
        //订单内容 json字符串，SDK提供FastJsonUtil转Json
        String params = FastJsonUtil.toJSONString(common);
        //请求url
        //companyCode与appkey为双方约定
        String companyCode = "EWBGHSWKJJT";
        String appkey = "687cf616f6f4461ecae6e5ecbb70b4fd";
        //时间戳 SDK提供SecurityUtil获取时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());
        //摘要 SDK提供SecurityUtil生成摘要
        String digest = SecurityUtil.getCommonDigest(params + appkey + timestamp, null);
        //post请求参数
        NameValuePair[] data = new NameValuePair[4];
        data[0] = new NameValuePair("companyCode", companyCode);
        data[1] = new NameValuePair("digest", digest);
        data[2] = new NameValuePair("timestamp", timestamp);
        data[3] = new NameValuePair("params", params);
        ResultDO<String> response = null;
        //返回结果
        response = HttpUtils.sendRequest(url, data, "UTF-8", 5000);
        return response.getModel();
    }
}
