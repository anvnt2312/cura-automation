import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

try { DriverFactory.getWebDriver() } catch (Exception e) {
	WebUI.openBrowser(''); WebUI.maximizeWindow()
}

// [Edge Case] Username trống, password hợp lệ
CustomKeywords.'com.cura.pages.LoginPage.navigateToLoginPage'()
CustomKeywords.'com.cura.pages.LoginPage.enterUsername'('')
CustomKeywords.'com.cura.pages.LoginPage.enterPassword'('ThisIsNotAPassword')
CustomKeywords.'com.cura.pages.LoginPage.clickLogin'()

// Verify login thất bại
CustomKeywords.'com.cura.keywords.VerifyKeywords.verifyElementPresentWithScreenshot'(findTestObject('Page_CURA Healthcare Service/p_login_error'), 20)
WebUI.comment('[Edge Case] Username trống → login bị từ chối đúng')