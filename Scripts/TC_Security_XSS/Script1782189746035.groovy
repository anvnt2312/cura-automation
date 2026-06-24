import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

try { DriverFactory.getWebDriver() } catch (Exception e) {
	WebUI.openBrowser(''); WebUI.maximizeWindow()
}

// [Security] XSS - chèn script vào username
CustomKeywords.'com.cura.pages.LoginPage.navigateToLoginPage'()
CustomKeywords.'com.cura.pages.LoginPage.enterUsername'('<script>alert(1)</script>')
CustomKeywords.'com.cura.pages.LoginPage.enterPassword'('ThisIsNotAPassword')
CustomKeywords.'com.cura.pages.LoginPage.clickLogin'()

// Verify XSS bị chặn (login từ chối, không có alert popup)
CustomKeywords.'com.cura.keywords.VerifyKeywords.verifyLoginRejected'('XSS Script Injection')
WebUI.comment('[Security] XSS bị chặn — script không thực thi, login từ chối')