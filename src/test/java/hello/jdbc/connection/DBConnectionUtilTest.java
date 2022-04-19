package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class DBConnectionUtilTest {

    @Test
    public void connection() {
        Connection connection = DBConnectionUtils.getConnection(); // h2 가 구현한 org.h2.jdbc.JdbcConnection 를 반환.

        assertThat(connection).isNotNull();
    }

}
