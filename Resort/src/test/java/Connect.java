@Autowired
private Environment env;

@Bean
public DataSource getDataSource() {
   DriverManagerDataSource dataSource = new DriverManagerDataSource();
   dataSource.setDriverClassName(env.getProperty("db.driver");

   .....

   return dataSource;
 }