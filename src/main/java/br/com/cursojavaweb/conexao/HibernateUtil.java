package br.com.cursojavaweb.conexao;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

@SuppressWarnings("deprecation")
public class HibernateUtil {
	private static final SessionFactory conexaoBaseLocal;
    

    public static Connection getConexao(){
		Session sessao = conexaoBaseLocal.openSession();
		
		Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() {
			@Override
			public Connection execute(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				return conn;
			}
		});
		return conexao;
	}
    
    static {
        try {
            Configuration configuration1 = new Configuration();
            configuration1.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry1 = new ServiceRegistryBuilder().applySettings(configuration1.getProperties()).buildServiceRegistry();
            conexaoBaseLocal = configuration1.buildSessionFactory(serviceRegistry1);

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError("msg de erro " + ex.getMessage());
        }
    }

    public static SessionFactory getConexaoBaseLocal() {
        return conexaoBaseLocal;
    }

}