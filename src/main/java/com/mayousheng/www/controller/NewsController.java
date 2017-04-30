package com.mayousheng.www.controller;

import com.jfinal.core.Controller;
import com.mayousheng.www.service.NewsService;

/**
 * Created by marking on 2017/4/30.
 */
public class NewsController extends Controller {

    public void getNews() {
        renderJson(new NewsService().getNews(getPara("type")
                , getParaToInt("page"), getParaToInt("num")));
    }

}
