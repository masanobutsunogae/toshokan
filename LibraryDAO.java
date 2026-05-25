import java.sql.*;
import java.util.*;

public class LibraryDAO {

  private static final String URL = "jdbc:h2:file:./library";

  private static final String USER = "sa";

  private static final String PASSWORD = "";

  public LibraryDAO() {
    try {
      Class.forName("org.h2.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  // getState(検索するID)
  public Integer getState(int id) {

    Connection conn = null;

    try {
      conn = DriverManager.getConnection(URL, USER, PASSWORD);

      String sql = "SELECT STATUS FROM BOOK WHERE ID = ?";

      PreparedStatement pStmt = conn.prepareStatement(sql);

      pStmt.setInt(1, id);

      ResultSet rs = pStmt.executeQuery();

      if (rs.next()) {

        int status = rs.getInt("STATUS");

        if (status == 0) {
          return 0;
        } else {
          return 1;
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return null;
  }

  // setState(セットするID、状態)
  public boolean setState(int id, int state) {

    Connection conn = null;

    try {
      conn = DriverManager.getConnection(URL, USER, PASSWORD);

      String sql = "UPDATE BOOK " + "SET STATUS = ? " + "WHERE ID = ?";

      PreparedStatement pStmt = conn.prepareStatement(sql);

      pStmt.setInt(1, state);
      pStmt.setInt(2, id);

      int result = pStmt.executeUpdate();

      if (result > 0) {
        return true;
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return false;
  }

  // searchBook('name') -> return List(id, 'name', 'author', status)
  public List<Object> searchBook(String title) {
    Connection conn = null;

    try {
      conn = DriverManager.getConnection(URL, USER, PASSWORD);

      String sql = "SELECT * FROM BOOK WHERE TITLE LIKE ? OR AUTHOR LIKE ?";

      PreparedStatement pStmt = conn.prepareStatement(sql);
      pStmt.setString(1, "%" + title + "%");
      pStmt.setString(2, "%" + title + "%");

      ResultSet rs = pStmt.executeQuery();

      if (rs.next()) {
        List<Object> bookData = new ArrayList<Object>();
        bookData.add(rs.getInt("ID"));
        bookData.add(rs.getString("TITLE"));
        bookData.add(rs.getString("AUTHOR"));
        bookData.add(rs.getInt("STATUS"));

        return bookData;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}
