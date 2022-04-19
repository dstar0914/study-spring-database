package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtils;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

/**
 * JDBC - DrivderManager 사용
 */
@Slf4j
public class MemberRepositoryV0 {

    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values(?, ?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null; // PreparedStatement 는 파라메터 바인딩 가능. Statement 는 불가능.

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getMemberId());
            preparedStatement.setInt(2, member.getMoney());
            preparedStatement.executeUpdate();

            return member;

        } catch (SQLException e) {
            log.error("db error", e);

            throw e;
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    private void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (statement != null) { // 여기서 exception 이 나는 경우 아래 connection 이 안 닫힐 수도 있기 때문에 아래와 같이 따로 작성.
            try {
                statement.close();
            } catch (SQLException e) {
                log.error("error", e);
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("error", e);
            }
        }
    }

    private Connection getConnection() {
        return DBConnectionUtils.getConnection();
    }

}
