package olcs

import java.net.URL
import java.io.File
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.JavascriptExecutor
import java.util.ResourceBundle;
import org.openqa.selenium.remote.DesiredCapabilities
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor
import java.util.concurrent.TimeUnit

class seleniumObject {
    private boolean suiteSuccessful = true
    private String envName = "dev"
    private String browserIndex = "2"

    def isSuiteSuccessful() {
        return this.suiteSuccessful
    }

    def startDriver() {
        val capabilitiesMap = newHashMap(
            1 -> DesiredCapabilities.internetExplorer,
            2 -> DesiredCapabilities.firefox,
            3 -> DesiredCapabilities.phantomjs,
            4 -> DesiredCapabilities.chrome
        )
        envName = System.getProperty("env")
        if (null == envName) {
            envName = "dev"
        }
        //val environment = envName
        browserIndex = System.getProperty("browser")
        if (null == browserIndex) {
            browserIndex = "2"
        }

        val hub = fetchHub(envName, browserIndex)
        val browser = Integer.parseInt(browserIndex)
        //val rb = ResourceBundle.getBundle("environments")
        System.out.println(capabilitiesMap)
        System.out.println("Environment running in is " + envName)
        System.out.println("Hub requested " + hub)
        System.out.println("Browser " + capabilitiesMap.get(browser))
        val driverResult = new RemoteWebDriver(new URL(hub), capabilitiesMap.get(browser))
        //driverResult.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS)
        return driverResult
    }

    def fetchHub(String envName, String browserIndex) {
        val rb = ResourceBundle.getBundle("environments")
        var hubToUse = ""

        try {
            hubToUse = rb.getString("hub." + envName + "." + browserIndex)
        } catch (Exception e) {
        }

        if (hubToUse == "") {
            hubToUse = rb.getString("hub." + envName)
        }
        System.out.println("Hub Chosen: " + hubToUse)

        return hubToUse
    }

    def fetchElement(String tcByElement, String tcNameElement, RemoteWebDriver driver) {
        val resultElement = switch tcByElement {
            case ( tcByElement == "linkText") :
                driver.findElement(By::linkText(tcNameElement))
            case ( tcByElement == "id") :
                driver.findElement(By::id(tcNameElement))
            case ( tcByElement == "tagName") :
                driver.findElement(By::tagName(tcNameElement))
            case ( tcByElement == "partialLinkText") :
                driver.findElement(By::partialLinkText(tcNameElement))
            case ( tcByElement == "className") :
                driver.findElement(By::className(tcNameElement))
            case ( tcByElement == "xpathHref") :
                driver.findElement(By::xpath("//a[@href = '" + tcNameElement + "']"))
            case ( tcByElement == "xpathLinkContains") :
                driver.findElement(By::xpath("//a[contains(text(), '" + tcNameElement + "')]"))
            case ( tcByElement == "cssSelector") :
                driver.findElement(By::cssSelector(tcNameElement + " > ul.multiselect > li > div > a"))
            case ( tcByElement == "xpathLabelContains") :
                driver.findElement(By::xpath("//label[contains(.,'" + tcNameElement + "')]/input"))
            case ( tcByElement == "xpathValue") :
                driver.findElement(By::xpath("//input[@value='" + tcNameElement + "']"))
            case ( tcByElement == "inputName") :
                driver.findElement(By::name(tcNameElement))
            case ( tcByElement == "xpath") :
                driver.findElement(By::xpath(tcNameElement))
            default:
                null
        }
        return resultElement
    }

    def fetchElements(String tcByElement, String tcNameElement, RemoteWebDriver driver) {
        val resultElements = switch tcByElement {
            case tcByElement == "className": driver.findElements(By::className(tcNameElement))
            default: null
        }
        return resultElements
    }

    def waitForElement(String tcByElement, String tcNameElement, RemoteWebDriver driver) {
        val wait = new WebDriverWait(driver, 30)
        switch tcByElement {
            case ( tcByElement == "linkText") :
                wait.until[findElement(By::linkText(tcNameElement))].isDisplayed
            case ( tcByElement == "id") :
                wait.until[findElement(By::id(tcNameElement))].isDisplayed
            case ( tcByElement == "tagName") :
                wait.until[findElement(By::tagName(tcNameElement))].isDisplayed
            case ( tcByElement == "partialLinkText") :
                wait.until[findElement(By::partialLinkText(tcNameElement))].isDisplayed
            case ( tcByElement == "className") :
                wait.until[findElement(By::className(tcNameElement))].isDisplayed
            case ( tcByElement == "xpathHref") :
                wait.until [
                    findElement(By::xpath("//a[@href = '" + tcNameElement + "']"))
                ].isDisplayed
            case ( tcByElement == "xpathLinkContains") :
                wait.until [
                    findElement(By::xpath("//a[contains(text(), '" + tcNameElement + "')]"))
                ].isDisplayed
            case ( tcByElement == "cssSelector") :
                wait.until [
                    findElement(By::cssSelector(tcNameElement + " > ul.multiselect > li > div > a"))
                ].isDisplayed
            case ( tcByElement == "xpathLabelContains") :
                wait.until [
                    findElement(By.xpath("//label[contains(.,'" + tcNameElement + "')]/input"))
                ].isDisplayed
            case ( tcByElement == "xpathValue") :
                driver.findElement(By::xpath("//input[@value='" + tcNameElement + "']"))
            case ( tcByElement == "inputName") :
                driver.findElement(By::name(tcNameElement)).isDisplayed
            case ( tcByElement == "xpath") :
                driver.findElement(By::xpath(tcNameElement)).isDisplayed
        }
    }

    def doUserAction(String accessibilityPluginEnabled, String currentUrl, int accessibilityPause,
        RemoteWebDriver driver, String testRunIdentifier, String testRunIdentifierTimestamp, String reportDirectory,
        String input, String action, WebElement execute) {
        if (accessibilityPluginEnabled == "Y")
            loadAccessibilityPlugin(currentUrl, accessibilityPause, driver, testRunIdentifier,
                testRunIdentifierTimestamp, reportDirectory)
        switch action {
            case ( action == "click") : execute.click
            case ( action == "standardKeysOneAtATime") : execute.sendKeys(input)
            case ( action == "standardKeys") : sendFullValue(driver, input, execute)
            case ( action == "specialKeys") : execute.sendKeys(Keys.valueOf(input))
            case ( action == "clear") : execute.clear()
        }
        if(accessibilityPluginEnabled == "Y") unloadAccessibilityPlugin(driver)
    }

    def sendFullValue(RemoteWebDriver driver, String input, WebElement element) {
        var jsExecutor = driver as JavascriptExecutor
        jsExecutor.executeScript("arguments[0].value='" + input + "'", element);
    }

    def doUrlNavigation(String accessibilityPluginEnabled, String currentUrl, int accessibilityPause,
        RemoteWebDriver driver, String testRunIdentifier, String testRunIdentifierTimestamp, String reportDirectory,
        String action, String navigateUrl) {
        if (accessibilityPluginEnabled == "Y")
            loadAccessibilityPlugin(currentUrl, accessibilityPause, driver, testRunIdentifier,
                testRunIdentifierTimestamp, reportDirectory)
        driver.navigate.to(navigateUrl)
        if (accessibilityPluginEnabled == "Y")
            loadAccessibilityPlugin(currentUrl, accessibilityPause, driver, testRunIdentifier,
                testRunIdentifierTimestamp, reportDirectory)
    }

    def getDocumentReadyState(RemoteWebDriver driver) {
        val JavascriptExecutor executor = driver
        val documentState = executor.executeScript("return document.readyState")
        return documentState
    }

    def loadAccessibilityPlugin(String testCaseUrl, int accessibilityPause, RemoteWebDriver driver,
        String testRunIdentifier, String testRunIdentifierTimestamp, String reportDirectory) {
        val pluginIdentifier = "Accessibility"
        val accessibilityAction = new Actions(driver)
        waitForElement("tagName", "body", driver)
        val accessibilityStream = fetchElement("tagName", "body", driver)
        accessibilityAction.contextClick(accessibilityStream).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_RIGHT).
            sendKeys(Keys.ENTER).perform
        waitForElement("className", "wave4tip", driver)
        val notifications = fetchElements("className", "wave4tip", driver)
        notifications.forEach [ notification |
            if (notification.getAttribute("alt").startsWith("ERROR: ")) {
                val JavascriptExecutor executor = driver
                executor.executeScript("arguments[0].scrollIntoView(true);", notification);
                val accessibilityAlertAction = new Actions(driver)
                accessibilityAlertAction.moveToElement(notification).perform
                Thread.sleep(accessibilityPause)
                accessibilityAuditFile(pluginIdentifier, testCaseUrl, testRunIdentifier, testRunIdentifierTimestamp,
                    notification.getAttribute("alt"), reportDirectory)
            }
        ]
    }

    def unloadAccessibilityPlugin(RemoteWebDriver driver) {
        val accessibilityAction = new Actions(driver)
        waitForElement("tagName", "body", driver)
        val accessibilityStream = fetchElement("tagName", "body", driver)
        accessibilityAction.contextClick(accessibilityStream).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_RIGHT).
            sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).
            sendKeys(Keys.ENTER).perform
    }

    def shouldRunTestSuite(String suiteId) {
        var runSuiteId = System.getProperty("suite.id")
        if (null == runSuiteId || suiteId.equals(runSuiteId)) {
            return true
        }
        println("Suite (" + suiteId + ") will not be executed because suite (" + runSuiteId + ") was requested")
        return false
    }

    def executeTestSuite(String suiteId, String testCases, String mapSurface, String recordVideo, int videoPause,
        String runSecurity, String runAccessibility, int accessibilityPause, String groupRunIdentifier,
        String reportDir) {

            if (shouldRunTestSuite(suiteId) == false) {
                return false
            }

            val splitCases = testCases.split(",")

            val driver = startDriver()
            val reportDirectory = System.getProperty("user.dir") + File.separator + reportDir
            println("Reports will be generated in " + reportDirectory)
            val reportDirectories = new File(reportDirectory)
            reportDirectories.mkdirs()
            splitCases.forEach [ testCase |
                caseObjects.forEach [
                    if (testCase.equals(bddIdentifier)) {
                        try {
                            print("-- " + testCase + "\n")
                            val splitSequence = bddSequence.split(",")
                            val startUrl = startPoint
                            splitSequence.forEach [ sequenceIdentifier |
                                journeyObjects.forEach [

                                    val uniqueRunIdentifier = groupRunIdentifier + "_" + testCase
                                    if (sequenceIdentifier.equals(jtcBddIdentifier)) {
                                        if (jtcAction == "start" && jtcBddIdentifier == startUrl) {
                                            
                                            val inputCheckURLKey = "appStartPoint." + jtcBddIdentifier + envName
                                            val inputCheckURL = ResourceBundle.getBundle("environments").getString(
                                                inputCheckURLKey);

                                            println("App URL Key:" + inputCheckURLKey)
                                            println("App URL:" + inputCheckURL)
                                            driver.get(inputCheckURL)
                                            if (recordVideo == "Y")
                                                startVideo(reportDirectory, uniqueRunIdentifier, videoPause)
                                        }
                                        val journeyCaseStartUrl = driver.getCurrentUrl
                                        //println("Attempting a wait for document ready state for :" + journeyCaseStartUrl)
                                        val journeyDocumentReadyState = getDocumentReadyState(driver)
                                        //println("Document Ready State (needs to be 'complete' to continue) " + journeyDocumentReadyState)
                                        if (journeyDocumentReadyState == "complete") {
                                            if (mapSurface == "Y")
                                                getSurface(driver, journeyCaseStartUrl, jtcBddIdentifier,
                                                    reportDirectory, uniqueRunIdentifier)
                                            else
                                                checkSurface(driver, journeyCaseStartUrl, jtcBddIdentifier,
                                                    reportDirectory, uniqueRunIdentifier)
                                            print("  |-- " + jtcComments)

                                            switch jtcAction {
                                                case ( jtcAction == "wait") :
                                                    waitForElement(jtcByElement, jtcNameElement, driver)
                                                case ( jtcAction == "click") :
                                                    doUserAction(runAccessibility, driver.getCurrentUrl,
                                                        accessibilityPause, driver, uniqueRunIdentifier,
                                                        uniqueRunIdentifier + "_TC_" + jtcBddIdentifier,
                                                        reportDirectory, jtcInputCheck, jtcAction,
                                                        fetchElement(jtcByElement, jtcNameElement, driver))
                                                case ( jtcAction == "standardKeys") :
                                                    doUserAction(runAccessibility, driver.getCurrentUrl,
                                                        accessibilityPause, driver, uniqueRunIdentifier,
                                                        uniqueRunIdentifier + "_TC_" + jtcBddIdentifier,
                                                        reportDirectory, jtcInputCheck, jtcAction,
                                                        fetchElement(jtcByElement, jtcNameElement, driver))
                                                case ( jtcAction == "specialKeys") :
                                                    doUserAction(runAccessibility, driver.getCurrentUrl,
                                                        accessibilityPause, driver, uniqueRunIdentifier,
                                                        uniqueRunIdentifier + "_TC_" + jtcBddIdentifier,
                                                        reportDirectory, jtcInputCheck, jtcAction,
                                                        fetchElement(jtcByElement, jtcNameElement, driver))
                                                case ( jtcAction == "checkContent"):
                                                    doNothing() // patternMatch(jtcByElement, jtcNameElement, driver, jtcInputCheck)
                                                case ( jtcAction == "checkContentEnabled"):
                                                    patternMatch(jtcByElement, jtcNameElement, driver, jtcInputCheck)
                                                case ( jtcAction == "checkUrl") :
                                                    driver.getCurrentUrl.contains(jtcInputCheck)
                                                case ( jtcAction == "switch") :
                                                    if (jtcNameElement != "defaultContent")
                                                        driver.switchTo.frame(jtcNameElement)
                                                    else
                                                        driver.switchTo.defaultContent
                                                case ( jtcAction == "url") :
                                                    doUrlNavigation(runAccessibility, driver.getCurrentUrl,
                                                        accessibilityPause, driver, uniqueRunIdentifier,
                                                        uniqueRunIdentifier + "_TC_" + jtcBddIdentifier,
                                                        reportDirectory, jtcAction, jtcInputCheck)
                                                case ( jtcAction == "hover") :
                                                    doHover(jtcByElement, jtcNameElement, driver)
                                                case ( jtcAction == "clear") :
                                                    doUserAction(runAccessibility, driver.getCurrentUrl,
                                                        accessibilityPause, driver, uniqueRunIdentifier,
                                                        uniqueRunIdentifier + "_TC_" + jtcBddIdentifier,
                                                        reportDirectory, jtcInputCheck, jtcAction,
                                                        fetchElement(jtcByElement, jtcNameElement, driver))
                                                case ( jtcAction == "maximizeBrowser") :
                                                    doUserAction(runAccessibility, driver.getCurrentUrl,
                                                        accessibilityPause, driver, uniqueRunIdentifier,
                                                        uniqueRunIdentifier + "_TC_" + jtcBddIdentifier,
                                                        reportDirectory, jtcInputCheck, jtcAction,
                                                        fetchElement(jtcByElement, jtcNameElement, driver))
                                            }
                                            println(" STEP COMPLETE")
                                            Thread.sleep(jtcPause)
                                        }
                                    }
                                ]
                            ]
                        } catch (Exception e) {
                            this.suiteSuccessful = false
                            println(" STEP FAILED")
                            println("**** " + testCase + " TEST FAILED ****")
                        }
                    }
                ]
            ]
            if(recordVideo == "Y") stopVideo(videoPause)
            driver.quit

        }

        def doNothing() { print(" **DISABLED** ") }

        def patternMatch(String jtcByElement, String jtcNameElement, RemoteWebDriver driver, String jtcInputCheck) {
            if (fetchElement(jtcByElement, jtcNameElement, driver).getText.contains(jtcInputCheck) == true) {
//    print("     |-- SUCCESS" + "\n")
            } else {
                print("     |-- FAILURE" + "\n")
                System.exit(0)
            }
        }

        def doHover(String jtcByElement, String jtcNameElement, RemoteWebDriver driver) {
            val hover = fetchElement(jtcByElement, jtcNameElement, driver)
            val JavascriptExecutor executor = driver
            executor.executeScript("arguments[0].scrollIntoView(true);", hover);
            val hoverAction = new Actions(driver)
            hoverAction.moveToElement(hover).build.perform
        }

        def getSurface(RemoteWebDriver driver, String testCaseUrl, String testCaseIdentifier, String reportDirectory,
            String testRunIdentifier) {
            val String[] sections = testRunIdentifier.split("-")
            val testIdentifier = sections.get(0)
            val currentInputs = driver.findElements(By.tagName("input"))
            currentInputs.forEach [ currentInput |
                surfaceAuditBaseline(testCaseUrl, testCaseIdentifier, currentInput.getAttribute("id"), testIdentifier,
                    reportDirectory)
            ]
        }

        def checkSurface(RemoteWebDriver driver, String testCaseUrl, String testCaseIdentifier, String reportDirectory,
            String testRunIdentifier) {
            val String[] sections = testRunIdentifier.split("-")
            val testIdentifier = sections.get(0)
            val currentInputs = driver.findElements(By.tagName("input"))
            currentInputs.forEach [ currentInput |
                surfaceAuditCurrent(testCaseUrl, testCaseIdentifier, currentInput.getAttribute("id"), reportDirectory,
                    testRunIdentifier)
            ]
            surfaceAuditCompare(testCaseIdentifier, reportDirectory, testRunIdentifier, testIdentifier)
        }

        extension auditObject = new auditObject
        extension journeyObject = new journeyObject
        extension caseObject = new caseObject

    }