package dft.olcs

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement
import java.sql.ResultSet

class databaseObject {

def resetData(String resetDataTag) {
  val Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.2.92:3306/olcs?"+"user=tester&password=password")
  val Statement stmt = conn.createStatement
  if (resetDataTag == "isjst") {
    val ResultSet rs = stmt.executeQuery("SELECT id FROM t_case WHERE description='Test entry'")
      while (rs.next) { 	
        val id = rs.getString("id")
        val caseCategoryLinkId = "DELETE FROM case_category_link WHERE vcase='" + id + "'"
        val submissionId = "DELETE FROM submission WHERE f_vcase_uid_vcase='" + id + "'"
        val submissionDecisionRecommendation = "DELETE FROM submission_decision_recommendation WHERE comment='<P>Test entry 3</P>'"
        deleteStatement(conn, resetDataTag, caseCategoryLinkId, submissionId, submissionDecisionRecommendation)
      }
  }
  conn.close
}

def deleteStatement(Connection conn, String resetDataTag, String caseCategoryLinkId, String submissionId, String submissionDecisionRecommendation) {
  val Statement stmt = conn.createStatement
    if (resetDataTag == "isjst") {
    stmt.executeUpdate(submissionDecisionRecommendation)
    stmt.executeUpdate(caseCategoryLinkId)
    stmt.executeUpdate(submissionId)
    stmt.executeUpdate("DELETE FROM t_case WHERE description='Test entry'")
    }
}

}