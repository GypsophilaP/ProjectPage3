package com.page.struts2;

/**
 * Created by Gypsophila on 2016/12/30.
 */
/**
 * Created by root on 10/5/16.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import net.sf.json.JSONObject;
import org.joda.time.DateTime;
import org.omg.CORBA.Any;
import org.omg.PortableInterceptor.SUCCESSFUL;

import static com.opensymphony.xwork2.Action.SUCCESS;

public class druidtest {
    //    private static final String TOPIC = "medical"; //kafka创建的topic
    private static final String TOPIC = "kafka"; //kafka创建的topic  metrics   druidtest
    public String start() {
        System.out.println("started");
        Properties props = new Properties();
//        props.put("metadata.broker.list","centos7:9092");
        props.put("metadata.broker.list","172.21.15.84:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        // key.serializer.class默认为serializer.class
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        // 可选配置，如果不配置，则使用默认的partitioner
        //props.put("partitioner.class", "com.catt.kafka.demo.PartitionerDemo");
        // 触发acknowledgement机制，否则是fire and forget，可能会引起数据丢失
        // 值为0,1,-1,可以参考
        // http://kafka.apache.org/08/configuration.html
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);
        String CONTENT;
        //Send multiple messages.
        //  List<KeyedMessage<String,String>> messages =
        //         new ArrayList<KeyedMessage<String, String>>();
        while(true){
            try{
                Thread.sleep(5);
                CONTENT=randommedical();
//                CONTENT=random();
                KeyedMessage<String, String> message = new KeyedMessage<String, String>(TOPIC, CONTENT);
//                System.out.print(CONTENT+"\n");
                producer.send(message);
            }catch (InterruptedException e) {
            }
            return SUCCESS;
        }

    }
    public static String randommedical(){
        Map map = new HashMap<String,Any>();
        Random rand=new Random();

        String timestamp=new DateTime().toString();
        String USER_ID = ""+rand.nextInt(9)+1;
        String DEVICE_ID =""+rand.nextInt(6)+1;
        String COLLECTION_START_TIME = "2015-12-01 21:00:00";
        int ID = rand.nextInt(6)+1;
        int TIDALVOLUMEVALUE = rand.nextInt(20)+25;
        int BPMVALUE = (rand.nextInt(70)+10)*10;
        int LEAK_VALUE = rand.nextInt(20)+20;
        int HEARRATE_VALUE = rand.nextInt(20)+99;
        int SPO_VALUE = rand.nextInt(5)+90;

        map.put("CREATETIME",timestamp);
        map.put("USER_ID",USER_ID);
        map.put("DEVICE_ID",DEVICE_ID);
        map.put("COLLECTION_START_TIME",COLLECTION_START_TIME);
        map.put("ID",ID);
        map.put("TIDALVOLUMEVALUE",TIDALVOLUMEVALUE);
//        map.put("test",TIDALVOLUMEVALUE);
        map.put("BPMVALUE",BPMVALUE);
        map.put("LEAK_VALUE",LEAK_VALUE);
        map.put("HEARRATE_VALUE",HEARRATE_VALUE);
        map.put("SPO_VALUE",SPO_VALUE);

        JSONObject json = JSONObject.fromObject(map);
        String content = json.toString();
        System.out.println(content);
        return content;
    }
    public static String random(){
        Map map = new HashMap<String,Any>();
        Random rand=new Random();

        String url="http";
        String user="zhansan";
        int latencyMs=rand.nextInt(10)+10;
        String time=new DateTime().toString();

        map.put("time",time);
        map.put("url",url);
        map.put("user",user);
        map.put("latencyMs",latencyMs);

        JSONObject json = JSONObject.fromObject(map);
        String content = json.toString();
        System.out.println("a record values :"+content);
        return content;
    }
}

