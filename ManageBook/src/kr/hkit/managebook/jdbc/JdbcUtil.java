package kr.hkit.managebook.jdbc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	public static void close(Connection con){
		if (con != null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con = null;
		}
	}
	
	public static void close(BufferedOutputStream bos){
		if (bos != null){
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos = null;
		}
	}
	
	public static void close(PreparedStatement pstmt){
		if (pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pstmt = null;
		}
	}
	public static void close(ResultSet rs){
		if (rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
	}
	public static void close(Statement stmt) {
		if (stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}		
	}

	public static void close(BufferedInputStream bis) {
		if (bis != null){
			try {
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bis = null;
		}		
	}
}
