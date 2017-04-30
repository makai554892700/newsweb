package com.mayousheng.www.config;

import cn.dreampie.quartz.QuartzPlugin;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.template.Engine;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.json.MixedJsonFactory;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.render.ViewType;
import com.mayousheng.www.controller.IndexController;
import com.mayousheng.www.controller.NewsController;
import com.mayousheng.www.model.News;

public class MainConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants me) {
        PropKit.use("config.properties");
        me.setDevMode(PropKit.getBoolean("devMode"));
        me.setBaseUploadPath("upload/temp/");
        me.setBaseDownloadPath("download");
        me.setViewType(ViewType.JFINAL_TEMPLATE);
        me.setJsonFactory(MixedJsonFactory.me());
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", IndexController.class);
        me.add("/news", NewsController.class);
    }

    @Override
    public void configPlugin(Plugins me) {
        C3p0Plugin dbPlugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dbPlugin);
        arp.setShowSql(PropKit.getBoolean("devMode"));
        arp.setDialect(new MysqlDialect());
        arp.addMapping("news", News.class);
        me.add(dbPlugin);
        me.add(arp);
        me.add(new QuartzPlugin());
    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }

    @Override
    public void configEngine(Engine me) {

    }

    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 8080, "/");
    }


}
