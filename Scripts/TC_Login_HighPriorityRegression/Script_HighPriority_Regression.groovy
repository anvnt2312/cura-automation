import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

/**
 * High-Priority Login Regression Test (Data-Driven)
 * Chạy các High priority case từ HighPriorityData
 */

def testData = findTestData('Data Files/HighPriorityData')
int rowCount = testData.getRowNumbers()

int passCount = 0
int failCount = 0
List<String> failedTests = []

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.comment('='.multiply(60))
WebUI.comment('HIGH-PRIORITY LOGIN REGRESSION TEST SUITE')
WebUI.comment('='.multiply(60))

for (int row = 1; row <= rowCount; row++) {
	String testCaseID = testData.getValue('TestCaseID', row)
	String priority = testData.getValue('Priority', row)

	if (!priority.equals('High')) {
		continue
	}

	String category = testData.getValue('Category', row)
	String scenario = testData.getValue('Scenario', row)
	String username = testData.getValue('Username', row)
	String password = testData.getValue('Password', row)
	String expectedOutcome = testData.getValue('ExpectedOutcome', row)
	String expectedErrorMessage = testData.getValue('ErrorMessage', row)

	WebUI.comment("\n[$priority] $testCaseID - $scenario")

	try {
		// FIX ISOLATION: logout trước mỗi dòng để về trạng thái sạch
		WebUI.navigateToUrl(GlobalVariable.baseUrl + '/authenticate.php?logout')
		WebUI.waitForPageLoad(30)

		// Vào login page
		WebUI.navigateToUrl(GlobalVariable.baseUrl + '/profile.php#login')
		WebUI.waitForPageLoad(30)
		WebUI.waitForElementVisible(findTestObject('Page_CURA Healthcare Service/input_Username'), 20)

		// Nhập credential
		WebUI.clearText(findTestObject('Page_CURA Healthcare Service/input_Username'))
		WebUI.setText(findTestObject('Page_CURA Healthcare Service/input_Username'), username != null ? username : '')
		WebUI.clearText(findTestObject('Page_CURA Healthcare Service/input_Password_1'))
		WebUI.setText(findTestObject('Page_CURA Healthcare Service/input_Password_1'), password != null ? password : '')

		// Click login
		WebUI.click(findTestObject('Page_CURA Healthcare Service/button_btn-login'))
		WebUI.waitForPageLoad(20)

		// Verify theo expected outcome
		if (expectedOutcome.equals('Success')) {
			WebUI.verifyElementPresent(findTestObject('Page_CURA Healthcare Service/button_btn-book-appointment'), 20, FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.comment('✓ PASS - Login successful')
			passCount++
		} else {
			WebUI.verifyElementPresent(findTestObject('Page_CURA Healthcare Service/p_login_error'), 20, FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.comment('✓ PASS - Login failed as expected')
			passCount++
		}

	} catch (Exception e) {
		WebUI.comment("✗ FAIL - Exception: ${e.message}")
		WebUI.takeScreenshot()
		failCount++
		failedTests.add(testCaseID)
	}
}

WebUI.closeBrowser()

// Báo cáo tổng kết
WebUI.comment('\n' + '='.multiply(60))
WebUI.comment('TEST SUMMARY REPORT')
WebUI.comment('='.multiply(60))
WebUI.comment("Total Tests: ${passCount + failCount}")
WebUI.comment("Passed: $passCount")
WebUI.comment("Failed: $failCount")

if (failCount > 0) {
	WebUI.comment("\nFailed Tests:")
	failedTests.each { testId -> WebUI.comment("  - $testId") }
	// FIX: ép case báo FAIL khi có dòng fail (không "giả pass")
	throw new com.kms.katalon.core.exception.StepFailedException("Regression có ${failCount} case FAIL: ${failedTests.join(', ')}")
}

WebUI.comment('='.multiply(60))