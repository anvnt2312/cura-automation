import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

try { DriverFactory.getWebDriver() } catch (Exception e) {
	WebUI.openBrowser(''); WebUI.maximizeWindow()
}

// [Functional] Username đúng, password đúng nội dung nhưng SAI HOA/THƯỜNG
CustomKeywords.'com.cura.pages.LoginPage.navigateToLoginPage'()
CustomKeywords.'com.cura.pages.LoginPage.enterUsername'('John Doe')
CustomKeywords.'com.cura.pages.LoginPage.enterPassword'('THISISNOTAPASSWORD')
CustomKeywords.'com.cura.pages.LoginPage.clickLogin'()

// Verify login thất bại (password phân biệt hoa thường)
CustomKeywords.'com.cura.keywords.VerifyKeywords.verifyLoginRejected'('Password Case Sensitivity')
WebUI.comment('[Functional] Password phân biệt hoa thường — sai case bị từ chối đúng')