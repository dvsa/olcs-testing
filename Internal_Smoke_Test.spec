package dft.olcs

import java.util.Date
import java.text.SimpleDateFormat

describe 
"Internal 'Search' Journey Smoke Test"
{

// SCENARIO CONFIGURATION
// Enter the 'journeyStart' ('jtcBddIdentifier' column) reference from the 'journeys.csv' file, the corresponding row must contain 'start' and the element name and the element type to wait, and the starting URL
val journeyStart = "BDD0000-T02"
// Enter the required journey sequence (located in column 1 ('jtcBddIdentifier' field) within the reusable 'journeys.csv' file)
val journeyList = list(
'BDD0000-T02','BDD0000-R02','BDD0000-A01','BDD0000-B01','BDD0000-C01','BDD0000-D01','BDD0000-E01','BDD0000-G01','BDD0000-F01','BDD0000-H01','BDD0000-I01','BDD0000-J01','BDD0000-P01','BDD0000-M01','BDD0000-O01','BDD0000-L01','BDD0000-N01','BDD0000-E01','BDD0000-K01','BDD0000-O01','BDD0000-Q01','BDD0000-R01','BDD0000-S01','BDD0000-T01','BDD0000-U01','BDD0000-V01','BDD0000-G01','BDD0000-W01','BDD0000-X01','BDD0000-Y01','BDD0000-Z01','BDD0000-A02','BDD0000-B02','BDD0000-E01','BDD0000-C02','BDD0000-D02','BDD0000-E02','BDD0000-F02','BDD0000-G02','BDD0000-H02','BDD0000-I02','BDD0000-J02','BDD0000-K02','BDD0000-L02','BDD0000-M02','BDD0000-N02','BDD0000-O02','BDD0000-P02','BDD0000-S01','BDD0000-T01','BDD0000-Q02','BDD0000-V01','BDD0000-E01','BDD0000-S02'
)
// Available browsers are Internet Explorer (set to '1'), Mozilla Firefox (set to '2'), PhantomJS (set to '3')
val browserList = list(1)
// Available hubs are Functional (set to '1'), Accessibility (set to '2'), and Security (set to '3')
val hubList = list(1)
// If 'resetDataEnabled' is set to 'Yes' then the database will be reset prior to the test run
val resetDataEnabled = "Yes"
// If 'resetDataEnabled' is set to 'Yes' then the 'resetDataTag' must contain the initials in the 'description' tag at the top of this file (and make the corresponding SQL entries in the 'DVSA_Database_Class.xtend' file)
val resetDataTag = "isjst"
// If 'videoPluginEnabled is set to 'Yes' then the test run will be recorded in '.mp4' format 
val videoPluginEnabled ="No"
val videoPause = 5000
// If 'securityPluginEnabled' is set to 'Yes' then the 'hubList' value must be set to '3' and the 'browserList' value must be set to '2'
val securityPluginEnabled = "No"  
// If 'accessibilityPluginEnabled' is set to 'Yes' then 'hubList' and 'browserList' must both only contain a single value (set to '2') for the accessibility plugin to work
val accessibilityPluginEnabled = "Yes"
val accessibilityPause = 2000
val accessibilityCaptureImage = "Yes"
// Do not change 'simpleDateFormat' and 'timeStamp' values.
val simpleDateFormat = new SimpleDateFormat("dd_M_yyyy_hh_mm_ss")
val timeStamp = simpleDateFormat.format(new Date)
// Set 'testRunIdentifier' and 'reportDirectory' values to save audit artifacts, use the same name as this .spec file for the 'testRunIdentifier' value
val testRunIdentifier = "OLCS_v001_Internal_Search_Journey_Smoke_Test_"+timeStamp
val reportDirectory = "C://olcs_backup//reports//"

def pointTestCases {
  | String ptcBddIdentifier | String ptcTitle | String ptcAction | String ptcNameElement | String ptcByElement | String ptcInputCheck | String ptcInclude | int ptcPause |
  | "BDD0000-Z01" | "OLCS" | "wait" | "ompliance" | "partialLinkText" | "NA" | "Yes" | 2000 |
  } 

fact 
"As a Business Analyst / QA Lead
I want to be able to audit that the current software build meets Functional (BDD), Accessibility, and Security requirements on an internal 'Search' journey 
So that I can ensure that there is a working baseline on any specific build" 
{

browserList.forEach [ capability, capabilityIndex |
  hubList.forEach [ hub, hubIndex |
    pointTestCases.forEach [
      if (ptcInclude == "Yes") {
      executeTestRun(hubList.get(hubIndex), browserList.get(capabilityIndex), resetDataEnabled, resetDataTag, journeyStart, videoPluginEnabled, reportDirectory, testRunIdentifier, videoPause, accessibilityPluginEnabled, accessibilityCaptureImage, accessibilityPause, journeyList, ptcBddIdentifier, ptcTitle, ptcAction, ptcNameElement, ptcByElement, ptcInputCheck, ptcInclude, ptcPause) 
      }  
    ]
  ]
]

}

extension seleniumObject = new seleniumObject

}