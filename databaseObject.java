package dft.olcs;

import com.google.common.base.Objects;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class databaseObject {
  public void resetData(final String resetDataTag) {
    try {
      final Connection conn = DriverManager.getConnection(("jdbc:mysql://192.168.2.92:3306/olcs?" + "user=tester&password=password"));
      final Statement stmt = conn.createStatement();
      boolean _equals = Objects.equal(resetDataTag, "isjst");
      if (_equals) {
        final ResultSet rs = stmt.executeQuery("SELECT id FROM t_case WHERE description=\'Test entry\'");
        boolean _next = rs.next();
        boolean _while = _next;
        while (_while) {
          {
            final String id = rs.getString("id");
            final String caseCategoryLinkId = (("DELETE FROM case_category_link WHERE vcase=\'" + id) + "\'");
            final String submissionId = (("DELETE FROM submission WHERE f_vcase_uid_vcase=\'" + id) + "\'");
            final String submissionDecisionRecommendation = "DELETE FROM submission_decision_recommendation WHERE comment=\'<P>Test entry 3</P>\'";
            this.deleteStatement(conn, resetDataTag, caseCategoryLinkId, submissionId, submissionDecisionRecommendation);
          }
          boolean _next_1 = rs.next();
          _while = _next_1;
        }
      }
      conn.close();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public int deleteStatement(final Connection conn, final String resetDataTag, final String caseCategoryLinkId, final String submissionId, final String submissionDecisionRecommendation) {
    try {
      int _xblockexpression = (int) 0;
      {
        final Statement stmt = conn.createStatement();
        int _xifexpression = (int) 0;
        boolean _equals = Objects.equal(resetDataTag, "isjst");
        if (_equals) {
          int _xblockexpression_1 = (int) 0;
          {
            stmt.executeUpdate(submissionDecisionRecommendation);
            stmt.executeUpdate(caseCategoryLinkId);
            stmt.executeUpdate(submissionId);
            _xblockexpression_1 = (stmt.executeUpdate("DELETE FROM t_case WHERE description=\'Test entry\'"));
          }
          _xifexpression = _xblockexpression_1;
        }
        _xblockexpression = (_xifexpression);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
