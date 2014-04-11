package olcs

import java.util.Date
import java.text.SimpleDateFormat

describe "OLCS_Selenium_Test_Suite"
{

val simpleDateFormat = new SimpleDateFormat("dd_M_yyyy_hh_mm_ss")
val timeStamp = simpleDateFormat.format(new Date)

fact
"Scriptless Selenium Test Suite" 
{

suiteObjects.forEach [
  if (includeGroup == "Y") {
    executeTestSuite(testCases, browserList, hubList, mapSurface, recordVideo, videoPause, runSecurity, runAccessibility, accessibilityPause, groupRunIdentifier+timeStamp, reportDirectory)
  }
]

}

extension seleniumObject = new seleniumObject
extension suiteObject = new suiteObject

}