package com.mayousheng.www.controller;

import com.jfinal.core.Controller;

public class IndexController extends Controller {
    public void index() {
        render("index.html");
    }
}