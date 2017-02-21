package org.kulk.configuration.spring;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.kulk.db.PersonDAO;
import org.kulk.db.entities.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * User: frisokulk
 * Date: 2/23/16
 * Time: 10:47 AM
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({ "org.kulk" })
@Import({ SecurityConfig.class })
public class PersistenceConfig {

    public PersistenceConfig() {
	super();
    }


    @Bean(name = "calendar")
    public EntityManagerFactory entityManagerFactory(final DataSource dataSource, final JpaVendorAdapter jpaVendorAdapter) {
	final LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
	entityManagerFactory.setDataSource(dataSource);
	entityManagerFactory.setPersistenceUnitName("calendar");
	entityManagerFactory.setPackagesToScan(getPackagesToScan());
	entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
	entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
	entityManagerFactory.getJpaPropertyMap().put(Environment.SHOW_SQL, Boolean.TRUE);

	entityManagerFactory.afterPropertiesSet();

	EntityManagerFactory emf = entityManagerFactory.getObject();
	return emf;

    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
	final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
	jpaVendorAdapter.setDatabase(Database.MYSQL);
	jpaVendorAdapter.setDatabasePlatform(org.hibernate.dialect.MySQL5InnoDBDialect.class.getName());
	jpaVendorAdapter.setGenerateDdl(Boolean.FALSE);
	return jpaVendorAdapter;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
	final BasicDataSource dataSource = new BasicDataSource();

	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/calendar");
	dataSource.setUsername("root");
	dataSource.setPassword("killer");

	return dataSource;
    }

  /*  @Bean
    public LocalSessionFactoryBean sessionFactory() {
	final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	sessionFactory.setDataSource(dataSource());
	sessionFactory.setPackagesToScan("org.kulk");
	//TODO
	sessionFactory.setHibernateProperties(hibernateProperties());
	return sessionFactory;
    }*/

   /* @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory, final SessionFactory sessionFactory) {
	final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	transactionManager.setSessionFactory(sessionFactory);
	return transactionManager;

    }*/

    private String[] getPackagesToScan() {
	return new String[]{ PersonDAO.class.getPackage().getName(),
	    Person.class.getPackage().getName()
	};
    }

    @Bean
    public JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory, final JpaVendorAdapter jpaVendorAdapter) {
	final JpaTransactionManager transactionManager = new JpaTransactionManager();
	transactionManager.setEntityManagerFactory(entityManagerFactory);
	transactionManager.setJpaDialect(jpaVendorAdapter.getJpaDialect());
	return transactionManager;
    }

    private Properties hibernateProperties() {
	final Properties hibernateProperties = new Properties();

	hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
	hibernateProperties.setProperty("show_sql", "true");

	return hibernateProperties;
    }

    @Bean
    public PersistenceExceptionTranslator hibernateExceptionTranslator() {
	return new HibernateExceptionTranslator();
    }


}
