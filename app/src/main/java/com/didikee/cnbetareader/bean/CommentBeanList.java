package com.didikee.cnbetareader.bean;

import java.util.List;

/**
 * Created by didik on 2017/2/10.
 */

public class CommentBeanList {

    /**
     * status : success
     * result : [{"tid":"13936685","pid":"0","username":"","content":"世界首款吗?你把中国的孕橙APP放哪里啦?",
     * "created_time":"2017-02-10 17:29:14","support":"1","against":"2"},{"tid":"13936691",
     * "pid":"0","username":"","content":"中国已经无数款了好不?！","created_time":"2017-02-10 17:29:47",
     * "support":"1","against":"3"},{"tid":"13936717","pid":"0","username":"",
     * "content":"下面说的国内的app有临床吗?有第三方认证吗?\u201c2016年针对4000名女性进行的临床试验表明这款app
     * 和避孕药一样有效。目前，德国医学监管部门人员Tuv Sud认可这一app为Class IIb等级的医用设备\u201d","created_time":"2017-02-10
     * 17:40:22","support":"5","against":"0"},{"tid":"13936735","pid":"0","username":"",
     * "content":"关键还是要体温计准确，APP再怎么算也是根据体温。难道还能算出花来?","created_time":"2017-02-10 17:44:41",
     * "support":"1","against":"0"},{"tid":"13936739","pid":"0","username":"",
     * "content":"不是西柚App吗","created_time":"2017-02-10 17:45:34","support":"0","against":"0"},
     * {"tid":"13936743","pid":"0","username":"","content":"30天换30个男人，这个APP能鉴定出爸爸是谁吗",
     * "created_time":"2017-02-10 17:46:20","support":"6","against":"0"},{"tid":"13936751",
     * "pid":"0","username":"","content":"从此可以愉快地约x了吗","created_time":"2017-02-10 17:49:11",
     * "support":"0","against":"0"},{"tid":"13936771","pid":"0","username":"","content":"小心使用
     * 一不小心要出人命的啊","created_time":"2017-02-10 17:53:06","support":"4","against":"0"},
     * {"tid":"13936783","pid":"0","username":"",
     * "content":"处男才会用的方法。在不安全的日子XXOO的体验明显好过其他时间。戴套就行了。","created_time":"2017-02-10 17:54:34",
     * "support":"0","against":"7"},{"tid":"13936821","pid":"0","username":"",
     * "content":"结果还是中标了~~","created_time":"2017-02-10 18:13:30","support":"0","against":"0"}]
     */

    private String status;
    private List<CommentBean> result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CommentBean> getResult() {
        return result;
    }

    public void setResult(List<CommentBean> result) {
        this.result = result;
    }

}
