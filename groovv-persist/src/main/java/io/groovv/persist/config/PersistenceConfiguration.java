package io.groovv.persist.config;

import static io.groovv.model.api.Models.getScannedPackages;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.groovv.persist.registrations.RegistrationRepository;
import io.groovv.persist.users.AccountRepository;
import javax.sql.DataSource;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackageClasses = {RegistrationRepository.class, AccountRepository.class})
public class PersistenceConfiguration {

  @Bean
  public DataSource dataSource() {
    val cfg = new HikariConfig();
    cfg.setJdbcUrl(PersistenceEnvironment.LeaderDomainName.getString());
    cfg.setUsername(PersistenceEnvironment.DatabaseUserName.getString());
    cfg.setPassword(PersistenceEnvironment.DatabasePassword.getString());
    cfg.addDataSourceProperty("cachePrepStmts", "true");
    cfg.addDataSourceProperty("prepStmtCacheSize", "250");
    cfg.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    return new HikariDataSource(cfg);
  }

  @Bean
  @DependsOn("migrationManager")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      DataSource dataSource) {
    val factorybean = new LocalContainerEntityManagerFactoryBean();
    factorybean.setDataSource(dataSource);
    factorybean.setPackagesToScan(getScannedPackages());
    val adapter = new HibernateJpaVendorAdapter();
    adapter.setGenerateDdl(true);
    adapter.setPrepareConnection(true);
    adapter.setShowSql(false);
    factorybean.setJpaVendorAdapter(adapter);
    return factorybean;
  }

  @Bean
  public PlatformTransactionManager transactionManager(
      LocalContainerEntityManagerFactoryBean factoryBean) {
    val transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(factoryBean.getObject());
    return transactionManager;
  }
}
