package common;

import dao.OrderDao;
import dao.PhoneDao;
import dao.PhoneDetailDao;
import dao.UserDao;
import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisManager {
    private static final SqlSessionFactory sqlSessionFactory;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("config/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException("MyBatis 설정 파일 로딩 실패", e);
        }
    }

    public static SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }

    public static PhoneDao getPhoneDao(SqlSession session) {
        return session.getMapper(PhoneDao.class);
    }

    public static UserDao getUserDao(SqlSession session) {
        return session.getMapper(UserDao.class);
    }

    public static PhoneDetailDao getPhoneDetailDao(SqlSession session) {
        return session.getMapper(PhoneDetailDao.class);
    }

    public static OrderDao getOrderDao(SqlSession session) {
        return session.getMapper(OrderDao.class);
    }
}

