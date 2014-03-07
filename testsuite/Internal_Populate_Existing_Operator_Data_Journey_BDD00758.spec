package arc

import java.util.Date
import java.text.SimpleDateFormat

describe 
"Internal 'Populate Existing Operator Data' Journey BDD00758"
{
// SCENARIO CONFIGURATION
// Enter the 'journeyStart' ('jtcBddIdentifier' column) reference from the 'journeys.csv' file, the corresponding row must contain 'start' and the element name and the element type to wait, and the starting URL
val journeyStart = "BDD0000-T02"
// Enter the required journey sequence (located in column 1 ('jtcBddIdentifier' field) within the reusable 'journeys.csv' file)
val journeyList = list(
'BDD0000-T02','BDD0000-V02','BDD0000-E10','BDD0000-A04','BDD0000-B04','BDD0000-C04','BDD0000-D04','BDD0000-F04','BDD0000-G04','BDD0000-H04','BDD0000-I04','BDD0000-J04','BDD0000-K04','BDD0000-L04','BDD0000-M04','BDD0000-E07','BDD0000-N04','BDD0000-O04','BDD0000-P04','BDD0000-Q04','BDD0000-E07'
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
val resetDataTag = "iec0304"
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
val testRunIdentifier = "OLCS_v001_Internal_Populate_Existing_Operator_Data_Journey_BDD00758-"+timeStamp
val reportDirectory = "C://ARC//reports//"

def pointTestCases {
  | String ptcBddIdentifier | String ptcTitle | String ptcAction | String ptcNameElement | String ptcByElement | String ptcInputCheck | String ptcInclude | int ptcPause |
  | "BDD0000-Z01" | "OLCS" | "wait" | "ompliance" | "partialLinkText" | "NA" | "Yes" | 2000 |
  } 

fact 
"As a FEP caseworker
I want to be able to select an existing operator name from a search results and populate their existing data 
(registered address, company number, persons, subsidiary companies) So that I don't have to type information 
already available in OLCS" 
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