package com.mlick.demo.other.jiankangduoduo;

/**
 * Created by lxx on 2016/6/15 9:23
 */
public class PersonTabBean {
    private String name;
    private String title;

    public PersonTabBean(String s) {
        setName(s);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if ("+".equals(name)) {
            this.name = "新家人";
        } else {
            this.name = name;
        }
        setTitle(name);
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {//外部调用不了
        if (title != null && title.length() > 0) {
            this.title = title.substring(0, 1);//取出第一个字母
        }
    }
}
