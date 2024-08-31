package gov.uscis.nonimmigrant.worker.repository;

import gov.uscis.nonimmigrant.worker.domain.VisaApplication;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcVisaApplicationRepository implements VisaApplicationRepository {
    private final DataSource dataSource;

    public JdbcVisaApplicationRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public VisaApplication add(VisaApplication visaApplication, int bid, int pid) {
        String sql = "insert into visa_application(petitioner_id, beneficiary_id, status, application_date, approval_date, job_title, wage, from_date, to_date, visa_type) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, pid);
            pstmt.setInt(2, bid);
            pstmt.setString(3, visaApplication.getStatus());
            pstmt.setDate(4, visaApplication.getApplicationDate());
            pstmt.setDate(5, visaApplication.getApprovalDate());
            pstmt.setString(6, visaApplication.getJobTitle());
            pstmt.setString(7, visaApplication.getWage());
            pstmt.setDate(8, visaApplication.getFromDate());
            pstmt.setDate(9, visaApplication.getToDate());
            pstmt.setString(10, visaApplication.getVisaType());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                visaApplication.setApplicationId(rs.getInt(1));
            } else {
                throw new SQLException("ID not found");
            }
            return visaApplication;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<VisaApplication> findAll() {
        String sql = "select * from visa_application";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            List<VisaApplication> visaApplicationList = new ArrayList<>();
            while (rs.next()) {
                VisaApplication visaApplication = setVisaApplication(rs);
                visaApplicationList.add(visaApplication);
            }
            return visaApplicationList;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void updateStatus(int id, String status) {
        String sql = "update visa_application set status = ? where application_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, status);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private static VisaApplication setVisaApplication(ResultSet rs) throws SQLException {
        VisaApplication visaApplication = new VisaApplication();
        visaApplication.setApplicationId(rs.getInt("application_id"));
        visaApplication.setPetitionerId(rs.getInt("petitioner_id"));
        visaApplication.setBeneficiaryId(rs.getInt("beneficiary_id"));
        visaApplication.setStatus(rs.getString("status"));
        visaApplication.setApplicationDate(rs.getDate("application_date"));
        visaApplication.setApprovalDate(rs.getDate("approval_date"));
        visaApplication.setJobTitle(rs.getString("job_title"));
        visaApplication.setWage(rs.getString("wage"));
        visaApplication.setFromDate(rs.getDate("from_date"));
        visaApplication.setToDate(rs.getDate("to_date"));
        visaApplication.setVisaType(rs.getString("visa_type"));
        return visaApplication;
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
}
