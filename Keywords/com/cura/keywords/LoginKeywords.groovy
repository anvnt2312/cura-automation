package com.cura.keywords

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.cura.pages.LoginPage

public class LoginKeywords {

	@Keyword
	def performLogin(String username, String password) {
		// Logout trước (test isolation)
		WebUI.navigateToUrl(GlobalVariable.baseUrl + '/authenticate.php?logout')
		WebUI.waitForPageLoad(30)

		// Tạo object LoginPage rồi gọi method trực tiếp
		LoginPage loginPage = new LoginPage()
		loginPage.navigateToLoginPage()
		loginPage.enterUsername(username)
		loginPage.enterPassword(password)
		loginPage.clickLogin()
	}
}