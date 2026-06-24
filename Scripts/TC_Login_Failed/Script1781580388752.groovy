import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

try { DriverFactory.getWebDriver() } catch (Exception e) {
	WebUI.openBrowser(''); WebUI.maximizeWindow()
}

CustomKeywords.'com.cura.pages.LoginPage.navigateToLoginPage'()
CustomKeywords.'com.cura.pages.LoginPage.enterUsername'('John Doe')
CustomKeywords.'com.cura.pages.LoginPage.enterPassword'('WrongPassword123')
CustomKeywords.'com.cura.pages.LoginPage.clickLogin'()

CustomKeywords.'com.cura.keywords.VerifyKeywords.verifyElementPresentWithScreenshot'(findTestObject('Page_CURA Healthcare Service/p_login_error'), 20)