import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
try {
	DriverFactory.getWebDriver()
} catch (Exception e) {
	WebUI.openBrowser('')
	WebUI.maximizeWindow()
}

// [Security] Logout đầu để đảm bảo test isolation
WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/authenticate.php?logout')
WebUI.waitForPageLoad(30)

// [Security] Vào login page
WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/profile.php#login')
WebUI.waitForPageLoad(30)
WebUI.waitForElementVisible(findTestObject('Page_CURA Healthcare Service/input_Username'), 20)

// [Security] Thử SQL injection
WebUI.setText(findTestObject('Page_CURA Healthcare Service/input_Username'), "' OR '1'='1")
WebUI.setText(findTestObject('Page_CURA Healthcare Service/input_Password_1'), "' OR '1'='1")
WebUI.click(findTestObject('Page_CURA Healthcare Service/button_btn-login'))

// [Security] Verify login BỊ TỪ CHỐI (vẫn ở trang login)
CustomKeywords.'com.cura.keywords.VerifyKeywords.verifyLoginRejected'('SQL Injection USERNAME field')
WebUI.comment('[Security] SQL injection bị chặn đúng — login không thành công')