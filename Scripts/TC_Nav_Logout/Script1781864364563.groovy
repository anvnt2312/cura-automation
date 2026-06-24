import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
// Mở browser nếu chưa có (chạy lẻ lẫn trong suite đều được)
try {
	DriverFactory.getWebDriver()
} catch (Exception e) {
	WebUI.openBrowser('')
	WebUI.maximizeWindow()
}

// [Navigation] Login trước để có trạng thái đã đăng nhập
WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/profile.php#login')
WebUI.waitForPageLoad(30)
WebUI.setText(findTestObject('Page_CURA Healthcare Service/input_Username'), 'John Doe')
WebUI.setText(findTestObject('Page_CURA Healthcare Service/input_Password_1'), 'ThisIsNotAPassword')
WebUI.click(findTestObject('Page_CURA Healthcare Service/button_btn-login'))
WebUI.waitForElementVisible(findTestObject('Page_CURA Healthcare Service/button_btn-book-appointment'), 20)

// [Navigation] Logout bằng URL trực tiếp
WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/authenticate.php?logout')
WebUI.waitForPageLoad(30)

// [Navigation] Verify đã về trang chủ (nút Make Appointment xuất hiện)
WebUI.waitForElementVisible(findTestObject('Page_CURA Healthcare Service/a_btn-make-appointment'), 20)
WebUI.verifyElementPresent(findTestObject('Page_CURA Healthcare Service/a_btn-make-appointment'), 20)
WebUI.comment('[Navigation] Logout thành công, đã về trang chủ')