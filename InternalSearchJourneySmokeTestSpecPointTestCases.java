package dft.olcs;

import java.util.List;
import org.jnario.lib.ExampleTableRow;

@SuppressWarnings("all")
public class InternalSearchJourneySmokeTestSpecPointTestCases extends ExampleTableRow {
  public InternalSearchJourneySmokeTestSpecPointTestCases(final List<String> cellNames, final String ptcBddIdentifier, final String ptcTitle, final String ptcAction, final String ptcNameElement, final String ptcByElement, final String ptcInputCheck, final String ptcInclude, final int ptcPause) {
    super(cellNames);
    this.ptcBddIdentifier = ptcBddIdentifier;
    this.ptcTitle = ptcTitle;
    this.ptcAction = ptcAction;
    this.ptcNameElement = ptcNameElement;
    this.ptcByElement = ptcByElement;
    this.ptcInputCheck = ptcInputCheck;
    this.ptcInclude = ptcInclude;
    this.ptcPause = ptcPause;
    
  }
  
  private String ptcBddIdentifier;
  
  public String getPtcBddIdentifier() {
    return this.ptcBddIdentifier;
  }
  
  private String ptcTitle;
  
  public String getPtcTitle() {
    return this.ptcTitle;
  }
  
  private String ptcAction;
  
  public String getPtcAction() {
    return this.ptcAction;
  }
  
  private String ptcNameElement;
  
  public String getPtcNameElement() {
    return this.ptcNameElement;
  }
  
  private String ptcByElement;
  
  public String getPtcByElement() {
    return this.ptcByElement;
  }
  
  private String ptcInputCheck;
  
  public String getPtcInputCheck() {
    return this.ptcInputCheck;
  }
  
  private String ptcInclude;
  
  public String getPtcInclude() {
    return this.ptcInclude;
  }
  
  private int ptcPause;
  
  public int getPtcPause() {
    return this.ptcPause;
  }
}
