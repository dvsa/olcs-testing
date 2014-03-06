package dft.olcs;

import com.google.common.base.Objects;
import dft.olcs.InternalSearchJourneySmokeTestSpecPointTestCases;
import dft.olcs.seleniumObject;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;
import org.jnario.lib.Each;
import org.jnario.lib.ExampleTable;
import org.jnario.lib.JnarioCollectionLiterals;
import org.jnario.runner.ExampleGroupRunner;
import org.jnario.runner.Named;
import org.jnario.runner.Order;
import org.junit.Test;
import org.junit.runner.RunWith;

@Named("Internal \\\'Search\\\' Journey Smoke Test")
@RunWith(ExampleGroupRunner.class)
@SuppressWarnings("all")
public class InternalSearchJourneySmokeTestSpec {
  final String journeyStart = "BDD0000-T02";
  
  final List<String> journeyList = JnarioCollectionLiterals.<String>list(
    "BDD0000-T02", "BDD0000-R02", "BDD0000-A01", "BDD0000-B01", "BDD0000-C01", "BDD0000-D01", "BDD0000-E01", "BDD0000-G01", "BDD0000-F01", "BDD0000-H01", "BDD0000-I01", "BDD0000-J01", "BDD0000-P01", "BDD0000-M01", "BDD0000-O01", "BDD0000-L01", "BDD0000-N01", "BDD0000-E01", "BDD0000-K01", "BDD0000-O01", "BDD0000-Q01", "BDD0000-R01", "BDD0000-S01", "BDD0000-T01", "BDD0000-U01", "BDD0000-V01", "BDD0000-G01", "BDD0000-W01", "BDD0000-X01", "BDD0000-Y01", "BDD0000-Z01", "BDD0000-A02", "BDD0000-B02", "BDD0000-E01", "BDD0000-C02", "BDD0000-D02", "BDD0000-E02", "BDD0000-F02", "BDD0000-G02", "BDD0000-H02", "BDD0000-I02", "BDD0000-J02", "BDD0000-K02", "BDD0000-L02", "BDD0000-M02", "BDD0000-N02", "BDD0000-O02", "BDD0000-P02", "BDD0000-S01", "BDD0000-T01", "BDD0000-Q02", "BDD0000-V01", "BDD0000-E01", "BDD0000-S02");
  
  final List<Integer> browserList = JnarioCollectionLiterals.<Integer>list(Integer.valueOf(1));
  
  final List<Integer> hubList = JnarioCollectionLiterals.<Integer>list(Integer.valueOf(1));
  
  final String resetDataEnabled = "Yes";
  
  final String resetDataTag = "isjst";
  
  final String videoPluginEnabled = "No";
  
  final int videoPause = 5000;
  
  final String securityPluginEnabled = "No";
  
  final String accessibilityPluginEnabled = "Yes";
  
  final int accessibilityPause = 2000;
  
  final String accessibilityCaptureImage = "Yes";
  
  final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_M_yyyy_hh_mm_ss");
  
  final String timeStamp = this.simpleDateFormat.format(new Date());
  
  final String testRunIdentifier = ("OLCS_v001_Internal_Search_Journey_Smoke_Test_" + this.timeStamp);
  
  final String reportDirectory = "C://olcs_backup//reports//";
  
  public ExampleTable<InternalSearchJourneySmokeTestSpecPointTestCases> _initInternalSearchJourneySmokeTestSpecPointTestCases() {
    return ExampleTable.create("pointTestCases", 
      Arrays.asList("ptcBddIdentifier", "ptcTitle", "ptcAction", "ptcNameElement", "ptcByElement", "ptcInputCheck", "ptcInclude", "ptcPause"), 
      new InternalSearchJourneySmokeTestSpecPointTestCases(  Arrays.asList("\"BDD0000-Z01\"", "\"OLCS\"", "\"wait\"", "\"ompliance\"", "\"partialLinkText\"", "\"NA\"", "\"Yes\"", "2000"), _initInternalSearchJourneySmokeTestSpecPointTestCasesCell0(), _initInternalSearchJourneySmokeTestSpecPointTestCasesCell1(), _initInternalSearchJourneySmokeTestSpecPointTestCasesCell2(), _initInternalSearchJourneySmokeTestSpecPointTestCasesCell3(), _initInternalSearchJourneySmokeTestSpecPointTestCasesCell4(), _initInternalSearchJourneySmokeTestSpecPointTestCasesCell5(), _initInternalSearchJourneySmokeTestSpecPointTestCasesCell6(), _initInternalSearchJourneySmokeTestSpecPointTestCasesCell7())
    );
  }
  
  protected ExampleTable<InternalSearchJourneySmokeTestSpecPointTestCases> pointTestCases = _initInternalSearchJourneySmokeTestSpecPointTestCases();
  
  public String _initInternalSearchJourneySmokeTestSpecPointTestCasesCell0() {
    return "BDD0000-Z01";
  }
  
  public String _initInternalSearchJourneySmokeTestSpecPointTestCasesCell1() {
    return "OLCS";
  }
  
  public String _initInternalSearchJourneySmokeTestSpecPointTestCasesCell2() {
    return "wait";
  }
  
  public String _initInternalSearchJourneySmokeTestSpecPointTestCasesCell3() {
    return "ompliance";
  }
  
  public String _initInternalSearchJourneySmokeTestSpecPointTestCasesCell4() {
    return "partialLinkText";
  }
  
  public String _initInternalSearchJourneySmokeTestSpecPointTestCasesCell5() {
    return "NA";
  }
  
  public String _initInternalSearchJourneySmokeTestSpecPointTestCasesCell6() {
    return "Yes";
  }
  
  public int _initInternalSearchJourneySmokeTestSpecPointTestCasesCell7() {
    return 2000;
  }
  
  @Test
  @Named("As a Business Analyst / QA Lead I want to be able to audit that the current software build meets Functional [BDD], Accessibility, and Security requirements on an internal \\\'Search\\\' journey So that I can ensure that there is a working baseline on any specific build")
  @Order(1)
  public void _asABusinessAnalystQALeadIWantToBeAbleToAuditThatTheCurrentSoftwareBuildMeetsFunctionalBDDAccessibilityAndSecurityRequirementsOnAnInternalSearchJourneySoThatICanEnsureThatThereIsAWorkingBaselineOnAnySpecificBuild() throws Exception {
    final Procedure2<Integer,Integer> _function = new Procedure2<Integer,Integer>() {
      public void apply(final Integer capability, final Integer capabilityIndex) {
        final Procedure2<Integer,Integer> _function = new Procedure2<Integer,Integer>() {
          public void apply(final Integer hub, final Integer hubIndex) {
            final Procedure1<InternalSearchJourneySmokeTestSpecPointTestCases> _function = new Procedure1<InternalSearchJourneySmokeTestSpecPointTestCases>() {
              public void apply(final InternalSearchJourneySmokeTestSpecPointTestCases it) {
                String _ptcInclude = it.getPtcInclude();
                boolean _equals = Objects.equal(_ptcInclude, "Yes");
                if (_equals) {
                  Integer _get = InternalSearchJourneySmokeTestSpec.this.hubList.get((hubIndex).intValue());
                  Integer _get_1 = InternalSearchJourneySmokeTestSpec.this.browserList.get((capabilityIndex).intValue());
                  String _ptcBddIdentifier = it.getPtcBddIdentifier();
                  String _ptcTitle = it.getPtcTitle();
                  String _ptcAction = it.getPtcAction();
                  String _ptcNameElement = it.getPtcNameElement();
                  String _ptcByElement = it.getPtcByElement();
                  String _ptcInputCheck = it.getPtcInputCheck();
                  String _ptcInclude_1 = it.getPtcInclude();
                  int _ptcPause = it.getPtcPause();
                  InternalSearchJourneySmokeTestSpec.this._seleniumObject.executeTestRun((_get).intValue(), (_get_1).intValue(), InternalSearchJourneySmokeTestSpec.this.resetDataEnabled, InternalSearchJourneySmokeTestSpec.this.resetDataTag, InternalSearchJourneySmokeTestSpec.this.journeyStart, InternalSearchJourneySmokeTestSpec.this.videoPluginEnabled, InternalSearchJourneySmokeTestSpec.this.reportDirectory, InternalSearchJourneySmokeTestSpec.this.testRunIdentifier, InternalSearchJourneySmokeTestSpec.this.videoPause, InternalSearchJourneySmokeTestSpec.this.accessibilityPluginEnabled, InternalSearchJourneySmokeTestSpec.this.accessibilityCaptureImage, InternalSearchJourneySmokeTestSpec.this.accessibilityPause, InternalSearchJourneySmokeTestSpec.this.journeyList, _ptcBddIdentifier, _ptcTitle, _ptcAction, _ptcNameElement, _ptcByElement, _ptcInputCheck, _ptcInclude_1, _ptcPause);
                }
              }
            };
            Each.<InternalSearchJourneySmokeTestSpecPointTestCases>forEach(InternalSearchJourneySmokeTestSpec.this.pointTestCases, _function);
          }
        };
        IterableExtensions.<Integer>forEach(InternalSearchJourneySmokeTestSpec.this.hubList, _function);
      }
    };
    IterableExtensions.<Integer>forEach(this.browserList, _function);
  }
  
  @Extension
  @org.jnario.runner.Extension
  public seleniumObject _seleniumObject = new seleniumObject();
}
