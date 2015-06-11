package olcs;

import org.eclipse.xtend.lib.Data;
import org.eclipse.xtext.xbase.lib.util.ToStringHelper;

@Data
@SuppressWarnings("all")
public class suites {
  private final String _testCases;
  
  public String getTestCases() {
    return this._testCases;
  }
  
  private final String _mapSurface;
  
  public String getMapSurface() {
    return this._mapSurface;
  }
  
  private final String _recordVideo;
  
  public String getRecordVideo() {
    return this._recordVideo;
  }
  
  private final int _videoPause;
  
  public int getVideoPause() {
    return this._videoPause;
  }
  
  private final String _runSecurity;
  
  public String getRunSecurity() {
    return this._runSecurity;
  }
  
  private final String _runAccessibility;
  
  public String getRunAccessibility() {
    return this._runAccessibility;
  }
  
  private final int _accessibilityPause;
  
  public int getAccessibilityPause() {
    return this._accessibilityPause;
  }
  
  private final String _groupRunIdentifier;
  
  public String getGroupRunIdentifier() {
    return this._groupRunIdentifier;
  }
  
  private final String _reportDirectory;
  
  public String getReportDirectory() {
    return this._reportDirectory;
  }
  
  private final String _includeGroup;
  
  public String getIncludeGroup() {
    return this._includeGroup;
  }
  
  public suites(final String testCases, final String mapSurface, final String recordVideo, final int videoPause, final String runSecurity, final String runAccessibility, final int accessibilityPause, final String groupRunIdentifier, final String reportDirectory, final String includeGroup) {
    super();
    this._testCases = testCases;
    this._mapSurface = mapSurface;
    this._recordVideo = recordVideo;
    this._videoPause = videoPause;
    this._runSecurity = runSecurity;
    this._runAccessibility = runAccessibility;
    this._accessibilityPause = accessibilityPause;
    this._groupRunIdentifier = groupRunIdentifier;
    this._reportDirectory = reportDirectory;
    this._includeGroup = includeGroup;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((_testCases== null) ? 0 : _testCases.hashCode());
    result = prime * result + ((_mapSurface== null) ? 0 : _mapSurface.hashCode());
    result = prime * result + ((_recordVideo== null) ? 0 : _recordVideo.hashCode());
    result = prime * result + _videoPause;
    result = prime * result + ((_runSecurity== null) ? 0 : _runSecurity.hashCode());
    result = prime * result + ((_runAccessibility== null) ? 0 : _runAccessibility.hashCode());
    result = prime * result + _accessibilityPause;
    result = prime * result + ((_groupRunIdentifier== null) ? 0 : _groupRunIdentifier.hashCode());
    result = prime * result + ((_reportDirectory== null) ? 0 : _reportDirectory.hashCode());
    result = prime * result + ((_includeGroup== null) ? 0 : _includeGroup.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    suites other = (suites) obj;
    if (_testCases == null) {
      if (other._testCases != null)
        return false;
    } else if (!_testCases.equals(other._testCases))
      return false;
    if (_mapSurface == null) {
      if (other._mapSurface != null)
        return false;
    } else if (!_mapSurface.equals(other._mapSurface))
      return false;
    if (_recordVideo == null) {
      if (other._recordVideo != null)
        return false;
    } else if (!_recordVideo.equals(other._recordVideo))
      return false;
    if (other._videoPause != _videoPause)
      return false;
    if (_runSecurity == null) {
      if (other._runSecurity != null)
        return false;
    } else if (!_runSecurity.equals(other._runSecurity))
      return false;
    if (_runAccessibility == null) {
      if (other._runAccessibility != null)
        return false;
    } else if (!_runAccessibility.equals(other._runAccessibility))
      return false;
    if (other._accessibilityPause != _accessibilityPause)
      return false;
    if (_groupRunIdentifier == null) {
      if (other._groupRunIdentifier != null)
        return false;
    } else if (!_groupRunIdentifier.equals(other._groupRunIdentifier))
      return false;
    if (_reportDirectory == null) {
      if (other._reportDirectory != null)
        return false;
    } else if (!_reportDirectory.equals(other._reportDirectory))
      return false;
    if (_includeGroup == null) {
      if (other._includeGroup != null)
        return false;
    } else if (!_includeGroup.equals(other._includeGroup))
      return false;
    return true;
  }
  
  @Override
  public String toString() {
    String result = new ToStringHelper().toString(this);
    return result;
  }
}
