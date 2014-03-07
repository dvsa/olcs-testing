package arc

import java.util.Date
import java.text.SimpleDateFormat

describe 
"External 'New Application' Journey Smoke Test"
{

// SCENARIO CONFIGURATION
// Enter the 'journeyStart' ('jtcBddIdentifier' column) reference from the 'journeys.csv' file, the corresponding row must contain 'start' and the element name and the element type to wait, and the starting URL
val journeyStart = "BDD0000-U02"
// Enter the required journey sequence, (located in column 1 ('jtcBddIdentifier' field) within the reusable 'journeys.csv' file)
val journeyList = list(
'BDD0000-U02','BDD0000-V02','BDD0000-X02','BDD0000-C05','BDD0000-W02','BDD0000-Z02','BDD0000-Y02','BDD0000-B03','BDD0000-A03','BDD0000-C03','BDD0000-D03','BDD0000-B05'
)
// Available browsers are Internet Explorer (set to '1'), Mozilla Firefox (set to '2'), PhantomJS (set to '3')
val browserList = list(1)
// Available hubs are Functional (set to '1'), Accessibility (set to '2'), and Security (set to '3')
val hubList = list(1)
// Map the surface (input vectors) of the application for each test step and saves each one to a file, in order to compare the surface in each test subsequent test run with the saved baseline. Should be set to 'No' except for baselining the surface when it should be set to 'Yes'
val mapSurface = "No"
// If 'resetDataEnabled' is set to 'Yes' then the database will be reset prior to the test run
val resetDataEnabled = "Yes"
// If 'resetDataEnabled' is set to 'Yes' then the 'resetDataTag' must contain the initials in the 'description' tag at the top of this file (and make the corresponding SQL entries in the 'DVSA_Database_Class.xtend' file)
val resetDataTag = "enajst"
// If 'videoPluginEnabled is set to 'Yes' then the test run will be recorded in '.mp4' format 
val videoPluginEnabled ="No"
val videoPause = 5000
// If 'securityPluginEnabled' is set to 'Yes' then the 'hubList' value must be set to '3' and the 'browserList' value must be set to '2'
val securityPluginEnabled = "No"  
// If 'accessibilityPluginEnabled' is set to 'Yes' then 'hubList' and 'browserList' must both only contain a single value (set to '2') for the accessibility plugin to work
val accessibilityPluginEnabled = "No"
val accessibilityPause = 2000
val accessibilityCaptureImage = "Yes"
// Do not change 'simpleDateFormat' and 'timeStamp' values.
val simpleDateFormat = new SimpleDateFormat("dd_M_yyyy_hh_mm_ss")
val timeStamp = simpleDateFormat.format(new Date)
// Set 'testRunIdentifier' and 'reportDirectory' values to save audit artifacts, use the same name as this .spec file for the 'testRunIdentifier' value
val testRunIdentifier = "OLCS_v001_External_New_Application_Journey_Smoke_Test-"+timeStamp
val reportDirectory = "C://ARC//reports//"

def pointTestCases {
  | String ptcBddIdentifier | String ptcTitle | String ptcAction | String ptcNameElement | String ptcByElement | String ptcInputCheck | String ptcInclude | int ptcPause |
  | "BDD0000-Z01" | "OLCS" | "wait" | "GOV.UK" | "linkText" | "NA" | "Yes" | 2000 |
  } 

fact 
"As a Business Analyst / QA Lead
I want to be able to audit that the current software build meets Functional (BDD), Accessibility, and Security requirements on an external 'New Application' journey 
So that I can ensure that there is a working baseline on any specific build" 
{

browserList.forEach [ capability, capabilityIndex |
  hubList.forEach [ hub, hubIndex |
    pointTestCases.forEach [
      if (ptcInclude == "Yes") {
        executeTestRun(hubList.get(hubIndex), browserList.get(capabilityIndex), mapSurface, resetDataEnabled, resetDataTag, journeyStart, videoPluginEnabled, reportDirectory, testRunIdentifier, videoPause, accessibilityPluginEnabled, accessibilityCaptureImage, accessibilityPause, journeyList, ptcBddIdentifier, ptcTitle, ptcAction, ptcNameElement, ptcByElement, ptcInputCheck, ptcInclude, ptcPause) 
      }  
    ]
  ]
]

}

extension seleniumObject = new seleniumObject

}