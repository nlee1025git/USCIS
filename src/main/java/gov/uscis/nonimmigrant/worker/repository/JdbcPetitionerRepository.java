package gov.uscis.nonimmigrant.worker.repository;

import gov.uscis.nonimmigrant.worker.domain.Petitioner;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcPetitionerRepository implements PetitionerRepository {
    private final DataSource dataSource;

    public JdbcPetitionerRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Petitioner save(Petitioner petitioner) {
        String sql = "insert into petitioner(lastName, firstName, middleName, companyName, address, number, city, state, zipcode, phoneNumber, email, fein) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, petitioner.getLast());
            pstmt.setString(2, petitioner.getFirst());
            pstmt.setString(3, petitioner.getMiddle());
            pstmt.setString(4, petitioner.getCompanyName());
            pstmt.setString(5, petitioner.getAddress());
            pstmt.setString(6, petitioner.getNumber());
            pstmt.setString(7, petitioner.getCity());
            pstmt.setString(8, petitioner.getState());
            pstmt.setString(9, petitioner.getZipcode());
            pstmt.setString(10, petitioner.getPhoneNumber());
            pstmt.setString(11, petitioner.getEmail());
            pstmt.setString(12, petitioner.getFein());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                petitioner.setId(rs.getInt(1));
            } else {
                throw new SQLException("ID not found");
            }
            return petitioner;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Petitioner> findById(int id) {
        String sql = "select * from petitioner where petitioner_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Petitioner petitioner = new Petitioner();
                petitioner.setId(rs.getInt("petitioner_id"));
                petitioner.setLast(rs.getString("lastName"));
                petitioner.setFirst(rs.getString("firstName"));
                petitioner.setMiddle(rs.getString("middleName"));
                petitioner.setCompanyName(rs.getString("companyName"));
                petitioner.setAddress(rs.getString("address"));
                petitioner.setNumber(rs.getString("number"));
                petitioner.setCity(rs.getString("city"));
                petitioner.setState(rs.getString("state"));
                petitioner.setZipcode(rs.getString("zipcode"));
                petitioner.setPhoneNumber(rs.getString("phoneNumber"));
                petitioner.setEmail(rs.getString("email"));
                petitioner.setFein(rs.getString("fein"));
                return Optional.of(petitioner);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Petitioner> findByName(String lastName, String firstName, String middleName) {
        return Optional.empty();
    }

    @Override
    public List<Petitioner> findAll() {
        String sql = "select * from petitioner";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            List<Petitioner> petitioners = new ArrayList<>();
            while (rs.next()) {
                Petitioner petitioner = new Petitioner();
                petitioner.setId(rs.getInt("petitioner_id"));
                petitioner.setLast(rs.getString("lastName"));
                petitioner.setFirst(rs.getString("firstName"));
                petitioner.setMiddle(rs.getString("middleName"));
                petitioner.setCompanyName(rs.getString("companyName"));
                petitioner.setAddress(rs.getString("address"));
                petitioner.setNumber(rs.getString("number"));
                petitioner.setCity(rs.getString("city"));
                petitioner.setState(rs.getString("state"));
                petitioner.setZipcode(rs.getString("zipcode"));
                petitioner.setPhoneNumber(rs.getString("phoneNumber"));
                petitioner.setEmail(rs.getString("email"));
                petitioner.setFein(rs.getString("fein"));
                petitioners.add(petitioner);
            }

            return petitioners;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public Petitioner update(Petitioner petitioner) {
        String sql = "update petitioner set lastName = ?, firstName = ?, middleName = ?, companyName = ?," +
                "address = ?, number = ?, city = ?, state = ?, zipcode = ?, phoneNumber = ?, email = ?, fein = ? where petitioner_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, petitioner.getLast());
            pstmt.setString(2, petitioner.getFirst());
            pstmt.setString(3, petitioner.getMiddle());
            pstmt.setString(4, petitioner.getCompanyName());
            pstmt.setString(5, petitioner.getAddress());
            pstmt.setString(6, petitioner.getNumber());
            pstmt.setString(7, petitioner.getCity());
            pstmt.setString(8, petitioner.getState());
            pstmt.setString(9, petitioner.getZipcode());
            pstmt.setString(10, petitioner.getPhoneNumber());
            pstmt.setString(11, petitioner.getEmail());
            pstmt.setString(12, petitioner.getFein());
            pstmt.setInt(13, petitioner.getId());
            pstmt.executeUpdate();

            return petitioner;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Petitioner find(Petitioner petitioner) {
        List<Petitioner> petitionerList = findAll();
        for (int i = 0; i < petitionerList.size(); i++) {
            if (petitionerList.get(i).getLast().equalsIgnoreCase(petitioner.getLast()) &&
                    petitionerList.get(i).getFirst().equalsIgnoreCase(petitioner.getFirst()) &&
                    petitionerList.get(i).getMiddle().equalsIgnoreCase(petitioner.getMiddle()) &&
                    petitionerList.get(i).getCompanyName().equalsIgnoreCase(petitioner.getCompanyName())) {
                return petitionerList.get(i);
            }
        }
        return null;
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
