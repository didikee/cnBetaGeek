package com.didikee.cnbetareader.utils;

import android.text.TextUtils;

/**
 * Created by didik 
 * Created time 2017/2/9
 * Description: 
 */

public class HtmlUtil {
    private static final String filter = "<p.*?>";//"<strong>", "</strong>"

    public static String htmlFilter(String htmlTxt) {
        if (TextUtils.isEmpty(htmlTxt)) {
            return htmlTxt;
        }
        htmlTxt = htmlTxt.replaceAll(filter, "");
        htmlTxt = htmlTxt.replaceAll("<br/></p>|<br></p>|</p>", "<br>");
        htmlTxt = htmlTxt.trim();
        if (htmlTxt.endsWith("<br>")) {
            htmlTxt = htmlTxt.substring(0, htmlTxt.length() - 4);
        }
        return htmlTxt;
    }
}
