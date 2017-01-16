package com.page.struts2;

import com.opensymphony.xwork2.ActionSupport;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * Created by Gypsophila on 2016/12/26.
 */
public class DeviceData extends ActionSupport{
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public ArrayList<device> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<device> devices) {
        this.devices = devices;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    private String deviceType;
    private ArrayList<device> devices = new ArrayList<device>();
    private int i=0;

    public String define(){
        java.sql.Connection conn = null;
        String URL = "jdbc:mysql://localhost/random";
        String sql = "CREATE TABLE ";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL,"root","root");
            sql = sql + this.getDeviceType() + "(";
            int j = devices.size();
            while (j!=0){
                sql = sql +this.getDevices().get(i).getColumnName() + " " +this.getDevices().get(i).getType()+" "+"("+ this.getDevices().get(i).getLength() +"),";
                i++;
                j--;
            }
            sql = sql.substring(0,sql.length()-1);
            sql += ")";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            System.out.println("no exception");
            int result=ps.executeUpdate();
            System.out.println("no exception");
            if(result>-1){
                System.out.println("create table success");
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }finally {
            if(conn!=null){
                try{
                    conn.close();
                }catch (Exception e){
                    System.out.println(e.toString());
                }
                conn = null;
            }
        }
        return SUCCESS;
    }

}
