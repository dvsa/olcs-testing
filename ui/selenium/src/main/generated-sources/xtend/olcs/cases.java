package olcs;

import org.eclipse.xtend.lib.Data;
import org.eclipse.xtext.xbase.lib.util.ToStringHelper;

@Data
@SuppressWarnings("all")
public class cases {
  private final String _bddIdentifier;
  
  public String getBddIdentifier() {
    return this._bddIdentifier;
  }
  
  private final String _bddAuthor;
  
  public String getBddAuthor() {
    return this._bddAuthor;
  }
  
  private final String _bddType;
  
  public String getBddType() {
    return this._bddType;
  }
  
  private final String _bddTitle;
  
  public String getBddTitle() {
    return this._bddTitle;
  }
  
  private final String _bddStory;
  
  public String getBddStory() {
    return this._bddStory;
  }
  
  private final String _startPoint;
  
  public String getStartPoint() {
    return this._startPoint;
  }
  
  private final String _bddSequence;
  
  public String getBddSequence() {
    return this._bddSequence;
  }
  
  public cases(final String bddIdentifier, final String bddAuthor, final String bddType, final String bddTitle, final String bddStory, final String startPoint, final String bddSequence) {
    super();
    this._bddIdentifier = bddIdentifier;
    this._bddAuthor = bddAuthor;
    this._bddType = bddType;
    this._bddTitle = bddTitle;
    this._bddStory = bddStory;
    this._startPoint = startPoint;
    this._bddSequence = bddSequence;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((_bddIdentifier== null) ? 0 : _bddIdentifier.hashCode());
    result = prime * result + ((_bddAuthor== null) ? 0 : _bddAuthor.hashCode());
    result = prime * result + ((_bddType== null) ? 0 : _bddType.hashCode());
    result = prime * result + ((_bddTitle== null) ? 0 : _bddTitle.hashCode());
    result = prime * result + ((_bddStory== null) ? 0 : _bddStory.hashCode());
    result = prime * result + ((_startPoint== null) ? 0 : _startPoint.hashCode());
    result = prime * result + ((_bddSequence== null) ? 0 : _bddSequence.hashCode());
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
    cases other = (cases) obj;
    if (_bddIdentifier == null) {
      if (other._bddIdentifier != null)
        return false;
    } else if (!_bddIdentifier.equals(other._bddIdentifier))
      return false;
    if (_bddAuthor == null) {
      if (other._bddAuthor != null)
        return false;
    } else if (!_bddAuthor.equals(other._bddAuthor))
      return false;
    if (_bddType == null) {
      if (other._bddType != null)
        return false;
    } else if (!_bddType.equals(other._bddType))
      return false;
    if (_bddTitle == null) {
      if (other._bddTitle != null)
        return false;
    } else if (!_bddTitle.equals(other._bddTitle))
      return false;
    if (_bddStory == null) {
      if (other._bddStory != null)
        return false;
    } else if (!_bddStory.equals(other._bddStory))
      return false;
    if (_startPoint == null) {
      if (other._startPoint != null)
        return false;
    } else if (!_startPoint.equals(other._startPoint))
      return false;
    if (_bddSequence == null) {
      if (other._bddSequence != null)
        return false;
    } else if (!_bddSequence.equals(other._bddSequence))
      return false;
    return true;
  }
  
  @Override
  public String toString() {
    String result = new ToStringHelper().toString(this);
    return result;
  }
}
