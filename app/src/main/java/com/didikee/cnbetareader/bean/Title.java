package com.didikee.cnbetareader.bean;

/**
 * Created by didik on 2017/2/9.
 */

public class Title {
    public String title = "";
    public String subTitle = "";
    public boolean isShowBackIcon = true;
    public boolean isShowLogo = false;

    public Title(String title) {
        this.title = title;
    }

    public Title(String title, boolean isShowBackIcon) {
        this.title = title;
        this.isShowBackIcon = isShowBackIcon;
    }

    public Title(String title, String subTitle, boolean isShowBackIcon) {
        this.title = title;
        this.subTitle = subTitle;
        this.isShowBackIcon = isShowBackIcon;
    }

    public Title(String title, String subTitle, boolean isShowBackIcon, boolean isShowLogo) {
        this.title = title;
        this.subTitle = subTitle;
        this.isShowBackIcon = isShowBackIcon;
        this.isShowLogo = isShowLogo;
    }
}
