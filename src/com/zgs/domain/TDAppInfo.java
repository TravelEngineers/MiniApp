package com.zgs.domain;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Shmily
 * @email shmily_zgs@163.com
 * @date 2018/3/6 14:22
 */
public class TDAppInfo {
    private SimpleStringProperty ip;
    private SimpleStringProperty port;
    private SimpleStringProperty checkUrl;
    private SimpleStringProperty pageUrl;
    private SimpleStringProperty appDesc;
    private SimpleStringProperty status;

    public TDAppInfo(String ip, String port, String checkUrl, String pageUrl, String appDesc, String status) {
        this.ip = new SimpleStringProperty(ip);
        this.port = new SimpleStringProperty(port);
        this.checkUrl =new SimpleStringProperty( checkUrl);
        this.pageUrl = new SimpleStringProperty(pageUrl);
        this.appDesc = new SimpleStringProperty(appDesc);
        this.status = new SimpleStringProperty(status);
    }

    public String getIp() {
        return ip.get();
    }

    public SimpleStringProperty ipProperty() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip.set(ip);
    }

    public String getPort() {
        return port.get();
    }

    public SimpleStringProperty portProperty() {
        return port;
    }

    public void setPort(String port) {
        this.port.set(port);
    }

    public String getCheckUrl() {
        return checkUrl.get();
    }

    public SimpleStringProperty checkUrlProperty() {
        return checkUrl;
    }

    public void setCheckUrl(String checkUrl) {
        this.checkUrl.set(checkUrl);
    }

    public String getPageUrl() {
        return pageUrl.get();
    }

    public SimpleStringProperty pageUrlProperty() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl.set(pageUrl);
    }

    public String getAppDesc() {
        return appDesc.get();
    }

    public SimpleStringProperty appDescProperty() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc.set(appDesc);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
