package com.mlick.demo.animation;

import java.io.Serializable;

/**
 * Created by lxx on 2016/8/12 10:34
 */
public class SerBean implements Serializable {

    private String string;

    private SerBean2 serBean2;

    public SerBean(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public SerBean2 getSerBean2() {
        return serBean2;
    }

    public void setSerBean2(SerBean2 serBean2) {
        this.serBean2 = serBean2;
    }

    public SerBean(SerBean2 serBean2) {
        this.serBean2 = serBean2;
    }
}
