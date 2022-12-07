package co.kr.web02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( "file:src/main/resources/db/conn.xml")
public class DBConnTest {
	
	@Autowired private DataSource ds;
	@Autowired private SqlSession sql;
	
	@Test
	public void query_test() {
		Date today = sql.selectOne("test.today");
		System.out.println( "오늘: " + today );
	}
	
	
	@Test
	public void connect() throws SQLException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			System.out.println("DB Connection 성공: " + conn);
		}catch(Exception e) {
			System.out.println("DB Connection 실패");
		}finally {
			conn.close();
		}
	}
}
