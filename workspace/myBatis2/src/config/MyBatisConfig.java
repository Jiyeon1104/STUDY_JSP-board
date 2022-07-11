package config;

import java.io.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConfig {
	private static SqlSessionFactory sqlsession_f;
	
	static {
		try {
		String resource = "./config/config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		sqlsession_f = new SqlSessionFactoryBuilder().build(reader);
		} catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("초기화 문제 발생, MyBatisConfig.java");
		}
	}


public static SqlSessionFactory getSqlsession_f() {
	return sqlsession_f;
}

}