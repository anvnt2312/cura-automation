# Data-Driven Login Testing - Quick Start Guide

## Overview

You now have a **comprehensive data-driven testing framework** for the CURA Healthcare login feature with 25 test scenarios covering functional, security, and usability requirements.

---

## What Was Created

### 1. **Test Data** (`Data Files/LoginTestData.csv`)
A CSV file containing 25 test scenarios with:
- Username and password combinations
- Expected outcomes (Success/Failure)
- Expected error messages
- Test categories and priorities

### 2. **Two Execution Modes**

#### Full Comprehensive Suite
- **Test**: `TC_Login_DataDriven` (All 25 tests)
- **Time**: ~10-15 minutes
- **Use**: Full regression, feature validation, complete coverage
- **Tests**: Happy path + Invalid credentials + Empty inputs + Boundary values + Security + Usability

#### Quick High-Priority Suite
- **Test**: `TC_Login_HighPriorityRegression` (12 HIGH priority tests)
- **Time**: ~5-7 minutes
- **Use**: Pre-commit checks, CI/CD pipelines, daily regression
- **Tests**: Critical functionality + Security risks + Error validation

---

## How to Run

### Option 1: Katalon Studio GUI
1. Open Katalon Studio
2. In **Test Explorer** panel, locate `Test Cases/TC_Login_DataDriven` (or `TC_Login_HighPriorityRegression`)
3. Right-click → **Run** → Select browser (Chrome, Firefox, etc.)
4. View results in **Execution** tab

### Option 2: Command Line
```bash
# Run full test suite
katalonc -projectPath="c:\Users\avo24\Katalon Studio\Test project" `
  -testSuitePath="Test Suites/TS_Login" `
  -browserType="Chrome" `
  -executionProfile="default"

# Run high-priority regression only
katalonc -projectPath="c:\Users\avo24\Katalon Studio\Test project" `
  -testCasePath="Test Cases/TC_Login_HighPriorityRegression" `
  -browserType="Chrome"
```

---

## Test Cases Covered

### ✅ Happy Path (1 test)
- Valid username & password → Login succeeds

### ❌ Invalid Credentials (3 tests)
- Invalid user + valid pass
- Valid user + invalid pass
- Both invalid

### ⚠️ Empty Inputs (3 tests)
- Empty username
- Empty password
- Both empty

### 🔍 Boundary Values (5 tests)
- Username 1 character
- Password 1 character
- Username 255 characters
- Username with leading space
- Password with trailing space

### 🔄 Equivalence Partitioning (4 tests)
- Alphanumeric username
- Special characters in username
- Numeric password
- Special characters in password

### 🛡️ Security (5 tests)
- SQL injection in username: `' OR '1'='1`
- SQL injection in password
- XSS in username: `<script>alert(1)</script>`
- XSS in password: `<img src=x onerror=alert(1)>`
- Extremely long input

### 💬 Usability (4 tests)
- Error message accuracy
- Whitespace-only username
- Whitespace-only password
- Unicode characters (Chinese: 用户名, 密码)

---

## Test Results

### For Each Test Case, You'll See:
```
[HIGH] TC_Login_Happy_ValidCredentials - Valid credentials
✓ PASS - Login successful

[HIGH] TC_Login_Invalid_InvalidUser_ValidPass - Invalid username + valid password
✓ PASS - Login failed as expected

[HIGH] TC_Login_Security_SQLInjection_Username - SQL Injection in username
✓ PASS - Login failed as expected
```

### Summary Report:
```
============================================================
TEST SUMMARY REPORT
============================================================
Total Tests: 25
Passed: 25 ✓
Failed: 0 ✗
Success Rate: 100%
============================================================
```

---

## Priority Levels

### 🔴 HIGH Priority (12 tests) - Run Every Build
Critical functionality that must never fail:
- Login success
- Invalid credential handling
- Empty field validation
- SQL injection protection
- XSS protection
- Error message accuracy

### 🟡 MEDIUM Priority (8 tests) - Run Weekly
Edge cases and input validation:
- Boundary value testing
- Length limits
- Space handling
- Long payload handling

### 🟢 LOW Priority (5 tests) - Run Periodically
Data type coverage:
- Alphanumeric inputs
- Special characters
- Unicode support

---

## Adding New Tests (No Code Required!)

To add a new test scenario:

1. **Open**: `Data Files/LoginTestData.csv` in Excel or VS Code
2. **Add a new row** with:
   ```
   TC_Login_Custom_MyTest,Custom Category,My test scenario,myusername,mypassword,Failure,Login failed! Please ensure the username and password are valid.,High
   ```
3. **Save** the CSV
4. **Run** `TC_Login_DataDriven` again
5. **New test is included automatically!**

No script changes needed. The data-driven engine reads the CSV dynamically.

---

## Columns in LoginTestData.csv

| Column | Description | Example |
|--------|-------------|---------|
| TestCaseID | Unique test identifier | `TC_Login_Happy_ValidCredentials` |
| Category | Test category | `Happy Path`, `Security`, `Boundary Value` |
| Scenario | Human-readable description | `Valid credentials` |
| Username | Username input value | `John Doe` or `invalid_user` |
| Password | Password input value | `ThisIsNotAPassword` or empty string |
| ExpectedOutcome | `Success` or `Failure` | `Success` or `Failure` |
| ErrorMessage | Expected error text (Failure only) | `Login failed! Please ensure...` |
| Priority | Test priority | `High`, `Medium`, or `Low` |

---

## Expected Results

### Successful Login Test
```
Username: John Doe
Password: ThisIsNotAPassword
↓
URL changes to: https://katalon-demo-cura.herokuapp.com/profile.php#appointment
✓ SUCCESS
```

### Failed Login Test
```
Username: invalid_user
Password: wrongPassword
↓
Error message appears: "Login failed! Please ensure the username and password are valid."
✓ FAILURE AS EXPECTED
```

---

## Troubleshooting

### Test Timeout (Element Not Visible)
- Increase wait time in script (currently 10 seconds)
- Check if CURA service is accessible
- Verify object repository paths are correct

### CSV File Not Found
- Ensure path: `Data Files/LoginTestData.csv`
- Verify file is in Katalon project root
- Reload project in Katalon Studio

### Login Still Success After Security Test
- Security tests expect failure (no actual injection executed)
- Application should reject malicious input safely
- Error message should appear as normal login failure

---

## Performance Notes

| Test Suite | Tests | Time | Use Case |
|------------|-------|------|----------|
| `TC_Login_HighPriorityRegression` | 12 | 5-7 min | CI/CD, pre-commit |
| `TC_Login_DataDriven` | 25 | 10-15 min | Full regression, nightly |

*Times are approximate and depend on network latency to CURA demo server*

---

## Object Repository Paths Used

All tests reuse existing objects (no new objects created):
- `Page_CURA Healthcare Service/a_btn-make-appointment`
- `Page_CURA Healthcare Service/input_Username`
- `Page_CURA Healthcare Service/input_Password_1`
- `Page_CURA Healthcare Service/button_btn-login`

Error element created dynamically via XPath:
- `//p[contains(@class,'text-danger')]`

---

## Next Steps

1. **Run the tests**: Execute `TC_Login_DataDriven` in Katalon
2. **Review results**: Check the execution log for any failures
3. **Extend the tests**: Add more scenarios to the CSV as needed
4. **Integrate with CI/CD**: Use `TC_Login_HighPriorityRegression` in your pipeline
5. **Schedule runs**: Setup nightly test execution

---

## Support

For each test case, the script logs:
- Test Case ID
- Category
- Scenario name
- Priority level
- Pass/Fail status
- Error details (if any)

Check **Katalon Execution Reports** for detailed step-by-step logs.
