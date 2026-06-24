package com.cura.pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

public class LoginPage {

	// Mở trang login
	@Keyword
def navigateToLoginPage() {
	// Logout trước để đảm bảo về trạng thái chưa đăng nhập (test isolation)
	WebUI.navigateToUrl(GlobalVariable.baseUrl + '/authenticate.php?logout')
	WebUI.waitForPageLoad(30)
	// Vào login page
	WebUI.navigateToUrl(GlobalVariable.baseUrl + '/profile.php#login')
	WebUI.waitForPageLoad(30)
	WebUI.waitForElementVisible(findTestObject('Page_CURA Healthcare Service/input_Username'), 20)
}

	// Nhập username
	@Keyword
	def enterUsername(String username) {
		WebUI.clearText(findTestObject('Page_CURA Healthcare Service/input_Username'))
		WebUI.setText(findTestObject('Page_CURA Healthcare Service/input_Username'), username)
	}

	// Nhập password
	@Keyword
	def enterPassword(String password) {
		WebUI.clearText(findTestObject('Page_CURA Healthcare Service/input_Password_1'))
		WebUI.setText(findTestObject('Page_CURA Healthcare Service/input_Password_1'), password)
	}

	// Click nút login
	@Keyword
	def clickLogin() {
		WebUI.click(findTestObject('Page_CURA Healthcare Service/button_btn-login'))
	}
}