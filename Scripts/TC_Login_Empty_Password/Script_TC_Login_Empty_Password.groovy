import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

try { DriverFactory.getWebDriver() } catch (Exception e) {
	WebUI.openBrowser(''); WebUI.maximizeWindow()
}

// [Edge Case] Username hợp lệ, password trống
CustomKeywords.'com.cura.pages.LoginPage.navigateToLoginPage'()
CustomKeywords.'com.cura.pages.LoginPage.enterUsername'('John Doe')
CustomKeywords.'com.cura.pages.LoginPage.enterPassword'('')
CustomKeywords.'com.cura.pages.LoginPage.clickLogin'()

CustomKeywords.'com.cura.keywords.VerifyKeywords.verifyElementPresentWithScreenshot'(findTestObject('Page_CURA Healthcare Service/p_login_error'), 20)
WebUI.comment('[Edge Case] Password trống → login bị từ chối đúng')