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
    private static final ThreadLocal<SqlSession> threadLocalSession = new ThreadLocal<>();

    static {
        try {
            Reader reader = Resources.getResourceAsReader("config/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException("MyBatis 설정 파일 로딩 실패", e);
        }
    }

    public static SqlSession getSession() {
        SqlSession session = threadLocalSession.get();
        if (session == null) {
            session = sqlSessionFactory.openSession();
            threadLocalSession.set(session);
        }
        return session;
    }

    public static PhoneDao getPhoneDao() {
        return getSession().getMapper(PhoneDao.class);
    }

    public static UserDao getUserDao() {
        return getSession().getMapper(UserDao.class);
    }

    public static PhoneDetailDao getPhoneDetailDao() {
        return getSession().getMapper(PhoneDetailDao.class);
    }

    public static OrderDao getOrderDao() {
        return getSession().getMapper(OrderDao.class);
    }

    public static void closeSession() {
        SqlSession session = threadLocalSession.get();
        if (session != null) {
            session.close();
            threadLocalSession.remove();
        }
    }

    public static void commit() {
        SqlSession session = threadLocalSession.get();
        if (session != null) {
            session.commit();
        }
    }
}

