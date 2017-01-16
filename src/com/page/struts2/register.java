package com.page.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Gypsophila on 2016/12/26.
 */
public class register extends ActionSupport {
    private static String deviceType;
    private static String deviceAddress;

    public ArrayList<manage> getMa() {
        return ma;
    }

    public void setMa(ArrayList<manage> ma) {
        this.ma = ma;
    }

    private ArrayList<String> deviceManager = new ArrayList<String>();
    private  ArrayList<String> deviceId = new ArrayList<String>();
    private ArrayList<String> deviceName = new ArrayList<String>();
    private ArrayList<String> address = new ArrayList<String>();
    private ArrayList<String> isselected = new ArrayList<String>();
    private ArrayList<manage> ma = new ArrayList<manage>();

    public ArrayList<String> getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(ArrayList<String> deviceId) {
        this.deviceId = deviceId;
    }

    public ArrayList<String> getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(ArrayList<String> deviceName) {
        this.deviceName = deviceName;
    }

    public ArrayList<String> getAddress() {
        return address;
    }

    public void setAddress(ArrayList<String> address) {
        this.address = address;
    }

    public ArrayList<String> getIsselected() {
        return isselected;
    }

    public void setIsselected(ArrayList<String> isselected) {
        this.isselected = isselected;
    }

    public ArrayList<String> getDeviceManager() {
        return deviceManager;
    }

    public void setDeviceManager(ArrayList<String> deviceManager) {
        this.deviceManager = deviceManager;
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

    private ArrayList<device> devices = new ArrayList<device>();
    private int i = 0;

    public String safeAddressAction() {
        java.sql.Connection conn = null;
        String URL = "jdbc:mysql://localhost/random";
        String sql1 = "CREATE TABLE device_address ('device_Address' varchar(80))";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, "root", "root");
            PreparedStatement ps;
            ps = conn.prepareStatement(sql1);
            int result = ps.executeUpdate();
            if (result > -1) {
                System.out.println("create table success");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return ERROR;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e.toString());
                    return ERROR;
                }
            }
        }
        //String sql2 = "SELECT * FROM ";
        /*try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL,"root","root");
            sql2 = sql2 + deviceType;
            PreparedStatement ps = conn.prepareStatement(sql2);
            ResultSet rs = ps.executeQuery(sql2);
            ResultSetMetaData data = rs.getMetaData();
            for(int i = 1;i<=data.getColumnCount();++i){

            }
        }catch (Exception e){

        }finally {

        }*/

        return SUCCESS;
    }

    public String ThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int inddex = i;
            try {
                Thread.sleep(inddex * 1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    register r = new register();
                    register.registerDev();
                }
            });
        }
        return SUCCESS;
    }

    private HttpServletRequest getRequest() {
        ActionContext ctx = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
        return request;
    }

    public String manager() {
        java.sql.Connection conn = null;
        String URL = "jdbc:mysql://localhost/random";
        String sql = "SELECT * FROM t_newdevice";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, "root", "root");
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            int i = 0;

            while (rs.next()) {
                manage m = new manage();
                m.setId(rs.getString(1));
                m.setType(rs.getString(2));
                m.setAddress(rs.getString(3));
                m.setFlag(rs.getString(4));
                ma.add(m);
                deviceId.add(rs.getString(1));
                deviceName.add(rs.getString(2));
                address.add(rs.getString(3));
                isselected.add(rs.getString(4));
            }
            //ActionContext.getContext().put("idlist",deviceId);
            //ActionContext.getContext().put("namelist",deviceName);
            //ActionContext.getContext().put("addresslist",address);
            //ActionContext.getContext().put("selectlist",isselected);
            HttpServletRequest request = getRequest();
            //request.setAttribute("idlist",deviceId);
            //request.setAttribute("namelist",deviceName);
            //request.setAttribute("addressList",address);
            //request.setAttribute("selectlist",isselected);
            request.setAttribute("manage", ma);
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            }
        }
        return SUCCESS;
    }



    public String testRegister() {
        java.sql.Connection conn = null;
        String URL = "jdbc:mysql://localhost/random";
        String sql = "INSERT INTO t_newdevice (deviceType,deviceAddress,isSelected) values(";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, "root", "root");
            int a = 0;
            sql = sql + "'" + deviceType + "','" + deviceAddress + "'," + "'" + a + "')";
            PreparedStatement ps = conn.prepareStatement(sql);
            int rs = ps.executeUpdate(sql);
            System.out.println("execute success");
        } catch (Exception e) {
            System.out.println(e.toString());
            return ERROR;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        }
        return SUCCESS;
    }

    static int count = 0;

    public class Mythread extends Thread {
        public void run(String id) {
            //druidtest dt = new druidtest();
            //dt.start();
            getvalue(id);
            System.out.println(count++);
            System.out.println("MyThread.run()");
        }
    }

    public void getvalue(String id){
        java.sql.Connection conn = null;
        String URL = "jdbc:mysql://localhost/random";
        HttpServletRequest request = getRequest();
        String maid = request.getParameter("userServId");
        String sql = "SELECT * FROM t_newdevice WHERE id=" + maid;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, "root", "root");
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            String  location = new String();
            String iddevice = new String();
            if(rs.next()){
                location = rs.getString(3);
                iddevice = rs.getString(2);
                System.out.println(location);
                System.out.println(iddevice);
            }
            String URL2 = "jdbc:mysql://"+location;
            String sql2 = "SELECT * FROM "+ iddevice;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(URL2, "root", "root");
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ResultSet rs2 = ps2.executeQuery(sql2);
                int i = 0;
                String result = new String();

                while (rs2.next()) {
                    result = "{" + "\"";
                    for(int j = 0 ; j<rs2.getMetaData().getColumnCount();j++){
                        result = result + rs2.getMetaData().getColumnName(j+1) + "\""+":" +"\""+ rs2.getString(j+1) +"\"" +",";

                    }
                    result = result.substring(0,result.length()-1);
                    result +="}";
                    System.out.println(result);
                    //在这给kafka传result作为content
                }
            }catch (Exception e){
                System.out.println(e.toString());
            }
    }catch (Exception e){
            System.out.println(e.toString());
    }
    }


    public String start() {
        System.out.println("start!");
        HttpServletRequest request = getRequest();
        String maid = request.getParameter("userServId");
        System.out.println(maid);
        Mythread mythread = new Mythread();
        mythread.run(maid);
        return SUCCESS;
    }

    public static String dateFormat() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public static String registerDev() {
        ArrayList<String> devicesColumn = new ArrayList<String>();
        java.sql.Connection conn1 = null;
        java.sql.Connection conn2 = null;
        String URL = "jdbc:mysql://localhost/random";
        String address = "jdbc:mysql://" + deviceAddress;
        String sql = "SELECT * FROM " + deviceType;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn1 = DriverManager.getConnection(URL, "root", "root");
            PreparedStatement ps = conn1.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            ResultSetMetaData data = rs.getMetaData();
            if (rs.next())
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    String columnName = data.getColumnName(i);
                    devicesColumn.add(columnName);
                }
            conn2 = DriverManager.getConnection(address, "root", "root");
            PreparedStatement ps2 = conn2.prepareStatement(sql);
            ResultSet rs2 = ps2.executeQuery(sql);
            ResultSetMetaData data2 = rs2.getMetaData();
            String time = "";
            int j = 0;
            while (rs2.next()) {
                String topic = "zTest1225";
                String temp = "{";
                for (int i = 0; i < 9; ++i) {

                    temp += "\"";
                    temp += data.getColumnName(i + 1);
                    temp += "\"";
                    temp += ":";
                    temp += "\"";
                    temp += rs.getString(i + 1);
                    temp += "\"";
                    temp += ",";

                }
                time = dateFormat();
                temp += "\"" + "TIMESTAMP" + "\"" + ":" + "\"" + time + "\"";
                temp = temp.substring(0, temp.length() - 1);
                temp += "}";
                System.out.println(temp);
                ++j;


            }

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        }


        return SUCCESS;

    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }
}
