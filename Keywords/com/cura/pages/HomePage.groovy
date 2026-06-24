package com.cura.pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

public class HomePage {

	// Click nút "Make Appointment" để vào form login
	@Keyword
	def clickMakeAppointment() {
		WebUI.waitForElementVisible(findTestObject('Page_CURA Healthcare Service/a_btn-make-appointment'), 20)
		WebUI.click(findTestObject('Page_CURA Healthcare Service/a_btn-make-appointment'))
	}

	// Logout - quay về trạng thái chưa đăng nhập
	@Keyword
	def logout() {
		WebUI.navigateToUrl(GlobalVariable.baseUrl + '/authenticate.php?logout')
		WebUI.waitForPageLoad(30)
	}

	// Verify đang ở trang chủ (nút Make Appointment hiển thị)
	@Keyword
	def isOnHomePage() {
		WebUI.waitForElementVisible(findTestObject('Page_CURA Healthcare Service/a_btn-make-appointment'), 20)
		return WebUI.verifyElementPresent(findTestObject('Page_CURA Healthcare Service/a_btn-make-appointment'), 20)
	}
}