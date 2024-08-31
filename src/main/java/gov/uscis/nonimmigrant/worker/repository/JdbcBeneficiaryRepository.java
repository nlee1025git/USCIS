package gov.uscis.nonimmigrant.worker.repository;

import gov.uscis.nonimmigrant.worker.domain.Beneficiary;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcBeneficiaryRepository implements BeneficiaryRepository {
    private final DataSource dataSource;

    public JdbcBeneficiaryRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Beneficiary save(Beneficiary beneficiary) {
        String sql = "insert into beneficiary(lastName, firstName, middleName, dob, gender, country, passportNumber, education) values(?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, beneficiary.getLastName());
            pstmt.setString(2, beneficiary.getFirstName());
            pstmt.setString(3, beneficiary.getMiddleName());
            pstmt.setDate(4, beneficiary.getDOB());
            pstmt.setString(5, beneficiary.getGender());
            pstmt.setString(6, beneficiary.getCountry());
            pstmt.setString(7, beneficiary.getPassportNumber());
            pstmt.setString(8, beneficiary.getEducation());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                beneficiary.setId(rs.getInt(1));
            } else {
                throw new SQLException("ID not found");
            }
            return beneficiary;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Beneficiary> findById(int id) {
        String sql = "select * from beneficiary where beneficiary_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Beneficiary beneficiary = setBeneficiary(rs);
                return Optional.of(beneficiary);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private static Beneficiary setBeneficiary(ResultSet rs) throws SQLException {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setId(rs.getInt("beneficiary_id"));
        beneficiary.setLastName(rs.getString("lastName"));
        beneficiary.setFirstName(rs.getString("firstName"));
        beneficiary.setMiddleName(rs.getString("middleName"));
        beneficiary.setDOB(rs.getDate("DOB"));
        beneficiary.setGender(rs.getString("gender"));
        beneficiary.setCountry(rs.getString("country"));
        beneficiary.setPassportNumber(rs.getString("passportNumber"));
        beneficiary.setEducation(rs.getString("education"));
        return beneficiary;
    }

    @Override
    public List<Beneficiary> findAll() {
        String sql = "select * from beneficiary";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            List<Beneficiary> beneficiaries = new ArrayList<>();
            while (rs.next()) {
                Beneficiary beneficiary = setBeneficiary(rs);
                beneficiaries.add(beneficiary);
            }

            return beneficiaries;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void remove(int id) {
        String sql = "delete from beneficiary where beneficiary_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Beneficiary update(Beneficiary beneficiary) {
        String sql = "update beneficiary set lastName = ?, firstName = ?, middleName = ?," +
                "dob = ?, gender = ?, country = ?, passportNumber = ?, education = ? where beneficiary_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, beneficiary.getLastName());
            pstmt.setString(2, beneficiary.getFirstName());
            pstmt.setString(3, beneficiary.getMiddleName());
            pstmt.setDate(4, beneficiary.getDOB());
            pstmt.setString(5, beneficiary.getGender());
            pstmt.setString(6, beneficiary.getCountry());
            pstmt.setString(7, beneficiary.getPassportNumber());
            pstmt.setString(8, beneficiary.getEducation());
            pstmt.setInt(9, beneficiary.getId());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                beneficiary.setId(rs.getInt(1));
            } else {
                throw new SQLException("id not found");
            }
            return beneficiary;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Beneficiary> findByName(String lastName, String firstName, String middleName) {
        String sql = "select * from beneficiary where lastName = ? and firstName = ? and middleName = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, lastName);
            pstmt.setString(2, firstName);
            pstmt.setString(3, middleName);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Beneficiary beneficiary = setBeneficiary(rs);
                return Optional.of(beneficiary);
            }

            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Integer> findBeneficiaryIDs() {
        List<Integer> ids = new ArrayList<>();
        String sql = "select id from beneficiary";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("id"));
            }

            return ids;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Integer> findIdsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Integer> ids = new ArrayList<>();
        String sql = "select id, status from beneficiary where applicationDate between ? and ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            Date start = Date.valueOf(startDate);
            Date end = Date.valueOf(endDate);

            pstmt.setDate(1, start);
            pstmt.setDate(2, end);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                if  (rs.getString("status").equals("2")) {
                    ids.add(rs.getInt("id"));
                }
            }
            return ids;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
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