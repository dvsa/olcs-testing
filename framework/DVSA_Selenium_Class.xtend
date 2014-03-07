package arc

import java.net.URL
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.JavascriptExecutor
import java.util.List

class seleniumObject {

def startDriver(int hubIndex, int capabilitiesIndex) {
  val capabilitiesMap = newHashMap (
    1 -> DesiredCapabilities.internetExplorer,
    2 -> DesiredCapabilities.firefox,
    3 -> DesiredCapabilities.phantomjs
  )
  val hubMap = newHashMap (
    1 -> "http://10.88.12.80:4444/wd/hub",  // Functional
    2 -> "http://10.88.12.80:4445/wd/hub",  // Accessibility
    3 -> "http://10.88.12.80:4446/wd/hub",  // Security (Partially implemented)
    4 -> "http://10.88.12.80:4447/wd/hub",  // Operational (Not implemented)
    5 -> "http://10.88.12.80:4448/wd/hub"   // Performance (Not implemented)
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

def doUserAction(String input, String action, WebElement execute) {
  switch action {
    case (action == "click") : execute.click
    case (action == "standardKeys") : execute.sendKeys(input)
    case (action == "specialKeys") : execute.sendKeys(Keys.valueOf(input))
  }
}

def getDocumentReadyState(RemoteWebDriver driver) {
  val JavascriptExecutor executor = driver
  val documentState = executor.executeScript("return document.readyState")
  return documentState
}

def loadAccessibilityPlugin(String accessibilityCaptureImage, String testCaseUrl, int accessibilityPause, RemoteWebDriver driver, String testRunIdentifier, String testRunIdentifierTimestamp, String reportDirectory) {
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
      accessibilityAuditFile(pluginIdentifier, testCaseUrl, testRunIdentifier, testRunIdentifierTimestamp, notification.getAttribute("alt"), reportDirectory, accessibilityCaptureImage)
      Thread.sleep(accessibilityPause)
    }
 ]
  accessibilityAction.contextClick(accessibilityStream).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform
}

def executePointTestCases(RemoteWebDriver driver, String mapSurface, String accessibilityPluginEnabled, String accessibilityCaptureImage, int accessibilityPause, String testRunIdentifier, String reportDirectory, String ptcBddIdentifier, String ptcTitle, String ptcAction, String ptcNameElement, String ptcByElement, String ptcInputCheck, String ptcInclude, int ptcPause) {
  val pointCaseStartUrl = driver.getCurrentUrl
  val pointDocumentReadyState = getDocumentReadyState(driver)
  if (ptcTitle == driver.getTitle && pointDocumentReadyState == "complete") {
  	if (mapSurface == "Yes") getSurface(driver, pointCaseStartUrl, ptcBddIdentifier, testRunIdentifier) else checkSurface(driver, pointCaseStartUrl, ptcBddIdentifier, reportDirectory, testRunIdentifier)
    switch ptcAction {
      case (ptcAction == "wait") : waitForElement(ptcByElement, ptcNameElement, driver)
      case (ptcAction == "click") : doUserAction(ptcInputCheck, ptcAction, fetchElement(ptcByElement, ptcNameElement, driver))
      case (ptcAction == "standardKeys") : doUserAction(ptcInputCheck, ptcAction, fetchElement(ptcByElement, ptcNameElement, driver))
      case (ptcAction == "specialKeys") : doUserAction(ptcInputCheck, ptcAction, fetchElement(ptcByElement, ptcNameElement, driver))
      case (ptcAction == "checkContent") : fetchElement(ptcByElement, ptcNameElement, driver).getText.contains(ptcInputCheck)
      case (ptcAction == "checkUrl") : driver.getCurrentUrl.contains(ptcInputCheck)
      case (ptcAction == "switch") : if (ptcNameElement != "defaultContent") driver.switchTo.frame(ptcNameElement) else driver.switchTo.defaultContent
      case (ptcAction == "url") : driver.navigate.to(ptcInputCheck)
      case (ptcAction == "hover") : doHover(ptcByElement, ptcNameElement, driver)        
    }
    Thread.sleep(ptcPause)
    val pointCaseEndUrl = driver.getCurrentUrl
    if (accessibilityPluginEnabled == "Yes" && pointCaseStartUrl != pointCaseEndUrl) loadAccessibilityPlugin(accessibilityCaptureImage, pointCaseEndUrl, accessibilityPause, driver, testRunIdentifier, testRunIdentifier+"_TC_"+ptcBddIdentifier, reportDirectory)  
  }
}

def executeTestRun (int hubIndex, int capabilityIndex, String mapSurface, String resetDataEnabled, String resetDataTag, String journeyStart, String videoPluginEnabled, String reportDirectory, String testRunIdentifier, int videoPause, String accessibilityPluginEnabled, String accessibilityCaptureImage, int accessibilityPause, List<String> journeyList, String ptcBddIdentifier, String ptcTitle, String ptcAction, String ptcNameElement, String ptcByElement, String ptcInputCheck, String ptcInclude, int ptcPause) { 
  val driver = startDriver(hubIndex, capabilityIndex)
  if (resetDataEnabled == "Yes") resetData(resetDataTag)
  journeyList.forEach [ value |
    journeyObjects.forEach [
      if (value.equals(jtcBddIdentifier)) {
      	print(value+'='+jtcBddIdentifier+"\n")
      	if (jtcAction == "start" && jtcBddIdentifier == journeyStart) {
      	  driver.get(jtcInputCheck)
      	  if (videoPluginEnabled == "Yes") startVideo(reportDirectory, testRunIdentifier, videoPause)
          val testCaseStartUrl = driver.getCurrentUrl
      	  waitForElement(jtcByElement, jtcNameElement, driver)
      	  if (accessibilityPluginEnabled == "Yes" && testCaseStartUrl == jtcInputCheck) loadAccessibilityPlugin(accessibilityCaptureImage, testCaseStartUrl, accessibilityPause, driver, testRunIdentifier, testRunIdentifier+"_Start_URL", reportDirectory)
      	}
        val journeyCaseStartUrl = driver.getCurrentUrl
        val journeyDocumentReadyState = getDocumentReadyState(driver)
        if (journeyDocumentReadyState == "complete") {
          if (mapSurface == "Yes") getSurface(driver, journeyCaseStartUrl, jtcBddIdentifier, testRunIdentifier) else checkSurface(driver, journeyCaseStartUrl, jtcBddIdentifier, reportDirectory, testRunIdentifier)
          switch jtcAction {
            case (jtcAction == "wait") : waitForElement(jtcByElement, jtcNameElement, driver)
            case (jtcAction == "click") : doUserAction(jtcInputCheck, jtcAction, fetchElement(jtcByElement, jtcNameElement, driver))
            case (jtcAction == "standardKeys") : doUserAction(jtcInputCheck, jtcAction, fetchElement(jtcByElement, jtcNameElement, driver))
            case (jtcAction == "specialKeys") : doUserAction(jtcInputCheck, jtcAction, fetchElement(jtcByElement, jtcNameElement, driver))
            case (jtcAction == "checkContent"): fetchElement(jtcByElement, jtcNameElement, driver).getText.contains(jtcInputCheck)
            case (jtcAction == "checkUrl") : driver.getCurrentUrl.contains(jtcInputCheck)
            case (jtcAction == "switch") : if (jtcNameElement != "defaultContent") driver.switchTo.frame(jtcNameElement) else driver.switchTo.defaultContent
            case (jtcAction == "url") : driver.navigate.to(jtcInputCheck)
            case (jtcAction == "hover") : doHover(jtcByElement, jtcNameElement, driver)
          }
        Thread.sleep(jtcPause)
        val journeyCaseEndUrl = driver.getCurrentUrl
        if (accessibilityPluginEnabled == "Yes" && journeyCaseStartUrl != journeyCaseEndUrl) loadAccessibilityPlugin(accessibilityCaptureImage, journeyCaseEndUrl, accessibilityPause, driver, testRunIdentifier, testRunIdentifier+"_TC_"+jtcBddIdentifier, reportDirectory)  
        }
      }
	]
  ]
  executePointTestCases(driver, mapSurface, accessibilityPluginEnabled, accessibilityCaptureImage, accessibilityPause, testRunIdentifier, reportDirectory, ptcBddIdentifier, ptcTitle, ptcAction, ptcNameElement, ptcByElement, ptcInputCheck, ptcInclude, ptcPause)
  if (videoPluginEnabled == "Yes") stopVideo(reportDirectory, testRunIdentifier, videoPause)
  driver.quit   
}

def doHover(String jtcByElement, String jtcNameElement, RemoteWebDriver driver) {
  val hover = fetchElement(jtcByElement, jtcNameElement, driver)
  val JavascriptExecutor executor = driver
  executor.executeScript("arguments[0].scrollIntoView(true);", hover);
  val hoverAction = new Actions(driver)
  hoverAction.moveToElement(hover).build.perform
}

def getSurface(RemoteWebDriver driver, String testCaseUrl, String testCaseIdentifier, String testRunIdentifier) {
  val String[] sections = testRunIdentifier.split("-")
  val testIdentifier = sections.get(0)
  val currentInputs = driver.findElements(By.tagName("input"))
  currentInputs.forEach [ currentInput |
  	surfaceAuditBaseline(testCaseUrl, testCaseIdentifier, currentInput.getAttribute("value"), testIdentifier)
  ] 
}

def checkSurface(RemoteWebDriver driver, String testCaseUrl, String testCaseIdentifier, String reportDirectory, String testRunIdentifier) {
  val String[] sections = testRunIdentifier.split("-")
  val testIdentifier = sections.get(0)
  val currentInputs = driver.findElements(By.tagName("input"))
  currentInputs.forEach [ currentInput |
  surfaceAuditCurrent(testCaseUrl, testCaseIdentifier, currentInput.getAttribute("value"), reportDirectory, testRunIdentifier)
  ]
  surfaceAuditCompare(testCaseIdentifier, reportDirectory, testRunIdentifier, testIdentifier)
}

extension auditObject = new auditObject
extension journeyObject = new journeyObject
extension databaseObject = new databaseObject

}

