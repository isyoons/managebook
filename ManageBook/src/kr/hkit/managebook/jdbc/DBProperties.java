package kr.hkit.managebook.jdbc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class DBProperties {
	
	public static void prnProperties(Properties prop){
		for( Entry<Object,Object> entry : prop.entrySet()){
			System.out.printf("key : %s - value : %s %n", entry.getKey(), entry.getValue());
		}
	}
	
	public static Properties loadProperties(String propertiesFilePath){
		BufferedInputStream bis = null;
		Properties prop = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(propertiesFilePath));
			prop = new Properties();
			prop.loadFromXML(bis);
		} catch (FileNotFoundException e) {
			System.err.println("파일이 존재 하지 않음");
		} catch (InvalidPropertiesFormatException e) {
			System.err.println("유효하지 않은 설정 파일");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(bis);
		}
		return prop;
	}
	
	public static void saveProperties(String propertiesFilePath, Map<String, String> loadData){
		BufferedOutputStream bos = null;
		Properties prop = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(propertiesFilePath));
			prop = new Properties();
			for( Entry<String,String> entry : loadData.entrySet()){
				prop.put(entry.getKey(), entry.getValue());
			}
			prop.storeToXML(bos, "Projec Configuration XML Property File");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(bos);
		}
	}
}
