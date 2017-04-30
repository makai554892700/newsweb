package com.mayousheng.www.utils;

import com.jfinal.json.Json;
import com.mayousheng.www.model.base.BaseNews;
import com.mayousheng.www.model.wangyi.WangYiNews;
import com.mayousheng.www.model.wangyi.WangYiResult;
import com.mayousheng.www.service.NewsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by marking on 2017/4/30.
 */
public class WangYiNewsUtils {

    private static WangYiNewsUtils wangYiNewsUtils;
    //军事,军事,体育,科技,教育,娱乐,财经,股票,旅游,女人
    private static final String[] types = new String[]{"war"
            , "sport", "tech", "edu", "ent", "money", "gupiao", "travel", "lady"};

    private WangYiNewsUtils() {
    }

    public static WangYiNewsUtils getInstance() {
        if (wangYiNewsUtils == null) {
            wangYiNewsUtils = new WangYiNewsUtils();
        }
        return wangYiNewsUtils;
    }

    public void loadNews() {
        String form = "http://wangyi.butterfly.mopaasapp.com/news/api?type=%s&page=1&limit=10";
        String tempUrl;
        for (int i = 0; i < types.length; i++) {
            tempUrl = String.format(form, types[i]);
            HttpUtils.getInstance().getURLResponse(tempUrl, null, new HttpUtils.IWebCallback() {
                @Override
                public void onCallback(int status, String message, Map<String, List<String>> heard, byte[] data) {
                    if (status == 200 && data != null) {
                        try {
                            new NewsService().saveNewsList(getBaseNewssByJsonStr(new String(data, "utf-8")));
                        } catch (Exception e) {
                            System.out.println("e=" + e);
                        }
                    }
                }

                @Override
                public void onFail(int status, String message) {

                }
            });
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
            }
        }
    }

    public List<BaseNews> getBaseNewssByJsonStr(String jsonStr) {
        if (jsonStr == null || jsonStr.isEmpty()) {
            return null;
        }
        WangYiResult wangYiResult = Json.getJson().parse(jsonStr, WangYiResult.class);
        if (wangYiResult != null) {
            List<WangYiNews> wangYiNewss = wangYiResult.getList();
            if (wangYiNewss != null) {
                List<BaseNews> result = new ArrayList<BaseNews>();
                for (WangYiNews wangYiNews : wangYiNewss) {
                    BaseNews baseNews = new BaseNews() {
                    };
                    baseNews.setType(wangYiNews.getChannelname());
                    baseNews.setTitle(wangYiNews.getTitle());
                    baseNews.setDescription(wangYiNews.getTitle());
                    baseNews.setPicUrl(wangYiNews.getImgurl());
                    baseNews.setUrl(wangYiNews.getDocurl());
                    baseNews.setCtime(wangYiNews.getTime());
                    result.add(baseNews);
                }
                return result;
            }
        }
        return null;
    }

}
