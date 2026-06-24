import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

try { DriverFactory.getWebDriver() } catch (Exception e) {
	WebUI.openBrowser(''); WebUI.maximizeWindow()
}

// [Functional] Username có khoảng trắng đầu/cuối
CustomKeywords.'com.cura.pages.LoginPage.navigateToLoginPage'()
CustomKeywords.'com.cura.pages.LoginPage.enterUsername'('  John Doe  ')
CustomKeywords.'com.cura.pages.LoginPage.enterPassword'('ThisIsNotAPassword')
CustomKeywords.'com.cura.pages.LoginPage.clickLogin'()

// Verify login thất bại (CURA không trim → username có space là sai)
CustomKeywords.'com.cura.keywords.VerifyKeywords.verifyLoginRejected'('Username Trailing Spaces')
WebUI.comment('[Functional] Username có khoảng trắng thừa → login từ chối (CURA không trim)')