import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// ===== handle data =====
String safeUsername = username ?: ''
String safePassword = password ?: ''
String expectedOutcome = expected ?: 'failure'

// ===== FIX GỐC: logout trước, đảm bảo mỗi iteration bắt đầu từ trạng thái CHƯA đăng nhập =====
WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/authenticate.php?logout')
WebUI.waitForPageLoad(30)

// ===== vào login page =====
WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/profile.php#login')
WebUI.waitForPageLoad(30)
WebUI.delay(1)

// ===== chờ element login =====
WebUI.waitForElementVisible(findTestObject('Page_CURA Healthcare Service/input_Username'), 20)
WebUI.waitForElementVisible(findTestObject('Page_CURA Healthcare Service/input_Password_1'), 20)

// ===== input USERNAME =====
WebUI.click(findTestObject('Page_CURA Healthcare Service/input_Username'))
WebUI.clearText(findTestObject('Page_CURA Healthcare Service/input_Username'))
WebUI.setText(findTestObject('Page_CURA Healthcare Service/input_Username'), safeUsername)

// ===== input PASSWORD =====
WebUI.click(findTestObject('Page_CURA Healthcare Service/input_Password_1'))
WebUI.clearText(findTestObject('Page_CURA Healthcare Service/input_Password_1'))
WebUI.setText(findTestObject('Page_CURA Healthcare Service/input_Password_1'), safePassword)

// ===== click login =====
WebUI.click(findTestObject('Page_CURA Healthcare Service/button_btn-login'))

// ===== verify =====
if (expectedOutcome.toLowerCase() == 'success') {

	WebUI.waitForElementVisible(
		findTestObject('Page_CURA Healthcare Service/button_btn-book-appointment'), 20)

	WebUI.verifyElementPresent(
		findTestObject('Page_CURA Healthcare Service/button_btn-book-appointment'), 20)

} else {

	WebUI.waitForElementVisible(
		findTestObject('Page_CURA Healthcare Service/input_Username'), 20)

	WebUI.verifyElementPresent(
		findTestObject('Page_CURA Healthcare Service/input_Username'), 20)
}