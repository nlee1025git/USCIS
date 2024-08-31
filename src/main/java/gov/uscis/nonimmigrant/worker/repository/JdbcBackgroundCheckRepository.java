package gov.uscis.nonimmigrant.worker.repository;

import gov.uscis.nonimmigrant.worker.domain.BackgroundCheck;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcBackgroundCheckRepository implements BackgroundCheckRepository {
    private final DataSource dataSource;

    public JdbcBackgroundCheckRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

    @Override
    public BackgroundCheck save(BackgroundCheck backgroundCheck) {
        String sql = "insert into backgroundcheck(application_id, job_title_verification, wage_compliance, beneficiary_qualification, criminal_record, result, check_date) values(?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, backgroundCheck.getApplicationId());
            pstmt.setInt(2, backgroundCheck.getJobTitleVerification());
            pstmt.setInt(3, backgroundCheck.getWageCompliance());
            pstmt.setInt(4, backgroundCheck.getBeneficiaryQualification());
            pstmt.setInt(5, backgroundCheck.getCriminalRecord());
            pstmt.setString(6, backgroundCheck.getResult());
            pstmt.setDate(7, backgroundCheck.getCheckDate());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                backgroundCheck.setBackgroundId(rs.getInt(1));
            } else {
                throw new SQLException("ID not found");
            }
            return backgroundCheck;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public BackgroundCheck add(int id) {
        int range = 10;
        BackgroundCheck backgroundCheck = new BackgroundCheck();
        String sql = "insert into backgroundcheck (application_id, job_title_verification, wage_compliance, beneficiary_qualification, criminal_record, result, check_date) values (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, id);
            pstmt.setInt(2, (int) (Math.random() * range) + 1);
            pstmt.setInt(3, (int) (Math.random() * range) + 1);
            pstmt.setInt(4, (int) (Math.random() * range) + 1);
            pstmt.setInt(5, (int) (Math.random() * range) + 1);
            pstmt.setString(6, "result");
            pstmt.setDate(7, Date.valueOf("2024-07-10"));
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                backgroundCheck.setBackgroundId(rs.getInt(1));
            } else {
                throw new SQLException("id not found");
            }
            return backgroundCheck;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<BackgroundCheck> findAll() {
        String sql = "select * from backgroundcheck";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            List<BackgroundCheck> backgroundCheckList = new ArrayList<>();
            while (rs.next()) {
                BackgroundCheck backgroundCheck = setBackground(rs);
                backgroundCheckList.add(backgroundCheck);
            }

            return backgroundCheckList;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private static BackgroundCheck setBackground(ResultSet rs) throws SQLException {
        BackgroundCheck backgroundCheck = new BackgroundCheck();
        backgroundCheck.setBackgroundId(rs.getInt("background_id"));
        backgroundCheck.setApplicationId(rs.getInt("application_id"));
        backgroundCheck.setJobTitleVerification(rs.getInt("job_title_verification"));
        backgroundCheck.setWageCompliance(rs.getInt("wage_compliance"));
        backgroundCheck.setBeneficiaryQualification(rs.getInt("beneficiary_qualification"));
        backgroundCheck.setCriminalRecord(rs.getInt("criminal_record"));
        backgroundCheck.setResult(rs.getString("result"));
        backgroundCheck.setCheckDate(rs.getDate("check_date"));
        return backgroundCheck;
    }

    @Override
    public int getCount() {
        int column = 0;
        String sql = "select * from backgroundcheck";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                column = rs.getMetaData().getColumnCount() - 1;
            }

            return column;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Integer> findAllJobs() {
        String sql = "select job_title_verification from backgroundcheck";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            List<Integer> backgroundCheckList = new ArrayList<>();
            while (rs.next()) {
                backgroundCheckList.add(rs.getInt("job_title_verification"));
            }

            return backgroundCheckList;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
}