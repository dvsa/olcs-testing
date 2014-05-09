package olcs

import java.net.URL
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.support.ui.Select

class seleniumObject {

def startDriver(int hubIndex, int capabilitiesIndex) {
  val capabilitiesMap = newHashMap (
    1 -> DesiredCapabilities.internetExplorer,
    2 -> DesiredCapabilities.firefox,
    3 -> DesiredCapabilities.phantomjs,
    4 -> DesiredCapabilities.chrome
  )
  val hubMap = newHashMap (
    1 -> "http://192.168.2.95:4444/wd/hub",  // Functional
    2 -> "http://192.168.2.95:4445/wd/hub",  // Accessibility
    3 -> "http://192.168.2.95:4446/wd/hub",  // Security
    4 -> "http://192.168.2.95:4447/wd/hub",  // Operational
    5 -> "http://192.168.2.95:4448/wd/hub"   // Performance
  )
  val driverResult = new RemoteWebDriver(new URL(hubMap.get(hubIndex)), capabilitiesMap.get(capabilitiesIndex))
  return driverResult
}

def fetchElement(String tcByElement, String tcNameElement, RemoteWebDriver driver) {
  val resultElement = switch tcByElement {
    case (tcByElement == "linkText") : driver.findElement(By::linkText(tcNameElement))
    case (tcByElement == "id") : driver.findElement(By::id(tcNameElement))
    case (tcByElement == "tagName") : driver.findElement(By::tagName(tcNameElement))
    case (tcByElement == "partialLinkText") : driver.findElement(By::partialLinkText(tcNameElement))
    case (tcByElement == "className") : driver.findElement(By::className(tcNameElement))
    case (tcByElement == "xpathHref") : driver.findElement(By::xpath("//a[@href = '" + tcNameElement + "']"))
    case (tcByElement == "xpathLinkContains") : driver.findElement(By::xpath("//a[contains(text(), '" + tcNameElement + "')]"))
    case (tcByElement == "cssSelector") : driver.findElement(By::cssSelector(tcNameElement +" > ul.multiselect > li > div > a"))
    case (tcByElement == "xpathLabelContains") : driver.findElement(By::xpath("//label[contains(.,'"+ tcNameElement +"')]/input"))
    case (tcByElement == "inputName") : driver.findElement(By::name(tcNameElement))
    default : null
  }
  return resultElement
}

def fetchElements(String tcByElement, String tcNameElement, RemoteWebDriver driver) {
  val resultElements = switch tcByElement {
    case tcByElement == "className" : driver.findElements(By::className(tcNameElement))
    default : null
  }
  return resultElements	
}

def waitForElement(String tcByElement, String tcNameElement, RemoteWebDriver driver) {
  val wait = new WebDriverWait(driver, 30)
  switch tcByElement {
    case (tcByElement == "linkText") : wait.until[findElement(By::linkText(tcNameElement))].isDisplayed
    case (tcByElement == "id") : wait.until[findElement(By::id(tcNameElement))].isDisplayed
    case (tcByElement == "tagName") : wait.until[findElement(By::tagName(tcNameElement))].isDisplayed
    case (tcByElement == "partialLinkText") : wait.until[findElement(By::partialLinkText(tcNameElement))].isDisplayed
    case (tcByElement == "className") : wait.until[findElement(By::className(tcNameElement))].isDisplayed
    case (tcByElement == "xpathHref") : wait.until[findElement(By::xpath("//a[@href = '" + tcNameElement + "']"))].isDisplayed
    case (tcByElement == "xpathLinkContains") : wait.until[findElement(By::xpath("//a[contains(text(), '" + tcNameElement + "')]"))].isDisplayed
    case (tcByElement == "cssSelector") : wait.until[findElement(By::cssSelector(tcNameElement +" > ul.multiselect > li > div > a"))].isDisplayed
    case (tcByElement == "xpathLabelContains") : wait.until[findElement(By.xpath("//label[contains(.,'"+ tcNameElement +"')]/input"))].isDisplayed
    case (tcByElement == "inputName") : driver.findElement(By::name(tcNameElement)).isDisplayed
  }
}

def doUserAction(String accessibilityPluginEnabled, String currentUrl, int accessibilityPause, RemoteWebDriver driver, String testRunIdentifier, String testRunIdentifierTimestamp, String reportDirectory, String input, String action, WebElement execute) {
  if (accessibilityPluginEnabled == "Y") loadAccessibilityPlugin(currentUrl, accessibilityPause, driver, testRunIdentifier, testRunIdentifierTimestamp, reportDirectory)  
  switch action {
    case (action == "click") : execute.click
    case (action == "standardKeys") : execute.sendKeys(input)
    case (action == "specialKeys") : execute.sendKeys(Keys.valueOf(input))
  }
  if (accessibilityPluginEnabled == "Y") unloadAccessibilityPlugin(driver)
}

def doUrlNavigation(String accessibilityPluginEnabled, String currentUrl, int accessibilityPause, RemoteWebDriver driver, String testRunIdentifier, String testRunIdentifierTimestamp, String reportDirectory, String action, String navigateUrl) {
	if (accessibilityPluginEnabled == "Y") loadAccessibilityPlugin(currentUrl, accessibilityPause, driver, testRunIdentifier, testRunIdentifierTimestamp, reportDirectory)  
	driver.navigate.to(navigateUrl)
	if (accessibilityPluginEnabled == "Y") loadAccessibilityPlugin(currentUrl, accessibilityPause, driver, testRunIdentifier, testRunIdentifierTimestamp, reportDirectory)  
}

def getDocumentReadyState(RemoteWebDriver driver) {
  val JavascriptExecutor executor = driver
  val documentState = executor.executeScript("return document.readyState")
  return documentState
}

def loadAccessibilityPlugin(String testCaseUrl, int accessibilityPause, RemoteWebDriver driver, String testRunIdentifier, String testRunIdentifierTimestamp, String reportDirectory) {
  val pluginIdentifier = "Accessibility"
  val accessibilityAction = new Actions(driver)
  waitForElement("tagName", "body", driver)
  val accessibilityStream = fetchElement("tagName", "body", driver)
  accessibilityAction.contextClick(accessibilityStream).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform
  waitForElement("className", "wave4tip", driver)
  val notifications = fetchElements("className", "wave4tip", driver)
  notifications.forEach [ notification |
    if (notification.getAttribute("alt").startsWith("ERROR: ")) {
      val JavascriptExecutor executor = driver
      executor.executeScript("arguments[0].scrollIntoView(true);", notification);
      val accessibilityAlertAction = new Actions(driver)
      accessibilityAlertAction.moveToElement(notification).perform
      Thread.sleep(accessibilityPause)
      accessibilityAuditFile(pluginIdentifier, testCaseUrl, testRunIdentifier, testRunIdentifierTimestamp, notification.getAttribute("alt"), reportDirectory)
    }
  ]
}

def unloadAccessibilityPlugin(RemoteWebDriver driver) {
  val accessibilityAction = new Actions(driver)
  waitForElement("tagName", "body", driver)
  val accessibilityStream = fetchElement("tagName", "body", driver)
  accessibilityAction.contextClick(accessibilityStream).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform   
}

def executeTestSuite(String testCases, String browserList, String hubList, String mapSurface, String recordVideo, int videoPause, String runSecurity, String runAccessibility, int accessibilityPause, String groupRunIdentifier, String reportDirectory) {
  val splitCases = testCases.split(",")
  val splitBrowser = browserList.split(",")
  val splitHub = hubList.split(",")
  splitBrowser.forEach [ capabilityIndex |
    splitHub.forEach [ hubIndex |
      val driver = startDriver(Integer.parseInt(hubIndex), Integer.parseInt(capabilityIndex))
      splitCases.forEach [ testCase |
      	caseObjects.forEach [
      	  if (testCase.equals(bddIdentifier)) {
      	  print(testCase)
            val splitSequence = bddSequence.split(",")
            val startUrl = startPoint
            splitSequence.forEach [ sequenceIdentifier |
              journeyObjects.forEach [
                val uniqueRunIdentifier = groupRunIdentifier+"_"+testCase
      		    if (sequenceIdentifier.equals(jtcBddIdentifier)) {
                  if (jtcAction == "start" && jtcBddIdentifier == startUrl) {
      		  	    driver.get(jtcInputCheck)
      	            if (recordVideo == "Y") startVideo(reportDirectory, uniqueRunIdentifier, videoPause)
      	            waitForElement(jtcByElement, jtcNameElement, driver)
      	          }
      	         val journeyCaseStartUrl = driver.getCurrentUrl
                 val journeyDocumentReadyState = getDocumentReadyState(driver)
                 if (journeyDocumentReadyState == "complete") {
                  if (mapSurface == "Y") getSurface(driver, journeyCaseStartUrl, jtcBddIdentifier, reportDirectory, uniqueRunIdentifier) else checkSurface(driver, journeyCaseStartUrl, jtcBddIdentifier, reportDirectory, uniqueRunIdentifier)
                   print(jtcBddIdentifier)
                   switch jtcAction {
                     case (jtcAction == "wait") : waitForElement(jtcByElement, jtcNameElement, driver)
                     case (jtcAction == "click") : doUserAction(runAccessibility, driver.getCurrentUrl, accessibilityPause, driver, uniqueRunIdentifier, uniqueRunIdentifier+"_TC_"+jtcBddIdentifier, reportDirectory, jtcInputCheck, jtcAction, fetchElement(jtcByElement, jtcNameElement, driver))
                     case (jtcAction == "standardKeys") : doUserAction(runAccessibility, driver.getCurrentUrl, accessibilityPause, driver, uniqueRunIdentifier, uniqueRunIdentifier+"_TC_"+jtcBddIdentifier, reportDirectory, jtcInputCheck, jtcAction, fetchElement(jtcByElement, jtcNameElement, driver))
                     case (jtcAction == "specialKeys") : doUserAction(runAccessibility, driver.getCurrentUrl, accessibilityPause, driver, uniqueRunIdentifier, uniqueRunIdentifier+"_TC_"+jtcBddIdentifier, reportDirectory, jtcInputCheck, jtcAction, fetchElement(jtcByElement, jtcNameElement, driver))
                     case (jtcAction == "checkContent"): fetchElement(jtcByElement, jtcNameElement, driver).getText.contains(jtcInputCheck)
                     case (jtcAction == "checkUrl") : driver.getCurrentUrl.contains(jtcInputCheck)
                     case (jtcAction == "switch") : if (jtcNameElement != "defaultContent") driver.switchTo.frame(jtcNameElement) else driver.switchTo.defaultContent
                     case (jtcAction == "url") : doUrlNavigation(runAccessibility, driver.getCurrentUrl, accessibilityPause, driver, uniqueRunIdentifier, uniqueRunIdentifier+"_TC_"+jtcBddIdentifier, reportDirectory, jtcAction, jtcInputCheck)
                     case (jtcAction == "hover") : doHover(jtcByElement, jtcNameElement, driver)
                     case (jtcAction == "select") : doSelect(jtcByElement, jtcNameElement, jtcInputCheck, driver)
                   }
                   Thread.sleep(jtcPause) 
                 } 
                }
      		  ]
      		]
      	  }
      	]
      ]
      if (recordVideo == "Y") stopVideo(videoPause)  
      driver.quit
    ]
  ]
  
}

def doSelect(String jtcByElement, String jtcNameElement, RemoteWebDriver driver) {
  val String[] sections = jtcInputCheck.split(",")
  val bySelect = sections.get(0)
  val dropDown = if (jtcByElement == "id") new Select(driver.findElement(By::id(jtcNameElement))) else new Select(driver.findElement(By::name(jtcNameElement)))
}

def doHover(String jtcByElement, String jtcNameElement, RemoteWebDriver driver) {
  val hover = fetchElement(jtcByElement, jtcNameElement, driver)
  val JavascriptExecutor executor = driver
  executor.executeScript("arguments[0].scrollIntoView(true);", hover);
  val hoverAction = new Actions(driver)
  hoverAction.moveToElement(hover).build.perform
}

def getSurface(RemoteWebDriver driver, String testCaseUrl, String testCaseIdentifier, String reportDirectory, String testRunIdentifier) {
  val String[] sections = testRunIdentifier.split("-")
  val testIdentifier = sections.get(0)
  val currentInputs = driver.findElements(By.tagName("input"))
  currentInputs.forEach [ currentInput |
  	surfaceAuditBaseline(testCaseUrl, testCaseIdentifier, currentInput.getAttribute("id"), testIdentifier, reportDirectory)
  ] 
}

def checkSurface(RemoteWebDriver driver, String testCaseUrl, String testCaseIdentifier, String reportDirectory, String testRunIdentifier) {
  val String[] sections = testRunIdentifier.split("-")
  val testIdentifier = sections.get(0)
  val currentInputs = driver.findElements(By.tagName("input"))
  currentInputs.forEach [ currentInput |
  surfaceAuditCurrent(testCaseUrl, testCaseIdentifier, currentInput.getAttribute("id"), reportDirectory, testRunIdentifier)
  ]
  surfaceAuditCompare(testCaseIdentifier, reportDirectory, testRunIdentifier, testIdentifier)
}

extension auditObject = new auditObject
extension journeyObject = new journeyObject
extension caseObject = new caseObject

}