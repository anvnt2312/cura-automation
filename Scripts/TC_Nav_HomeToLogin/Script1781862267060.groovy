import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

try { DriverFactory.getWebDriver() } catch (Exception e) {
	WebUI.openBrowser(''); WebUI.maximizeWindow()
}

// [Navigation] Mở trang chủ CURA
WebUI.navigateToUrl(GlobalVariable.baseUrl)
WebUI.waitForPageLoad(30)

// [Navigation] Dùng HomePage để vào form login
CustomKeywords.'com.cura.pages.HomePage.clickMakeAppointment'()

// [Navigation] Verify đã tới form login (LoginPage)
WebUI.waitForElementVisible(findTestObject('Page_CURA Healthcare Service/input_Username'), 20)
WebUI.verifyElementPresent(findTestObject('Page_CURA Healthcare Service/input_Username'), 20)
WebUI.comment('[Navigation] Đã điều hướng từ HomePage sang LoginPage thành công')