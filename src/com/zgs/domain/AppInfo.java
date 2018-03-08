package com.zgs.domain;

import java.io.Serializable;

/**
 * @author Shmily
 * @email shmily_zgs@163.com
 * @date 2018/3/6 11:41
 */
public class AppInfo implements Serializable {
    private String ip;
    private String port;
    private String checkUrl;
    private String pageUrl;
    private String appDesc;
    private String status;

    public AppInfo(String ip, String port, String checkUrl, String pageUrl, String appDesc) {
        this.ip = ip;
        this.port = port;
        this.checkUrl = checkUrl;
        this.pageUrl = pageUrl;
        this.appDesc = appDesc;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getCheckUrl() {
        return checkUrl;
    }

    public void setCheckUrl(String checkUrl) {
        this.checkUrl = checkUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
