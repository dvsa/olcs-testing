package olcs

import java.util.Date
import java.text.SimpleDateFormat

describe "OLCS_Selenium_Test_Suite"
{

val simpleDateFormat = new SimpleDateFormat("dd_M_yyyy_hh_mm_ss")
val timeStamp = simpleDateFormat.format(new Date)

fact
"As a quality assurance lead
I want to be able to audit that the current software build meets Functional (BDD), Accessibility, and Security requirements by grouping test cases together
So that I can ensure that there is a working baseline on any specific build using any combination of browsers and test cases" 
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