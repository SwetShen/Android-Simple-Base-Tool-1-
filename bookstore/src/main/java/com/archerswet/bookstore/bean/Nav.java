package com.archerswet.bookstore.bean;

/**
 * @description:message
 * @author:archerswet@163.com
 * @date:2021/12/12
 */
public class Nav {

    private Integer drawable;
    private String title;

    public Nav() {
    }

    public Nav(Integer drawable, String title) {
        this.drawable = drawable;
        this.title = title;
    }

    public Integer getDrawable() {
        return drawable;
    }

    public String getTitle() {
        return title;
    }
}
