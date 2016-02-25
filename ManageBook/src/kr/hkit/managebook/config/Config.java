package kr.hkit.managebook.config;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Config {
	public static String USER_DIR = System.getProperty("user.dir");
	public static String EXPORT_DIR = USER_DIR + "\\BackupFiles\\";
	public static String PROPERTIES_PATH = Config.EXPORT_DIR + "config.dat";
	public static DecimalFormat DECIMALF = new DecimalFormat("#,##0");
}
