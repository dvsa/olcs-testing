package olcs;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import olcs.auditObject;
import olcs.caseObject;
import olcs.cases;
import olcs.journey;
import olcs.journeyObject;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@SuppressWarnings("all")
public class seleniumObject {
  public RemoteWebDriver startDriver() {
    try {
      DesiredCapabilities _internetExplorer = DesiredCapabilities.internetExplorer();
      Pair<Integer,DesiredCapabilities> _mappedTo = Pair.<Integer, DesiredCapabilities>of(Integer.valueOf(1), _internetExplorer);
      DesiredCapabilities _firefox = DesiredCapabilities.firefox();
      Pair<Integer,DesiredCapabilities> _mappedTo_1 = Pair.<Integer, DesiredCapabilities>of(Integer.valueOf(2), _firefox);
      DesiredCapabilities _phantomjs = DesiredCapabilities.phantomjs();
      Pair<Integer,DesiredCapabilities> _mappedTo_2 = Pair.<Integer, DesiredCapabilities>of(Integer.valueOf(3), _phantomjs);
      DesiredCapabilities _chrome = DesiredCapabilities.chrome();
      Pair<Integer,DesiredCapabilities> _mappedTo_3 = Pair.<Integer, DesiredCapabilities>of(Integer.valueOf(4), _chrome);
      final HashMap<Integer,DesiredCapabilities> capabilitiesMap = CollectionLiterals.<Integer, DesiredCapabilities>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2, _mappedTo_3);
      String envName = System.getProperty("env");
      boolean _equals = Objects.equal(null, envName);
      if (_equals) {
        envName = "dev";
      }
      final String environment = envName;
      String browserIndex = System.getProperty("browser");
      boolean _equals_1 = Objects.equal(null, browserIndex);
      if (_equals_1) {
        browserIndex = "2";
      }
      final int browser = Integer.parseInt(browserIndex);
      final ResourceBundle rb = ResourceBundle.getBundle("environments");
      System.out.println(capabilitiesMap);
      System.out.println(("Environment running in is " + envName));
      String _string = rb.getString(("hub." + envName));
      String _plus = ("Hub " + _string);
      System.out.println(_plus);
      DesiredCapabilities _get = capabilitiesMap.get(Integer.valueOf(browser));
      String _plus_1 = ("Browser " + _get);
      System.out.println(_plus_1);
      String _string_1 = rb.getString(("hub." + envName));
      URL _uRL = new URL(_string_1);
      DesiredCapabilities _get_1 = capabilitiesMap.get(Integer.valueOf(browser));
      final RemoteWebDriver driverResult = new RemoteWebDriver(_uRL, _get_1);
      return driverResult;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public WebElement fetchElement(final String tcByElement, final String tcNameElement, final RemoteWebDriver driver) {
    WebElement _switchResult = null;
    boolean _matched = false;
    if (!_matched) {
      boolean _equals = Objects.equal(tcByElement, "linkText");
      if (_equals) {
        _matched=true;
        By _linkText = By.linkText(tcNameElement);
        _switchResult = driver.findElement(_linkText);
      }
    }
    if (!_matched) {
      boolean _equals_1 = Objects.equal(tcByElement, "id");
      if (_equals_1) {
        _matched=true;
        By _id = By.id(tcNameElement);
        _switchResult = driver.findElement(_id);
      }
    }
    if (!_matched) {
      boolean _equals_2 = Objects.equal(tcByElement, "tagName");
      if (_equals_2) {
        _matched=true;
        By _tagName = By.tagName(tcNameElement);
        _switchResult = driver.findElement(_tagName);
      }
    }
    if (!_matched) {
      boolean _equals_3 = Objects.equal(tcByElement, "partialLinkText");
      if (_equals_3) {
        _matched=true;
        By _partialLinkText = By.partialLinkText(tcNameElement);
        _switchResult = driver.findElement(_partialLinkText);
      }
    }
    if (!_matched) {
      boolean _equals_4 = Objects.equal(tcByElement, "className");
      if (_equals_4) {
        _matched=true;
        By _className = By.className(tcNameElement);
        _switchResult = driver.findElement(_className);
      }
    }
    if (!_matched) {
      boolean _equals_5 = Objects.equal(tcByElement, "xpathHref");
      if (_equals_5) {
        _matched=true;
        By _xpath = By.xpath((("//a[@href = \'" + tcNameElement) + "\']"));
        _switchResult = driver.findElement(_xpath);
      }
    }
    if (!_matched) {
      boolean _equals_6 = Objects.equal(tcByElement, "xpathLinkContains");
      if (_equals_6) {
        _matched=true;
        By _xpath_1 = By.xpath((("//a[contains(text(), \'" + tcNameElement) + "\')]"));
        _switchResult = driver.findElement(_xpath_1);
      }
    }
    if (!_matched) {
      boolean _equals_7 = Objects.equal(tcByElement, "cssSelector");
      if (_equals_7) {
        _matched=true;
        By _cssSelector = By.cssSelector((tcNameElement + " > ul.multiselect > li > div > a"));
        _switchResult = driver.findElement(_cssSelector);
      }
    }
    if (!_matched) {
      boolean _equals_8 = Objects.equal(tcByElement, "xpathLabelContains");
      if (_equals_8) {
        _matched=true;
        By _xpath_2 = By.xpath((("//label[contains(.,\'" + tcNameElement) + "\')]/input"));
        _switchResult = driver.findElement(_xpath_2);
      }
    }
    if (!_matched) {
      boolean _equals_9 = Objects.equal(tcByElement, "xpathValue");
      if (_equals_9) {
        _matched=true;
        By _xpath_3 = By.xpath((("//input[@value=\'" + tcNameElement) + "\']"));
        _switchResult = driver.findElement(_xpath_3);
      }
    }
    if (!_matched) {
      boolean _equals_10 = Objects.equal(tcByElement, "inputName");
      if (_equals_10) {
        _matched=true;
        By _name = By.name(tcNameElement);
        _switchResult = driver.findElement(_name);
      }
    }
    if (!_matched) {
      boolean _equals_11 = Objects.equal(tcByElement, "xpath");
      if (_equals_11) {
        _matched=true;
        By _xpath_4 = By.xpath(tcNameElement);
        _switchResult = driver.findElement(_xpath_4);
      }
    }
    if (!_matched) {
      _switchResult = null;
    }
    final WebElement resultElement = _switchResult;
    return resultElement;
  }
  
  public List<WebElement> fetchElements(final String tcByElement, final String tcNameElement, final RemoteWebDriver driver) {
    List<WebElement> _switchResult = null;
    boolean _matched = false;
    if (!_matched) {
      boolean _equals = Objects.equal(tcByElement, "className");
      if (_equals) {
        _matched=true;
        By _className = By.className(tcNameElement);
        _switchResult = driver.findElements(_className);
      }
    }
    if (!_matched) {
      _switchResult = null;
    }
    final List<WebElement> resultElements = _switchResult;
    return resultElements;
  }
  
  public Object waitForElement(final String tcByElement, final String tcNameElement, final RemoteWebDriver driver) {
    Object _xblockexpression = null;
    {
      final WebDriverWait wait = new WebDriverWait(driver, 30);
      Object _switchResult = null;
      boolean _matched = false;
      if (!_matched) {
        boolean _equals = Objects.equal(tcByElement, "linkText");
        if (_equals) {
          _matched=true;
          final Function<WebDriver,WebElement> _function = new Function<WebDriver,WebElement>() {
            public WebElement apply(final WebDriver it) {
              By _linkText = By.linkText(tcNameElement);
              return it.findElement(_linkText);
            }
          };
          WebElement _until = wait.<WebElement>until(_function);
          _switchResult = Boolean.valueOf(_until.isDisplayed());
        }
      }
      if (!_matched) {
        boolean _equals_1 = Objects.equal(tcByElement, "id");
        if (_equals_1) {
          _matched=true;
          final Function<WebDriver,WebElement> _function_1 = new Function<WebDriver,WebElement>() {
            public WebElement apply(final WebDriver it) {
              By _id = By.id(tcNameElement);
              return it.findElement(_id);
            }
          };
          WebElement _until_1 = wait.<WebElement>until(_function_1);
          _switchResult = Boolean.valueOf(_until_1.isDisplayed());
        }
      }
      if (!_matched) {
        boolean _equals_2 = Objects.equal(tcByElement, "tagName");
        if (_equals_2) {
          _matched=true;
          final Function<WebDriver,WebElement> _function_2 = new Function<WebDriver,WebElement>() {
            public WebElement apply(final WebDriver it) {
              By _tagName = By.tagName(tcNameElement);
              return it.findElement(_tagName);
            }
          };
          WebElement _until_2 = wait.<WebElement>until(_function_2);
          _switchResult = Boolean.valueOf(_until_2.isDisplayed());
        }
      }
      if (!_matched) {
        boolean _equals_3 = Objects.equal(tcByElement, "partialLinkText");
        if (_equals_3) {
          _matched=true;
          final Function<WebDriver,WebElement> _function_3 = new Function<WebDriver,WebElement>() {
            public WebElement apply(final WebDriver it) {
              By _partialLinkText = By.partialLinkText(tcNameElement);
              return it.findElement(_partialLinkText);
            }
          };
          WebElement _until_3 = wait.<WebElement>until(_function_3);
          _switchResult = Boolean.valueOf(_until_3.isDisplayed());
        }
      }
      if (!_matched) {
        boolean _equals_4 = Objects.equal(tcByElement, "className");
        if (_equals_4) {
          _matched=true;
          final Function<WebDriver,WebElement> _function_4 = new Function<WebDriver,WebElement>() {
            public WebElement apply(final WebDriver it) {
              By _className = By.className(tcNameElement);
              return it.findElement(_className);
            }
          };
          WebElement _until_4 = wait.<WebElement>until(_function_4);
          _switchResult = Boolean.valueOf(_until_4.isDisplayed());
        }
      }
      if (!_matched) {
        boolean _equals_5 = Objects.equal(tcByElement, "xpathHref");
        if (_equals_5) {
          _matched=true;
          final Function<WebDriver,WebElement> _function_5 = new Function<WebDriver,WebElement>() {
            public WebElement apply(final WebDriver it) {
              By _xpath = By.xpath((("//a[@href = \'" + tcNameElement) + "\']"));
              return it.findElement(_xpath);
            }
          };
          WebElement _until_5 = wait.<WebElement>until(_function_5);
          _switchResult = Boolean.valueOf(_until_5.isDisplayed());
        }
      }
      if (!_matched) {
        boolean _equals_6 = Objects.equal(tcByElement, "xpathLinkContains");
        if (_equals_6) {
          _matched=true;
          final Function<WebDriver,WebElement> _function_6 = new Function<WebDriver,WebElement>() {
            public WebElement apply(final WebDriver it) {
              By _xpath = By.xpath((("//a[contains(text(), \'" + tcNameElement) + "\')]"));
              return it.findElement(_xpath);
            }
          };
          WebElement _until_6 = wait.<WebElement>until(_function_6);
          _switchResult = Boolean.valueOf(_until_6.isDisplayed());
        }
      }
      if (!_matched) {
        boolean _equals_7 = Objects.equal(tcByElement, "cssSelector");
        if (_equals_7) {
          _matched=true;
          final Function<WebDriver,WebElement> _function_7 = new Function<WebDriver,WebElement>() {
            public WebElement apply(final WebDriver it) {
              By _cssSelector = By.cssSelector((tcNameElement + " > ul.multiselect > li > div > a"));
              return it.findElement(_cssSelector);
            }
          };
          WebElement _until_7 = wait.<WebElement>until(_function_7);
          _switchResult = Boolean.valueOf(_until_7.isDisplayed());
        }
      }
      if (!_matched) {
        boolean _equals_8 = Objects.equal(tcByElement, "xpathLabelContains");
        if (_equals_8) {
          _matched=true;
          final Function<WebDriver,WebElement> _function_8 = new Function<WebDriver,WebElement>() {
            public WebElement apply(final WebDriver it) {
              By _xpath = By.xpath((("//label[contains(.,\'" + tcNameElement) + "\')]/input"));
              return it.findElement(_xpath);
            }
          };
          WebElement _until_8 = wait.<WebElement>until(_function_8);
          _switchResult = Boolean.valueOf(_until_8.isDisplayed());
        }
      }
      if (!_matched) {
        boolean _equals_9 = Objects.equal(tcByElement, "xpathValue");
        if (_equals_9) {
          _matched=true;
          By _xpath = By.xpath((("//input[@value=\'" + tcNameElement) + "\']"));
          _switchResult = driver.findElement(_xpath);
        }
      }
      if (!_matched) {
        boolean _equals_10 = Objects.equal(tcByElement, "inputName");
        if (_equals_10) {
          _matched=true;
          By _name = By.name(tcNameElement);
          WebElement _findElement = driver.findElement(_name);
          _switchResult = Boolean.valueOf(_findElement.isDisplayed());
        }
      }
      if (!_matched) {
        boolean _equals_11 = Objects.equal(tcByElement, "xpath");
        if (_equals_11) {
          _matched=true;
          By _xpath_1 = By.xpath(tcNameElement);
          WebElement _findElement_1 = driver.findElement(_xpath_1);
          _switchResult = Boolean.valueOf(_findElement_1.isDisplayed());
        }
      }
      _xblockexpression = _switchResult;
    }
    return _xblockexpression;
  }
  
  public void doUserAction(final String accessibilityPluginEnabled, final String currentUrl, final int accessibilityPause, final RemoteWebDriver driver, final String testRunIdentifier, final String testRunIdentifierTimestamp, final String reportDirectory, final String input, final String action, final WebElement execute) {
    boolean _equals = Objects.equal(accessibilityPluginEnabled, "Y");
    if (_equals) {
      this.loadAccessibilityPlugin(currentUrl, accessibilityPause, driver, testRunIdentifier, testRunIdentifierTimestamp, reportDirectory);
    }
    boolean _matched = false;
    if (!_matched) {
      boolean _equals_1 = Objects.equal(action, "click");
      if (_equals_1) {
        _matched=true;
        execute.click();
      }
    }
    if (!_matched) {
      boolean _equals_2 = Objects.equal(action, "standardKeys");
      if (_equals_2) {
        _matched=true;
        execute.sendKeys(input);
      }
    }
    if (!_matched) {
      boolean _equals_3 = Objects.equal(action, "specialKeys");
      if (_equals_3) {
        _matched=true;
        Keys _valueOf = Keys.valueOf(input);
        execute.sendKeys(_valueOf);
      }
    }
    if (!_matched) {
      boolean _equals_4 = Objects.equal(action, "clear");
      if (_equals_4) {
        _matched=true;
        execute.clear();
      }
    }
    boolean _equals_5 = Objects.equal(accessibilityPluginEnabled, "Y");
    if (_equals_5) {
      this.unloadAccessibilityPlugin(driver);
    }
  }
  
  public void doUrlNavigation(final String accessibilityPluginEnabled, final String currentUrl, final int accessibilityPause, final RemoteWebDriver driver, final String testRunIdentifier, final String testRunIdentifierTimestamp, final String reportDirectory, final String action, final String navigateUrl) {
    boolean _equals = Objects.equal(accessibilityPluginEnabled, "Y");
    if (_equals) {
      this.loadAccessibilityPlugin(currentUrl, accessibilityPause, driver, testRunIdentifier, testRunIdentifierTimestamp, reportDirectory);
    }
    WebDriver.Navigation _navigate = driver.navigate();
    _navigate.to(navigateUrl);
    boolean _equals_1 = Objects.equal(accessibilityPluginEnabled, "Y");
    if (_equals_1) {
      this.loadAccessibilityPlugin(currentUrl, accessibilityPause, driver, testRunIdentifier, testRunIdentifierTimestamp, reportDirectory);
    }
  }
  
  public Object getDocumentReadyState(final RemoteWebDriver driver) {
    final JavascriptExecutor executor = driver;
    final Object documentState = executor.executeScript("return document.readyState");
    return documentState;
  }
  
  public void loadAccessibilityPlugin(final String testCaseUrl, final int accessibilityPause, final RemoteWebDriver driver, final String testRunIdentifier, final String testRunIdentifierTimestamp, final String reportDirectory) {
    final String pluginIdentifier = "Accessibility";
    final Actions accessibilityAction = new Actions(driver);
    this.waitForElement("tagName", "body", driver);
    final WebElement accessibilityStream = this.fetchElement("tagName", "body", driver);
    Actions _contextClick = accessibilityAction.contextClick(accessibilityStream);
    Actions _sendKeys = _contextClick.sendKeys(Keys.ARROW_UP);
    Actions _sendKeys_1 = _sendKeys.sendKeys(Keys.ARROW_RIGHT);
    Actions _sendKeys_2 = _sendKeys_1.sendKeys(Keys.ENTER);
    _sendKeys_2.perform();
    this.waitForElement("className", "wave4tip", driver);
    final List<WebElement> notifications = this.fetchElements("className", "wave4tip", driver);
    final Procedure1<WebElement> _function = new Procedure1<WebElement>() {
      public void apply(final WebElement notification) {
        try {
          String _attribute = notification.getAttribute("alt");
          boolean _startsWith = _attribute.startsWith("ERROR: ");
          if (_startsWith) {
            final JavascriptExecutor executor = driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", notification);
            final Actions accessibilityAlertAction = new Actions(driver);
            Actions _moveToElement = accessibilityAlertAction.moveToElement(notification);
            _moveToElement.perform();
            Thread.sleep(accessibilityPause);
            String _attribute_1 = notification.getAttribute("alt");
            seleniumObject.this._auditObject.accessibilityAuditFile(pluginIdentifier, testCaseUrl, testRunIdentifier, testRunIdentifierTimestamp, _attribute_1, reportDirectory);
          }
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      }
    };
    IterableExtensions.<WebElement>forEach(notifications, _function);
  }
  
  public void unloadAccessibilityPlugin(final RemoteWebDriver driver) {
    final Actions accessibilityAction = new Actions(driver);
    this.waitForElement("tagName", "body", driver);
    final WebElement accessibilityStream = this.fetchElement("tagName", "body", driver);
    Actions _contextClick = accessibilityAction.contextClick(accessibilityStream);
    Actions _sendKeys = _contextClick.sendKeys(Keys.ARROW_UP);
    Actions _sendKeys_1 = _sendKeys.sendKeys(Keys.ARROW_RIGHT);
    Actions _sendKeys_2 = _sendKeys_1.sendKeys(Keys.ARROW_DOWN);
    Actions _sendKeys_3 = _sendKeys_2.sendKeys(Keys.ARROW_DOWN);
    Actions _sendKeys_4 = _sendKeys_3.sendKeys(Keys.ARROW_DOWN);
    Actions _sendKeys_5 = _sendKeys_4.sendKeys(Keys.ARROW_DOWN);
    Actions _sendKeys_6 = _sendKeys_5.sendKeys(Keys.ENTER);
    _sendKeys_6.perform();
  }
  
  public void executeTestSuite(final String testCases, final String mapSurface, final String recordVideo, final int videoPause, final String runSecurity, final String runAccessibility, final int accessibilityPause, final String groupRunIdentifier, final String reportDirectory) {
    final String[] splitCases = testCases.split(",");
    final RemoteWebDriver driver = this.startDriver();
    final Procedure1<String> _function = new Procedure1<String>() {
      public void apply(final String testCase) {
        final Procedure1<cases> _function = new Procedure1<cases>() {
          public void apply(final cases it) {
            String _bddIdentifier = it.getBddIdentifier();
            boolean _equals = testCase.equals(_bddIdentifier);
            if (_equals) {
              InputOutput.<String>print((("-- " + testCase) + "\n"));
              String _bddSequence = it.getBddSequence();
              final String[] splitSequence = _bddSequence.split(",");
              final String startUrl = it.getStartPoint();
              final Procedure1<String> _function = new Procedure1<String>() {
                public void apply(final String sequenceIdentifier) {
                  final Procedure1<journey> _function = new Procedure1<journey>() {
                    public void apply(final journey it) {
                      try {
                        final String uniqueRunIdentifier = ((groupRunIdentifier + "_") + testCase);
                        String _jtcBddIdentifier = it.getJtcBddIdentifier();
                        boolean _equals = sequenceIdentifier.equals(_jtcBddIdentifier);
                        if (_equals) {
                          boolean _and = false;
                          String _jtcAction = it.getJtcAction();
                          boolean _equals_1 = Objects.equal(_jtcAction, "start");
                          if (!_equals_1) {
                            _and = false;
                          } else {
                            String _jtcBddIdentifier_1 = it.getJtcBddIdentifier();
                            boolean _equals_2 = Objects.equal(_jtcBddIdentifier_1, startUrl);
                            _and = _equals_2;
                          }
                          if (_and) {
                            String env = System.getProperty("env");
                            boolean _equals_3 = Objects.equal(null, env);
                            if (_equals_3) {
                              env = "dev";
                            }
                            String _jtcBddIdentifier_2 = it.getJtcBddIdentifier();
                            String _plus = ("appStartPoint." + _jtcBddIdentifier_2);
                            final String inputCheckURLKey = (_plus + env);
                            ResourceBundle _bundle = ResourceBundle.getBundle("environments");
                            final String inputCheckURL = _bundle.getString(inputCheckURLKey);
                            driver.get(inputCheckURL);
                            boolean _equals_4 = Objects.equal(recordVideo, "Y");
                            if (_equals_4) {
                              seleniumObject.this._auditObject.startVideo(reportDirectory, uniqueRunIdentifier, videoPause);
                            }
                          }
                          final String journeyCaseStartUrl = driver.getCurrentUrl();
                          final Object journeyDocumentReadyState = seleniumObject.this.getDocumentReadyState(driver);
                          boolean _equals_5 = Objects.equal(journeyDocumentReadyState, "complete");
                          if (_equals_5) {
                            boolean _equals_6 = Objects.equal(mapSurface, "Y");
                            if (_equals_6) {
                              String _jtcBddIdentifier_3 = it.getJtcBddIdentifier();
                              seleniumObject.this.getSurface(driver, journeyCaseStartUrl, _jtcBddIdentifier_3, reportDirectory, uniqueRunIdentifier);
                            } else {
                              String _jtcBddIdentifier_4 = it.getJtcBddIdentifier();
                              seleniumObject.this.checkSurface(driver, journeyCaseStartUrl, _jtcBddIdentifier_4, reportDirectory, uniqueRunIdentifier);
                            }
                            String _jtcComments = it.getJtcComments();
                            String _plus_1 = ("  |-- " + _jtcComments);
                            String _plus_2 = (_plus_1 + "\n");
                            InputOutput.<String>print(_plus_2);
                            String _jtcAction_1 = it.getJtcAction();
                            boolean _matched = false;
                            if (!_matched) {
                              String _jtcAction_2 = it.getJtcAction();
                              boolean _equals_7 = Objects.equal(_jtcAction_2, "wait");
                              if (_equals_7) {
                                _matched=true;
                                String _jtcByElement = it.getJtcByElement();
                                String _jtcNameElement = it.getJtcNameElement();
                                seleniumObject.this.waitForElement(_jtcByElement, _jtcNameElement, driver);
                              }
                            }
                            if (!_matched) {
                              String _jtcAction_3 = it.getJtcAction();
                              boolean _equals_8 = Objects.equal(_jtcAction_3, "click");
                              if (_equals_8) {
                                _matched=true;
                                String _currentUrl = driver.getCurrentUrl();
                                String _jtcBddIdentifier_5 = it.getJtcBddIdentifier();
                                String _plus_3 = ((uniqueRunIdentifier + "_TC_") + _jtcBddIdentifier_5);
                                String _jtcInputCheck = it.getJtcInputCheck();
                                String _jtcAction_4 = it.getJtcAction();
                                String _jtcByElement_1 = it.getJtcByElement();
                                String _jtcNameElement_1 = it.getJtcNameElement();
                                WebElement _fetchElement = seleniumObject.this.fetchElement(_jtcByElement_1, _jtcNameElement_1, driver);
                                seleniumObject.this.doUserAction(runAccessibility, _currentUrl, accessibilityPause, driver, uniqueRunIdentifier, _plus_3, reportDirectory, _jtcInputCheck, _jtcAction_4, _fetchElement);
                              }
                            }
                            if (!_matched) {
                              String _jtcAction_5 = it.getJtcAction();
                              boolean _equals_9 = Objects.equal(_jtcAction_5, "standardKeys");
                              if (_equals_9) {
                                _matched=true;
                                String _currentUrl_1 = driver.getCurrentUrl();
                                String _jtcBddIdentifier_6 = it.getJtcBddIdentifier();
                                String _plus_4 = ((uniqueRunIdentifier + "_TC_") + _jtcBddIdentifier_6);
                                String _jtcInputCheck_1 = it.getJtcInputCheck();
                                String _jtcAction_6 = it.getJtcAction();
                                String _jtcByElement_2 = it.getJtcByElement();
                                String _jtcNameElement_2 = it.getJtcNameElement();
                                WebElement _fetchElement_1 = seleniumObject.this.fetchElement(_jtcByElement_2, _jtcNameElement_2, driver);
                                seleniumObject.this.doUserAction(runAccessibility, _currentUrl_1, accessibilityPause, driver, uniqueRunIdentifier, _plus_4, reportDirectory, _jtcInputCheck_1, _jtcAction_6, _fetchElement_1);
                              }
                            }
                            if (!_matched) {
                              String _jtcAction_7 = it.getJtcAction();
                              boolean _equals_10 = Objects.equal(_jtcAction_7, "specialKeys");
                              if (_equals_10) {
                                _matched=true;
                                String _currentUrl_2 = driver.getCurrentUrl();
                                String _jtcBddIdentifier_7 = it.getJtcBddIdentifier();
                                String _plus_5 = ((uniqueRunIdentifier + "_TC_") + _jtcBddIdentifier_7);
                                String _jtcInputCheck_2 = it.getJtcInputCheck();
                                String _jtcAction_8 = it.getJtcAction();
                                String _jtcByElement_3 = it.getJtcByElement();
                                String _jtcNameElement_3 = it.getJtcNameElement();
                                WebElement _fetchElement_2 = seleniumObject.this.fetchElement(_jtcByElement_3, _jtcNameElement_3, driver);
                                seleniumObject.this.doUserAction(runAccessibility, _currentUrl_2, accessibilityPause, driver, uniqueRunIdentifier, _plus_5, reportDirectory, _jtcInputCheck_2, _jtcAction_8, _fetchElement_2);
                              }
                            }
                            if (!_matched) {
                              String _jtcAction_9 = it.getJtcAction();
                              boolean _equals_11 = Objects.equal(_jtcAction_9, "checkContent");
                              if (_equals_11) {
                                _matched=true;
                                String _jtcByElement_4 = it.getJtcByElement();
                                String _jtcNameElement_4 = it.getJtcNameElement();
                                String _jtcInputCheck_3 = it.getJtcInputCheck();
                                seleniumObject.this.patternMatch(_jtcByElement_4, _jtcNameElement_4, driver, _jtcInputCheck_3);
                              }
                            }
                            if (!_matched) {
                              String _jtcAction_10 = it.getJtcAction();
                              boolean _equals_12 = Objects.equal(_jtcAction_10, "checkUrl");
                              if (_equals_12) {
                                _matched=true;
                                String _currentUrl_3 = driver.getCurrentUrl();
                                String _jtcInputCheck_4 = it.getJtcInputCheck();
                                _currentUrl_3.contains(_jtcInputCheck_4);
                              }
                            }
                            if (!_matched) {
                              String _jtcAction_11 = it.getJtcAction();
                              boolean _equals_13 = Objects.equal(_jtcAction_11, "switch");
                              if (_equals_13) {
                                _matched=true;
                                String _jtcNameElement_5 = it.getJtcNameElement();
                                boolean _notEquals = (!Objects.equal(_jtcNameElement_5, "defaultContent"));
                                if (_notEquals) {
                                  WebDriver.TargetLocator _switchTo = driver.switchTo();
                                  String _jtcNameElement_6 = it.getJtcNameElement();
                                  _switchTo.frame(_jtcNameElement_6);
                                } else {
                                  WebDriver.TargetLocator _switchTo_1 = driver.switchTo();
                                  _switchTo_1.defaultContent();
                                }
                              }
                            }
                            if (!_matched) {
                              String _jtcAction_12 = it.getJtcAction();
                              boolean _equals_14 = Objects.equal(_jtcAction_12, "url");
                              if (_equals_14) {
                                _matched=true;
                                String _currentUrl_4 = driver.getCurrentUrl();
                                String _jtcBddIdentifier_8 = it.getJtcBddIdentifier();
                                String _plus_6 = ((uniqueRunIdentifier + "_TC_") + _jtcBddIdentifier_8);
                                String _jtcAction_13 = it.getJtcAction();
                                String _jtcInputCheck_5 = it.getJtcInputCheck();
                                seleniumObject.this.doUrlNavigation(runAccessibility, _currentUrl_4, accessibilityPause, driver, uniqueRunIdentifier, _plus_6, reportDirectory, _jtcAction_13, _jtcInputCheck_5);
                              }
                            }
                            if (!_matched) {
                              String _jtcAction_14 = it.getJtcAction();
                              boolean _equals_15 = Objects.equal(_jtcAction_14, "hover");
                              if (_equals_15) {
                                _matched=true;
                                String _jtcByElement_5 = it.getJtcByElement();
                                String _jtcNameElement_7 = it.getJtcNameElement();
                                seleniumObject.this.doHover(_jtcByElement_5, _jtcNameElement_7, driver);
                              }
                            }
                            if (!_matched) {
                              String _jtcAction_15 = it.getJtcAction();
                              boolean _equals_16 = Objects.equal(_jtcAction_15, "clear");
                              if (_equals_16) {
                                _matched=true;
                                String _currentUrl_5 = driver.getCurrentUrl();
                                String _jtcBddIdentifier_9 = it.getJtcBddIdentifier();
                                String _plus_7 = ((uniqueRunIdentifier + "_TC_") + _jtcBddIdentifier_9);
                                String _jtcInputCheck_6 = it.getJtcInputCheck();
                                String _jtcAction_16 = it.getJtcAction();
                                String _jtcByElement_6 = it.getJtcByElement();
                                String _jtcNameElement_8 = it.getJtcNameElement();
                                WebElement _fetchElement_3 = seleniumObject.this.fetchElement(_jtcByElement_6, _jtcNameElement_8, driver);
                                seleniumObject.this.doUserAction(runAccessibility, _currentUrl_5, accessibilityPause, driver, uniqueRunIdentifier, _plus_7, reportDirectory, _jtcInputCheck_6, _jtcAction_16, _fetchElement_3);
                              }
                            }
                            if (!_matched) {
                              String _jtcAction_17 = it.getJtcAction();
                              boolean _equals_17 = Objects.equal(_jtcAction_17, "maximizeBrowser");
                              if (_equals_17) {
                                _matched=true;
                                String _currentUrl_6 = driver.getCurrentUrl();
                                String _jtcBddIdentifier_10 = it.getJtcBddIdentifier();
                                String _plus_8 = ((uniqueRunIdentifier + "_TC_") + _jtcBddIdentifier_10);
                                String _jtcInputCheck_7 = it.getJtcInputCheck();
                                String _jtcAction_18 = it.getJtcAction();
                                String _jtcByElement_7 = it.getJtcByElement();
                                String _jtcNameElement_9 = it.getJtcNameElement();
                                WebElement _fetchElement_4 = seleniumObject.this.fetchElement(_jtcByElement_7, _jtcNameElement_9, driver);
                                seleniumObject.this.doUserAction(runAccessibility, _currentUrl_6, accessibilityPause, driver, uniqueRunIdentifier, _plus_8, reportDirectory, _jtcInputCheck_7, _jtcAction_18, _fetchElement_4);
                              }
                            }
                            int _jtcPause = it.getJtcPause();
                            Thread.sleep(_jtcPause);
                          }
                        }
                      } catch (Throwable _e) {
                        throw Exceptions.sneakyThrow(_e);
                      }
                    }
                  };
                  IterableExtensions.<journey>forEach(seleniumObject.this._journeyObject.journeyObjects, _function);
                }
              };
              IterableExtensions.<String>forEach(((Iterable<String>)Conversions.doWrapArray(splitSequence)), _function);
            }
          }
        };
        IterableExtensions.<cases>forEach(seleniumObject.this._caseObject.caseObjects, _function);
      }
    };
    IterableExtensions.<String>forEach(((Iterable<String>)Conversions.doWrapArray(splitCases)), _function);
    boolean _equals = Objects.equal(recordVideo, "Y");
    if (_equals) {
      this._auditObject.stopVideo(videoPause);
    }
    driver.quit();
  }
  
  public Object patternMatch(final String jtcByElement, final String jtcNameElement, final RemoteWebDriver driver, final String jtcInputCheck) {
    Object _xifexpression = null;
    WebElement _fetchElement = this.fetchElement(jtcByElement, jtcNameElement, driver);
    String _text = _fetchElement.getText();
    boolean _contains = _text.contains(jtcInputCheck);
    boolean _equals = (_contains == true);
    if (_equals) {
      _xifexpression = null;
    } else {
      InputOutput.<String>print(("     |-- FAILURE" + "\n"));
      System.exit(0);
    }
    return _xifexpression;
  }
  
  public void doHover(final String jtcByElement, final String jtcNameElement, final RemoteWebDriver driver) {
    final WebElement hover = this.fetchElement(jtcByElement, jtcNameElement, driver);
    final JavascriptExecutor executor = driver;
    executor.executeScript("arguments[0].scrollIntoView(true);", hover);
    final Actions hoverAction = new Actions(driver);
    Actions _moveToElement = hoverAction.moveToElement(hover);
    Action _build = _moveToElement.build();
    _build.perform();
  }
  
  public void getSurface(final RemoteWebDriver driver, final String testCaseUrl, final String testCaseIdentifier, final String reportDirectory, final String testRunIdentifier) {
    final String[] sections = testRunIdentifier.split("-");
    final String testIdentifier = sections[0];
    By _tagName = By.tagName("input");
    final List<WebElement> currentInputs = driver.findElements(_tagName);
    final Procedure1<WebElement> _function = new Procedure1<WebElement>() {
      public void apply(final WebElement currentInput) {
        String _attribute = currentInput.getAttribute("id");
        seleniumObject.this._auditObject.surfaceAuditBaseline(testCaseUrl, testCaseIdentifier, _attribute, testIdentifier, reportDirectory);
      }
    };
    IterableExtensions.<WebElement>forEach(currentInputs, _function);
  }
  
  public String checkSurface(final RemoteWebDriver driver, final String testCaseUrl, final String testCaseIdentifier, final String reportDirectory, final String testRunIdentifier) {
    String _xblockexpression = null;
    {
      final String[] sections = testRunIdentifier.split("-");
      final String testIdentifier = sections[0];
      By _tagName = By.tagName("input");
      final List<WebElement> currentInputs = driver.findElements(_tagName);
      final Procedure1<WebElement> _function = new Procedure1<WebElement>() {
        public void apply(final WebElement currentInput) {
          String _attribute = currentInput.getAttribute("id");
          seleniumObject.this._auditObject.surfaceAuditCurrent(testCaseUrl, testCaseIdentifier, _attribute, reportDirectory, testRunIdentifier);
        }
      };
      IterableExtensions.<WebElement>forEach(currentInputs, _function);
      _xblockexpression = this._auditObject.surfaceAuditCompare(testCaseIdentifier, reportDirectory, testRunIdentifier, testIdentifier);
    }
    return _xblockexpression;
  }
  
  @Extension
  private auditObject _auditObject = new auditObject();
  
  @Extension
  private journeyObject _journeyObject = new journeyObject();
  
  @Extension
  private caseObject _caseObject = new caseObject();
}
