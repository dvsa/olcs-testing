package dft.olcs;

import org.eclipse.xtend.lib.Data;
import org.eclipse.xtext.xbase.lib.util.ToStringHelper;

@Data
@SuppressWarnings("all")
public class journey {
  private final String _jtcBddIdentifier;
  
  public String getJtcBddIdentifier() {
    return this._jtcBddIdentifier;
  }
  
  private final String _jtcTitle;
  
  public String getJtcTitle() {
    return this._jtcTitle;
  }
  
  private final String _jtcAction;
  
  public String getJtcAction() {
    return this._jtcAction;
  }
  
  private final String _jtcNameElement;
  
  public String getJtcNameElement() {
    return this._jtcNameElement;
  }
  
  private final String _jtcByElement;
  
  public String getJtcByElement() {
    return this._jtcByElement;
  }
  
  private final String _jtcInputCheck;
  
  public String getJtcInputCheck() {
    return this._jtcInputCheck;
  }
  
  private final String _jtcInclude;
  
  public String getJtcInclude() {
    return this._jtcInclude;
  }
  
  private final int _jtcPause;
  
  public int getJtcPause() {
    return this._jtcPause;
  }
  
  public journey(final String jtcBddIdentifier, final String jtcTitle, final String jtcAction, final String jtcNameElement, final String jtcByElement, final String jtcInputCheck, final String jtcInclude, final int jtcPause) {
    super();
    this._jtcBddIdentifier = jtcBddIdentifier;
    this._jtcTitle = jtcTitle;
    this._jtcAction = jtcAction;
    this._jtcNameElement = jtcNameElement;
    this._jtcByElement = jtcByElement;
    this._jtcInputCheck = jtcInputCheck;
    this._jtcInclude = jtcInclude;
    this._jtcPause = jtcPause;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((_jtcBddIdentifier== null) ? 0 : _jtcBddIdentifier.hashCode());
    result = prime * result + ((_jtcTitle== null) ? 0 : _jtcTitle.hashCode());
    result = prime * result + ((_jtcAction== null) ? 0 : _jtcAction.hashCode());
    result = prime * result + ((_jtcNameElement== null) ? 0 : _jtcNameElement.hashCode());
    result = prime * result + ((_jtcByElement== null) ? 0 : _jtcByElement.hashCode());
    result = prime * result + ((_jtcInputCheck== null) ? 0 : _jtcInputCheck.hashCode());
    result = prime * result + ((_jtcInclude== null) ? 0 : _jtcInclude.hashCode());
    result = prime * result + _jtcPause;
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
    journey other = (journey) obj;
    if (_jtcBddIdentifier == null) {
      if (other._jtcBddIdentifier != null)
        return false;
    } else if (!_jtcBddIdentifier.equals(other._jtcBddIdentifier))
      return false;
    if (_jtcTitle == null) {
      if (other._jtcTitle != null)
        return false;
    } else if (!_jtcTitle.equals(other._jtcTitle))
      return false;
    if (_jtcAction == null) {
      if (other._jtcAction != null)
        return false;
    } else if (!_jtcAction.equals(other._jtcAction))
      return false;
    if (_jtcNameElement == null) {
      if (other._jtcNameElement != null)
        return false;
    } else if (!_jtcNameElement.equals(other._jtcNameElement))
      return false;
    if (_jtcByElement == null) {
      if (other._jtcByElement != null)
        return false;
    } else if (!_jtcByElement.equals(other._jtcByElement))
      return false;
    if (_jtcInputCheck == null) {
      if (other._jtcInputCheck != null)
        return false;
    } else if (!_jtcInputCheck.equals(other._jtcInputCheck))
      return false;
    if (_jtcInclude == null) {
      if (other._jtcInclude != null)
        return false;
    } else if (!_jtcInclude.equals(other._jtcInclude))
      return false;
    if (other._jtcPause != _jtcPause)
      return false;
    return true;
  }
  
  @Override
  public String toString() {
    String result = new ToStringHelper().toString(this);
    return result;
  }
}
