package cn.itcast.bookstore.user.dao;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;

public class UserDao {

    private QueryRunner qr = new TxQueryRunner();
}
