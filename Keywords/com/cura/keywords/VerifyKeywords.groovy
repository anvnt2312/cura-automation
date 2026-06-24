package com.cura.keywords

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

public class VerifyKeywords {

	@Keyword
	def verifyElementPresentWithScreenshot(TestObject to, int timeout) {
		try {
			WebUI.waitForElementVisible(to, timeout)
			WebUI.verifyElementPresent(to, timeout)
			// Chỉ chụp khi PASS nếu công tắc bật
			if (GlobalVariable.screenshotOnPass) {
				WebUI.takeScreenshot()
				WebUI.comment('[SCREENSHOT-PASS] Đã chụp màn hình - verify thành công')
			}
		} catch (Exception e) {
			// LUÔN chụp khi FAIL (không phụ thuộc công tắc)
			WebUI.takeScreenshot()
			WebUI.comment('[SCREENSHOT-FAIL] Đã chụp màn hình tại thời điểm fail')
			throw e
		}
	}
	// Verify login thất bại — báo lỗi có ngữ cảnh (phân biệt bug app)
	@Keyword
	def verifyLoginRejected(String scenario) {
		try {
			WebUI.waitForElementVisible(findTestObject('Page_CURA Healthcare Service/p_login_error'), 20)
			WebUI.verifyElementPresent(findTestObject('Page_CURA Healthcare Service/p_login_error'), 20)
			WebUI.takeScreenshot()
			WebUI.comment("[PASS] [$scenario] Login bị từ chối ĐÚNG như mong đợi")
		} catch (Exception e) {
			WebUI.takeScreenshot()
			throw new com.kms.katalon.core.exception.StepFailedException(
				"[$scenario] CẢNH BÁO: Mong đợi login bị từ chối nhưng KHÔNG thấy thông báo lỗi. " +
				"App có thể đã chấp nhận input không hợp lệ - cần kiểm tra xem có phải BUG bảo mật!"
			)
		}
	}
}