package com.mayousheng.www.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.mayousheng.www.model.base.BaseNews;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by marking on 2017/4/30.
 */
public class NewsService {
    private static final String select_news = "SELECT title from news WHERE title = ?;";
    private static final String find_news = "select * from news where type = ? order by id asc limit ?, ?;";
    private static final String insert_news =
            "INSERT INTO news (`type`, `title`, `description`, `picUrl`, `url`, `ctime`, `created_at`, `updated_at`)" +
                    " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);";

    public List<BaseNews> getNews(String type, int page, int num) {
        List<BaseNews> result = new ArrayList<BaseNews>();
        if (type != null && !type.isEmpty() && page >= 0 && num > 0) {
            List<Record> records = Db.find(find_news, type, page * num, num);
            if (records != null) {
                for (Record record : records) {
                    BaseNews baseNews = new BaseNews() {
                    };
                    baseNews.setType(record.getStr("type"));
                    baseNews.setTitle(record.getStr("title"));
                    baseNews.setDescription(record.getStr("description"));
                    baseNews.setPicUrl(record.getStr("picUrl"));
                    baseNews.setUrl(record.getStr("url"));
                    baseNews.setCtime(record.getDate("ctime"));
                    result.add(baseNews);
                }
            }
        }
        return result;
    }

    public void saveNews(BaseNews news) throws Exception {
        if (news != null && !isBaseNewsEmpty(news) && !news.getPicUrl().isEmpty()) {
            Record record = Db.findFirst(select_news, news.getTitle());
            if (record == null) {
                Db.update(insert_news, news.getType(), news.getTitle(), news.getDescription(), news.getPicUrl()
                        , news.getUrl(), news.getCtime(), new Date(), new Date());
            }
        }
    }

    public void saveNewsList(List<BaseNews> newss) throws Exception {
        if (newss != null) {
            for (BaseNews news : newss) {
                saveNews(news);
            }
        }
    }


    private boolean isBaseNewsEmpty(BaseNews news) {
        return news == null || news.getTitle() == null || news.getType() == null || news.getDescription() == null
                || news.getPicUrl() == null || news.getUrl() == null || news.getCtime() == null;
    }

}
