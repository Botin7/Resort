public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        registerColumnType(Types.BIT, "integer");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.INTEGER, "integer");
        // other data types
    }
}
public class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {

    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }

    @Override
    public String getIdentitySelectString(String table, String column, int type) 
      throws MappingException {
        return "select last_insert_rowid()";
    }

    @Override
    public String getIdentityColumnString(int type) throws MappingException {
        return "integer";
    }
}
@Override
public boolean hasAlterTable() {
    return false;
}

@Override
public boolean dropConstraints() {
    return false;
}

@Override
public String getDropForeignKeyString() {
    return "";
}

@Override
public String getAddForeignKeyConstraintString(String cn, 
  String[] fk, String t, String[] pk, boolean rpk) {
    return "";
}

@Override
public String getAddPrimaryKeyConstraintString(String constraintName) {
    return "";
}
@Autowired Environment env;

@Bean
public DataSource dataSource() {
    final DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("driverClassName"));
    dataSource.setUrl(env.getProperty("url"));
    dataSource.setUsername(env.getProperty("user"));
    dataSource.setPassword(env.getProperty("password"));
    return dataSource;
}