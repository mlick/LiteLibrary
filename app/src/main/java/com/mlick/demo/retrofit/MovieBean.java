package com.mlick.demo.retrofit;

import java.util.List;

/**
 * Created by lxx on 2016/6/28 11:27
 */
public class MovieBean extends ResultBean {
    /**
     * data : [{"_id":"5770e470ff7d23f820ab4284","doctor":"Mlick","title":"机械战警001","language":"EN","country":"USA","year":"2016","poster":"http://www.chinacomic.com.cn/Upload/Image/2008_06/20080603_132107_182403.jpg","flash":"http://player.youku.com/player.php/sid/XNjA1Njc0NTUy/v.swf","summary":"机械战警机械战警机械战警机械战警机械战警机械战警机械战警机械战警机械战警机械战警","__v":0,"mate":{"updateAt":"2016-06-28T02:10:32.564Z","createAt":"2016-06-27T08:31:44.874Z"}},{"_id":"5771dc3036687f2016300288","doctor":"Mlick","title":"机械战警002","language":"英语","country":"美国","year":"2015","poster":"http://www.chinacomic.com.cn/Upload/Image/2008_06/20080603_132107_182403.jpg","flash":"http://player.youku.com/player.php/sid/XNjA1Njc0NTUy/v.swf","summary":"机械战警机械战警机械战警机械战警","__v":0,"mate":{"updateAt":"2016-06-28T02:11:06.426Z","createAt":"2016-06-28T02:08:48.449Z"}},{"_id":"5771dd0ded32b9d01f7f4cca","doctor":"Mlick","title":"机械战警003","language":"英语","country":"美国","year":"2015","poster":"http://www.chinacomic.com.cn/Upload/Image/2008_06/20080603_132107_182403.jpg","flash":"http://player.youku.com/player.php/sid/XNjA1Njc0NTUy/v.swf","summary":"机械战警机械战警机械战警机械战警","__v":0,"mate":{"updateAt":"2016-06-28T02:12:29.123Z","createAt":"2016-06-28T02:12:29.123Z"}},{"_id":"5771dd0ded32b9d01f7f4ccc","doctor":"Mlick","title":"机械战警004","language":"英语","country":"美国","year":"2015","poster":"http://www.chinacomic.com.cn/Upload/Image/2008_06/20080603_132107_182403.jpg","flash":"http://player.youku.com/player.php/sid/XNjA1Njc0NTUy/v.swf","summary":"机械战警机械战警机械战警机械战警","__v":0,"mate":{"updateAt":"2016-06-28T02:12:29.123Z","createAt":"2016-06-28T02:12:29.123Z"}}]
     * result : 0
     */
    /**
     * _id : 5770e470ff7d23f820ab4284
     * doctor : Mlick
     * title : 机械战警001
     * language : EN
     * country : USA
     * year : 2016
     * poster : http://www.chinacomic.com.cn/Upload/Image/2008_06/20080603_132107_182403.jpg
     * flash : http://player.youku.com/player.php/sid/XNjA1Njc0NTUy/v.swf
     * summary : 机械战警机械战警机械战警机械战警机械战警机械战警机械战警机械战警机械战警机械战警
     * __v : 0
     * mate : {"updateAt":"2016-06-28T02:10:32.564Z","createAt":"2016-06-27T08:31:44.874Z"}
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }
}
