package com.mayousheng.www.utils;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.c3p0.C3p0Plugin;

import javax.sql.DataSource;

/**
 * Created by marking on 2017/4/29.
 * 在idea下无法自动生成，eclipse下可以
 */
public class MyGenerator {

    public static DataSource getDataSource() {
        Prop prop = PropKit.use("config.properties");
        C3p0Plugin c3p0Plugin = new C3p0Plugin(prop.get("jdbcUrl"), prop.get("user"), prop.get("password"));
        c3p0Plugin.start();
        return c3p0Plugin.getDataSource();
    }

    public static void main(String[] args) {
        String modelPackageName = "com.mayousheng.www.model";
        String baseModelPackageName = modelPackageName + ".base";
        String modelOutputDir = PathKit.getWebRootPath() + "/src/main/java/" + modelPackageName;
        String baseModelOutputDir = modelOutputDir + ".base";
        Generator gernerator = new Generator(getDataSource(), baseModelPackageName,
                baseModelOutputDir, modelPackageName, modelOutputDir);
        gernerator.setGenerateDaoInModel(true);
        gernerator.setGenerateDataDictionary(true);
        gernerator.generate();
    }

}
