public static DataSource getNewDataSource() {
    if (newDataSource == null) synchronized (TaleUtils.class) {
      if (newDataSource == null) {
        Properties properties = TaleUtils.getPropFromFile("application-default.properties");
        if (properties.size() == 0) {
          return newDataSource;
        }
        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        managerDataSource.setPassword(properties.getProperty("spring.datasource.password"));
        String str = "jdbc:mysql://" + properties.getProperty("spring.datasource.url") + "/" + properties.getProperty("spring.datasource.dbname") + "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        managerDataSource.setUrl(str);
        managerDataSource.setUsername(properties.getProperty("spring.datasource.username"));
        newDataSource = managerDataSource;
      }
    }
    return newDataSource;
  }
  