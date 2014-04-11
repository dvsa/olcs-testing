package olcs;

import com.google.common.base.Objects;
import java.text.SimpleDateFormat;
import java.util.Date;
import olcs.seleniumObject;
import olcs.suiteObject;
import olcs.suites;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.jnario.runner.ExampleGroupRunner;
import org.jnario.runner.Named;
import org.jnario.runner.Order;
import org.junit.Test;
import org.junit.runner.RunWith;

@Named("OLCS_Selenium_Test_Suite")
@RunWith(ExampleGroupRunner.class)
@SuppressWarnings("all")
public class OLCSSeleniumTestSuiteSpec {
  final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_M_yyyy_hh_mm_ss");
  
  final String timeStamp = this.simpleDateFormat.format(new Date());
  
  @Test
  @Named("As a quality assurance lead I want to be able to audit that the current software build meets Functional [BDD], Accessibility, and Security requirements by grouping test cases together So that I can ensure that there is a working baseline on any specific build using any combination of browsers and test cases")
  @Order(1)
  public void _asAQualityAssuranceLeadIWantToBeAbleToAuditThatTheCurrentSoftwareBuildMeetsFunctionalBDDAccessibilityAndSecurityRequirementsByGroupingTestCasesTogetherSoThatICanEnsureThatThereIsAWorkingBaselineOnAnySpecificBuildUsingAnyCombinationOfBrowsersAndTestC() throws Exception {
    final Procedure1<suites> _function = new Procedure1<suites>() {
      public void apply(final suites it) {
        String _includeGroup = it.getIncludeGroup();
        boolean _equals = Objects.equal(_includeGroup, "Y");
        if (_equals) {
          String _testCases = it.getTestCases();
          String _browserList = it.getBrowserList();
          String _hubList = it.getHubList();
          String _mapSurface = it.getMapSurface();
          String _recordVideo = it.getRecordVideo();
          int _videoPause = it.getVideoPause();
          String _runSecurity = it.getRunSecurity();
          String _runAccessibility = it.getRunAccessibility();
          int _accessibilityPause = it.getAccessibilityPause();
          String _groupRunIdentifier = it.getGroupRunIdentifier();
          String _plus = (_groupRunIdentifier + OLCSSeleniumTestSuiteSpec.this.timeStamp);
          String _reportDirectory = it.getReportDirectory();
          OLCSSeleniumTestSuiteSpec.this._seleniumObject.executeTestSuite(_testCases, _browserList, _hubList, _mapSurface, _recordVideo, _videoPause, _runSecurity, _runAccessibility, _accessibilityPause, _plus, _reportDirectory);
        }
      }
    };
    IterableExtensions.<suites>forEach(this._suiteObject.suiteObjects, _function);
  }
  
  @Extension
  @org.jnario.runner.Extension
  public seleniumObject _seleniumObject = new seleniumObject();
  
  @Extension
  @org.jnario.runner.Extension
  public suiteObject _suiteObject = new suiteObject();
}
