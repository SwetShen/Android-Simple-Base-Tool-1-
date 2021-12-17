package com.archerswet.bookstore.bean;

import java.util.List;

/**
 * @description:message
 * @author:archerswet@163.com
 * @date:2021/12/10
 */
public class Book {

    private Integer _id;
    private String author;
    private String press;
    private String title;
    private String original_name;
    private String translator;
    private String publish_date;
    private Integer page_num;
    private Float value;
    private Integer count;
    private List<String> desc;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public Integer getPage_num() {
        return page_num;
    }

    public void setPage_num(Integer page_num) {
        this.page_num = page_num;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<String> getDesc() {
        return desc;
    }

    public void setDesc(List<String> desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Book{" +
                "_id=" + _id +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", title='" + title + '\'' +
                ", original_name='" + original_name + '\'' +
                ", translator='" + translator + '\'' +
                ", publish_date='" + publish_date + '\'' +
                ", page_num=" + page_num +
                ", value=" + value +
                ", count=" + count +
                ", desc=" + desc +
                '}';
    }
}
