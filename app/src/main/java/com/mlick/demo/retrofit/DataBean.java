package com.mlick.demo.retrofit;

/**
 * Created by lxx on 2016/6/28 11:28
 */
public class DataBean {
    private String _id;
    private String doctor;
    private String title;
    private String language;
    private String country;
    private String year;
    private String poster;
    private String flash;
    private String summary;
    private int __v;
    /**
     * updateAt : 2016-06-28T02:10:32.564Z
     * createAt : 2016-06-27T08:31:44.874Z
     */

    private MateBean mate;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getFlash() {
        return flash;
    }

    public void setFlash(String flash) {
        this.flash = flash;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public MateBean getMate() {
        return mate;
    }

    public void setMate(MateBean mate) {
        this.mate = mate;
    }

    public static class MateBean {
        private String updateAt;
        private String createAt;

        public String getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(String updateAt) {
            this.updateAt = updateAt;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }
    }
}