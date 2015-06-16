package olcs

import java.util.Date
import java.text.SimpleDateFormat

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

describe "OLCS_Selenium_Test_Framework"
{

val simpleDateFormat = new SimpleDateFormat("dd_M_yyyy_hh_mm_ss")
val timeStamp = simpleDateFormat.format(new Date)

fact
"Scriptless Selenium Test Suite" 
{
suiteObjects.forEach [
  if (includeGroup == "Y") {
    println("Running Suite (Suite Id) :" + suiteId)
    executeTestSuite(suiteId, testCases, mapSurface, recordVideo, videoPause, runSecurity, runAccessibility, accessibilityPause, groupRunIdentifier+timeStamp, reportDirectory)
  }
]

suiteObjects.forEach [
 assertThat("At least one of the tests has failed. Please view the test report attached to discover which failures occurred.", isSuiteSuccessful(),is(true))
]

}

extension seleniumObject = new seleniumObject
extension suiteObject = new suiteObject

}