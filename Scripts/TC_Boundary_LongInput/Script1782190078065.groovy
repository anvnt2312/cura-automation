import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

try { DriverFactory.getWebDriver() } catch (Exception e) {
	WebUI.openBrowser(''); WebUI.maximizeWindow()
}

// [Boundary] Username cực dài (255 ký tự)
String longUsername = 'A' * 255

CustomKeywords.'com.cura.pages.LoginPage.navigateToLoginPage'()
CustomKeywords.'com.cura.pages.LoginPage.enterUsername'(longUsername)
CustomKeywords.'com.cura.pages.LoginPage.enterPassword'('ThisIsNotAPassword')
CustomKeywords.'com.cura.pages.LoginPage.clickLogin'()

// Verify login thất bại (username dài không hợp lệ)
CustomKeywords.'com.cura.keywords.VerifyKeywords.verifyLoginRejected'('Boundary Long Username 255')
WebUI.comment('[Boundary] Username 255 ký tự → login từ chối đúng')