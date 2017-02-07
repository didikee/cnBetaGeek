package com.didikee.cnbetareader.network;

import com.didikee.cnbetareader.utils.MD5;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by didik 
 * Created time 2017/2/7
 * Description: 
 */

public class RequestUrl {
    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    private static final SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    /**
     * 获取 Sid 小于 endSid 文章列表
     * @param endSid 文章Sid
     * @return 文章列表url
     */
    public static String getContentListUrl(final String endSid) {
        StringBuilder sb = new StringBuilder();
        sb.append("app_key=10000");
        sb.append("&end_sid=").append(endSid);
        sb.append("&format=json");
        sb.append("&method=Article.Lists");
        sb.append("&timestamp=").append(System.currentTimeMillis());
        sb.append("&v=2.8.5");
        final String signed = MD5.md5(sb.toString() + "&mpuffgvbvbttn3Rc");
        sb.append("&sign=").append(signed);
        sb.insert(0, "http://api.cnbeta.com/capi?");
        return sb.toString();
    }

    /**
     * 获取文章内容url
     * @param sid 文章的sid
     * @return 文章内容url
     */
    public static String getContentUrl(final String sid) {
        StringBuilder sb = new StringBuilder();
        sb.append("app_key=10000");
        sb.append("&format=json");
        sb.append("&method=Article.NewsContent");
        sb.append("&sid=").append(sid);
        sb.append("&timestamp=").append(System.currentTimeMillis());
        sb.append("&v=2.8.5");
        final String signed = MD5.md5(sb.toString() + "&mpuffgvbvbttn3Rc");
        sb.append("&sign=").append(signed);
        sb.insert(0, "http://api.cnbeta.com/capi?");
        return sb.toString();
    }

    /**
     * 获取热门的评论列表
     * @return 评论列表
     */
    public static String getRecommendCommentUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append("app_key=10000");
        sb.append("&format=json");
        sb.append("&method=Article.RecommendComment");
        sb.append("&timestamp=").append(System.currentTimeMillis());
        sb.append("&v=2.8.5");
        final String signed = MD5.md5(sb.toString() + "&mpuffgvbvbttn3Rc");
        sb.append("&sign=").append(signed);
        sb.insert(0, "http://api.cnbeta.com/capi?");
        return sb.toString();
    }

    /**
     * 根据类型获取今日排行的文章列表
     * @param type 排行类型
     * @return 文章列表的url
     */
    public static String getTodayRankUrl(final int type) {
        StringBuilder sb = new StringBuilder();
        sb.append("app_key=10000");
        sb.append("&format=json");
        sb.append("&method=Article.TodayRank");
        sb.append("&timestamp=").append(System.currentTimeMillis());
        switch (type) {
            case 1:
                sb.append("&type=comments");
                break;
            case 2:
                sb.append("&type=counter");
                break;
            case 3:
                sb.append("&type=dig");
                break;
        }
        sb.append("&v=2.8.5");
        final String signed = MD5.md5(sb.toString() + "&mpuffgvbvbttn3Rc");
        sb.append("&sign=").append(signed);
        sb.insert(0, "http://api.cnbeta.com/capi?");
        return sb.toString();
    }

    /**
     * 获取文章评论列表
     * @param sid 文章sid
     * @param page 第几页
     * @return 评论列表url
     */
    public static String getCommentsUrl(final String sid, final int page) {
        StringBuilder sb = new StringBuilder();
        sb.append("app_key=10000");
        sb.append("&format=json");
        sb.append("&method=Article.Comment");
        sb.append("&page=").append(page);
        sb.append("&pageSize=20");  //此参数无效
        sb.append("&sid=").append(sid);
        sb.append("&timestamp=").append(System.currentTimeMillis());
        sb.append("&v=2.8.5");
        final String signed = MD5.md5(sb.toString() + "&mpuffgvbvbttn3Rc");
        sb.append("&sign=").append(signed);
        sb.insert(0, "http://api.cnbeta.com/capi?");
        return sb.toString();
    }

    /**
     * 评论文章
     * @param sid 文章sid
     * @param content 评论内容
     * @return 请求评论文章的url
     */
    public static String getCommentArticleUrl(final String sid, final String content) {
        StringBuilder sb = new StringBuilder();
        sb.append("app_key=10000");
        sb.append("&content=").append(content);
        sb.append("&format=json");
        sb.append("&method=Article.DoCmt");
        sb.append("&op=publish");
        sb.append("&sid=").append(sid);
        sb.append("&timestamp=").append(System.currentTimeMillis());
        sb.append("&v=2.8.5");
        final String signed = MD5.md5(sb.toString() + "&mpuffgvbvbttn3Rc");
        sb.append("&sign=").append(signed);
        sb.insert(0, "http://api.cnbeta.com/capi?");
        return sb.toString();
    }

    /**
     * 评论文章
     * @param sid 文章sid
     * @param content 评论内容
     * @return 请求评论文章的url
     */
    public static String getCommentArticleUrl(final String sid, final String pid, final String content) {
        StringBuilder sb = new StringBuilder();
        sb.append("app_key=10000");
        sb.append("&content=").append(content);
        sb.append("&format=json");
        sb.append("&method=Article.DoCmt");
        sb.append("&op=publish");
        sb.append("&pid=").append(pid);
        sb.append("&sid=").append(sid);
        sb.append("&timestamp=").append(System.currentTimeMillis());
        sb.append("&v=2.8.5");
        final String signed = MD5.md5(sb.toString() + "&mpuffgvbvbttn3Rc");
        sb.append("&sign=").append(signed);
        sb.insert(0, "http://api.cnbeta.com/capi?");
        return sb.toString();
    }

    /**
     * 获取文章列表
     * @return 文章列表url
     */
    public static String getContentListUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append("app_key=10000");
        sb.append("&format=json");
        sb.append("&method=Article.Lists");
        sb.append("&timestamp=").append(System.currentTimeMillis());
        sb.append("&v=2.8.5");
        final String signed = MD5.md5(sb.toString() + "&mpuffgvbvbttn3Rc");
        sb.append("&sign=").append(signed);
        sb.insert(0, "http://api.cnbeta.com/capi?");
        return sb.toString();
    }

    /**
     * 获取指定话题类型的文章列表
     * @param topicId 话题类型id
     * @return 文章列表url
     */
    public static String getContentListUrlByTopic(final String topicId) {
        StringBuilder sb = new StringBuilder();
        sb.append("app_key=10000");
        sb.append("&format=json");
        sb.append("&method=Article.Lists");
        sb.append("&timestamp=").append(System.currentTimeMillis());
        sb.append("&topicid=").append(topicId);
        sb.append("&v=2.8.5");
        final String signed = MD5.md5(sb.toString() + "&mpuffgvbvbttn3Rc");
        sb.append("&sign=").append(signed);
        sb.insert(0, "http://api.cnbeta.com/capi?");
        return sb.toString();
    }

    /**
     * 给文章点赞
     * @param sid 文章sid
     * @param tid 文章的topic id
     * @return 点赞文章的url
     */
    public static String getSupportArticleUrl(final String sid, final int tid) {
        StringBuilder sb = new StringBuilder();
        sb.append("app_key=10000");
        sb.append("&format=json");
        sb.append("&method=Article.DoCmt");
        sb.append("&op=support");
        sb.append("&sid=").append(sid);
        sb.append("&tid=").append(tid);
        sb.append("&timestamp=").append(System.currentTimeMillis());
        sb.append("&v=2.8.5");
        final String signed = MD5.md5(sb.toString() + "&mpuffgvbvbttn3Rc");
        sb.append("&sign=").append(signed);
        sb.insert(0, "http://api.cnbeta.com/capi?");
        return sb.toString();
    }

    /**
     * 指定开始的sid,获取指定话题类型的文章列表
     * @param startSid 文章Sid
     * @return 文章列表url
     */
    public static String getContentListUrl(final String startSid, final String topicId, float x) {
        StringBuilder sb = new StringBuilder();
        sb.append("app_key=10000");
        sb.append("&format=json");
        sb.append("&method=Article.Lists");
        sb.append("&start_sid=").append(startSid);
        sb.append("&timestamp=").append(System.currentTimeMillis());
        sb.append("&topicid=").append(topicId);
        sb.append("&v=2.8.5");
        final String signed = MD5.md5(sb.toString() + "&mpuffgvbvbttn3Rc");
        sb.append("&sign=").append(signed);
        sb.insert(0, "http://api.cnbeta.com/capi?");
        return sb.toString();
    }

    /**
     * 获取本月Top 10的文章列表
     * @return 文章列表url
     */
    public static String getTop10Url() {
        StringBuilder sb = new StringBuilder();
        sb.append("app_key=10000");
        sb.append("&format=json");
        sb.append("&method=Article.Top10");
        sb.append("&timestamp=").append(System.currentTimeMillis());
        sb.append("&v=2.8.5");
        final String signed = MD5.md5(sb.toString() + "&mpuffgvbvbttn3Rc");
        sb.append("&sign=").append(signed);
        sb.insert(0, "http://api.cnbeta.com/capi?");
        return sb.toString();
    }

    /**
     * 反对文章
     * @param sid 文章sid
     * @param tid 文章的topic id
     * @return 反对文章的url
     */
    public static String getAgainstArticleUrl(final String sid, final int tid) {
        StringBuilder sb = new StringBuilder();
        sb.append("app_key=10000");
        sb.append("&format=json");
        sb.append("&method=Article.DoCmt");
        sb.append("&op=against");
        sb.append("&sid=").append(sid);
        sb.append("&tid=").append(tid);
        sb.append("&timestamp=").append(System.currentTimeMillis());
        sb.append("&v=2.8.5");
        final String signed = MD5.md5(sb.toString() + "&mpuffgvbvbttn3Rc");
        sb.append("&sign=").append(signed);
        sb.insert(0, "http://api.cnbeta.com/capi?");
        return sb.toString();
    }

    /**
     * 指定结尾的sid,获取指定话题类型的文章列表
     * @param endSid 文章Sid
     * @return 文章列表url
     */
    public static String getContentListUrl(final String endSid, final String topicId) {
        StringBuilder sb = new StringBuilder();
        sb.append("app_key=10000");
        sb.append("&end_sid=").append(endSid);
        sb.append("&format=json");
        sb.append("&method=Article.Lists");
        sb.append("&timestamp=").append(System.currentTimeMillis());
        sb.append("&topicid=").append(topicId);
        sb.append("&v=2.8.5");
        final String signed = MD5.md5(sb.toString() + "&mpuffgvbvbttn3Rc");
        sb.append("&sign=").append(signed);
        sb.insert(0, "http://api.cnbeta.com/capi?");
        return sb.toString();
    }

    /**
     * 获取话题列表
     * @return 话题列表url
     */
    public static String getTopicListUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append("app_key=10000");
        sb.append("&format=json");
        sb.append("&method=Article.NavList");
        sb.append("&timestamp=").append(System.currentTimeMillis());
        sb.append("&v=2.8.5");
        final String signed = MD5.md5(sb.toString() + "&mpuffgvbvbttn3Rc");
        sb.append("&sign=").append(signed);
        sb.insert(0, "http://api.cnbeta.com/capi?");
        return sb.toString();
    }

}
