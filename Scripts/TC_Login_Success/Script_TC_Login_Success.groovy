import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

try { DriverFactory.getWebDriver() } catch (Exception e) {
	WebUI.openBrowser(''); WebUI.maximizeWindow()
}

// In ra user theo profile (đặt NGOÀI catch để luôn chạy)
WebUI.comment("Đang chạy với user: " + GlobalVariable.validUser)

// Login
CustomKeywords.'com.cura.keywords.LoginKeywords.performLogin'('John Doe', 'ThisIsNotAPassword')

// Verify + tự chụp screenshot theo công tắc
CustomKeywords.'com.cura.keywords.VerifyKeywords.verifyElementPresentWithScreenshot'(findTestObject('Page_CURA Healthcare Service/button_btn-book-appointment'), 20)