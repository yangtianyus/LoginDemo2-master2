package com.wd.logindemo2.bean;

/**
 * @author dingtao
 * @date 2018/12/18 16:23
 * qq:1940870847
 */
public class Shop {
    String id;
    String name;
    int textColor = 0xff000000;
    int background = 0xffffffff;

    boolean check;

    public void setBackground(int background) {
        this.background = background;
    }

    public int getBackground() {
        return background;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCheck() {
        return check;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
