@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "anotherEntityManagerFactory",
    transactionManagerRef = "anotherTransactionManager",
    basePackages = {"com.mysource.anothersource.repository"})
public class AnotherRepositoryConfig {
  @Autowired
  JpaVendorAdapter jpaVendorAdapter;

  @Value("${another.datasource.url}")
  private String databaseUrl;

  @Value("${another.datasource.username}")
  private String username;

  @Value("${another.datasource.password}")
  private String password;

  @Value("${another.dataource.driverClassName}")
  private String driverClassName;

  @Value("${another.datasource.hibernate.dialect}")
  private String dialect;

  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource(databaseUrl, username, password);
    dataSource.setDriverClassName(driverClassName);
    return dataSource;
  }

  @Bean(name = "anotherEntityManager")
  public EntityManager entityManager() {
    return entityManagerFactory().createEntityManager();
  }

  @Bean(name = "anotherEntityManagerFactory")
  public EntityManagerFactory entityManagerFactory() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect", dialect);

    LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
    emf.setDataSource(dataSource());
    emf.setJpaVendorAdapter(jpaVendorAdapter);
    emf.setPackagesToScan("com.mysource.anothersource.model");   // <- package for entities
    emf.setPersistenceUnitName("anotherPersistenceUnit");
    emf.setJpaProperties(properties);
    emf.afterPropertiesSet();
    return emf.getObject();
  }

  @Bean(name = "anotherTransactionManager")
  public PlatformTransactionManager transactionManager() {
    return new JpaTransactionManager(entityManagerFactory());
  }
}