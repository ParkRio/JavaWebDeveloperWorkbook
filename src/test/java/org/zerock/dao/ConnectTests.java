package org.zerock.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTests {

    @Test
    public void test1() {

        int v1 = 10;
        int v2 = 10;

        Assertions.assertEquals(v1,v2);
    }

    @Test
    public void testConnection() throws Exception {

        Class.forName("org.mariadb.jdbc.Driver");
        // JDBC 드라이버 클래스를 메모리상으로 로딩하는 역할을 한다.
        // 이때 문자열은 패키지명과 클래스명의 대소문자까지 정확히 일치해야한다.
        // 만일 JDBC 드라이버 파일이 없는 경우에는 이 부분에서 예외가 발생하게 된다.

        // Connection connection은 데이터베이스와 네트워크의 연결을 의미한다.
        Connection connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/webdb",
                "webuser",
                "webuser"
        );
        // "jdbc:mariadb://localhost:3306/webdb"
        // jdbc 프로토콜을 이용한다는 의미이고, localhost:3306은 네트워크 연결 정보를,
        // webdb 는 연결하려는 데이터베이스 정보를 의미한다.
        // webuser, webuser 우리가 설정한 mariadb 의 계정 정쳬

        Assertions.assertNotNull(connection);

        connection.close();
    }

    @Test
    public void testHikariCP() throws Exception {

        // HikariCP 를 사용하기 위해선 HikariConfig 타입의 객체를 생성해주어야 한다.
        // HikariConfig 는 Connection Pool 을 설정하는데 있어서 필요한 정보를 가지고 있는 객체로
        // 이를 이용해서 HikariDataSource 라는 객체를 만든다.
        // HikariDateSource 는 getConnection() 을 제공하므로 이를 이용해서
        // Connection 객체를 얻어서 사용할 수 있다.
        HikariConfig config = new HikariConfig();

        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb");
        config.setUsername("webuser");
        config.setPassword("webuser");
        config.addDataSourceProperty("cachePrepStmts", true);
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config);
        Connection connection = ds.getConnection();

        System.out.println(connection);

        connection.close();
    }
}
