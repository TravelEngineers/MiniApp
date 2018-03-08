package com.zgs.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zgs.domain.TDAppInfo;
import com.zgs.utils.GlobalConfigUtils;
import com.zgs.utils.StatusCheckUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Shmily
 * @email shmily_zgs@163.com
 * @date 2018/3/5 14:35
 */
public class MainController implements Initializable {

    @FXML
    private ComboBox select_iplist;
    @FXML
    private ComboBox select_portlist;
    @FXML
    private Button ip_check_btn;
    @FXML
    private Button page_check_btn;
    @FXML
    private Button ping_btn;
    @FXML
    private Button telnet_btn;
    @FXML
    private TableView t_data_list;
    @FXML
    private TableColumn t_col_ip;
    @FXML
    private TableColumn t_col_port;
    @FXML
    private TableColumn t_col_curl;
    @FXML
    private TableColumn t_col_pt;
    @FXML
    private TableColumn t_col_result;
    @FXML
    private TextArea debug_tips_area;
    private ObservableList<TDAppInfo> checkData=FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            //初始化的时候调用
            initMenu();
            initEvent();
            bindMap();
            initData();
            initAction();
    }
    public void initMenu(){
        select_iplist.getItems().setAll( GlobalConfigUtils.getServerList());
        select_iplist.setValue("全部");
        select_portlist.getItems().setAll("全部端口");
        select_portlist.setValue("全部端口");
    }
    public void initEvent(){
        select_iplist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if("全部".equals(newValue)){
                    select_portlist.setValue("全部端口");
                }else{
                    select_portlist.getItems().setAll( GlobalConfigUtils.getPortByIp(newValue.toString()));
                    select_portlist.setValue("全部端口");
                }
            }
        });
        select_portlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

            }
        });
    }
    public void bindMap(){
        t_col_ip.setCellValueFactory(new PropertyValueFactory<TDAppInfo, String>("ip"));
        t_col_port.setCellValueFactory(new PropertyValueFactory<TDAppInfo, String>("port"));
        t_col_curl.setCellValueFactory(new PropertyValueFactory<TDAppInfo, String>("checkUrl"));
        t_col_pt.setCellValueFactory(new PropertyValueFactory<TDAppInfo, String>("appDesc"));
        t_col_result.setCellValueFactory(new PropertyValueFactory<TDAppInfo, String>("status"));
    }
    public void initData(){
        checkData.addAll(new TDAppInfo("--","--","--","","--","--"));
        t_data_list.setItems(checkData);
    }
    public void initAction(){
        ip_check_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                checkData=FXCollections.observableArrayList();//清空数据
                debug_tips_area.clear();
                String ip=select_iplist.getValue().toString();
                String port=select_portlist.getValue().toString();
                if(ip.equals("全部")){
                    JSONObject allConfig=GlobalConfigUtils.getServerConfig();
                    for(String cip:allConfig.keySet()){
                        JSONArray tmpList= GlobalConfigUtils.getServerConfig().getJSONArray(cip);
                        for(int i=0;i<tmpList.size();i++){
                            JSONObject p =tmpList.getJSONObject(i);
                            String checkUrl= p.getString("checkUrl");
                            boolean status=StatusCheckUtils.healthCheck(checkUrl);
                            debug_tips_area.appendText("[检测URL]"+checkUrl+",[校测结果]"+status+"\r\n");
                            checkData.addAll(new TDAppInfo(p.getString("ip"),p.getString("port"),p.getString("checkUrl"),p.getString("pageUrl"),p.getString("appDesc"),status==true?"正常":"异常"));
                            t_data_list.setItems(checkData);
                        }
                    }
                }else {
                    if("全部端口".equals(port)){
                       JSONArray tmpList= GlobalConfigUtils.getServerConfig().getJSONArray(ip);
                       for(int i=0;i<tmpList.size();i++){
                           JSONObject p =tmpList.getJSONObject(i);
                           String checkUrl= p.getString("checkUrl");
                           boolean status=StatusCheckUtils.healthCheck(checkUrl);
                           debug_tips_area.appendText("[检测URL]"+checkUrl+",[校测结果]"+status+"\r\n");
                           checkData.addAll(new TDAppInfo(p.getString("ip"),p.getString("port"),p.getString("checkUrl"),p.getString("pageUrl"),p.getString("appDesc"),status==true?"正常":"异常"));
                           t_data_list.setItems(checkData);
                       }
                    }else{
                        JSONArray tmpList= GlobalConfigUtils.getServerConfig().getJSONArray(ip);
                        for(int i=0;i<tmpList.size();i++){
                            JSONObject p =tmpList.getJSONObject(i);
                            if(port.equals(p.getString("port"))){
                                String checkUrl= p.getString("checkUrl");
                                boolean status=StatusCheckUtils.healthCheck(checkUrl);
                                debug_tips_area.appendText("[检测URL]"+checkUrl+",[校测结果]"+status+"\r\n");
                                checkData.addAll(new TDAppInfo(p.getString("ip"),p.getString("port"),p.getString("checkUrl"),p.getString("pageUrl"),p.getString("appDesc"),status==true?"正常":"异常"));
                                t_data_list.setItems(checkData);
                            }
                        }
                    }
                }
            }
        });
    }
}
