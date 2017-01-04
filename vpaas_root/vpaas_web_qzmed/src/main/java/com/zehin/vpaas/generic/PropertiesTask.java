package com.zehin.vpaas.generic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;


public class PropertiesTask {
	private static Properties props = new Properties();
	private String profilepath=PropertiesTask.class.getClassLoader().getResource("task.properties").getPath().replaceAll("%20", " ");
	
	public Properties getPro(){
		try {
			System.out.println("get=="+profilepath);
            props.load(new FileInputStream(profilepath)); 
            return props;
        } catch (IOException e) {
            System.err.println("属性文件更新错误" + e.getMessage());
            return props;
        }
	}
	public void setPro(String key, String val){
		System.out.println("systemSave" + val+"=="+profilepath);
		try {
            props.load(new FileInputStream(profilepath));
            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。.replaceAll("%20", " ")
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(profilepath);  
            System.out.println(props.getProperty("email.send.server"));
            props.setProperty(key, val);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "Update '" + key + "' value");
            fos.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
	}
}
