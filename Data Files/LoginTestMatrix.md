# Data-Driven Login Test Matrix - Katalon Test Suite

## Overview
This document outlines the data-driven testing approach for the CURA Healthcare Service login feature. All 25 test scenarios are organized in `LoginTestData.csv` and executed via a single data-driven test case: `TC_Login_DataDriven`.

---

## Test Execution Flow
1. **Script**: `Scripts/TC_Login_DataDriven/Script_DataDriven_Login.groovy`
2. **Data Source**: `Data Files/LoginTestData.csv`
3. **Test Case**: `Test Cases/TC_Login_DataDriven.tc`
4. **Test Suite**: `Test Suites/TS_Login.ts`

For each row in the CSV:
- Open browser and navigate to login page
- Set username/password from the data file
- Click login button
- Verify expected outcome (Success or Failure)
- Log test result with category and priority

---

## Test Matrix by Category

### 1. HAPPY PATH (1 test)
| Test Case ID | Scenario | Username | Password | Expected | Priority |
|---|---|---|---|---|---|
| TC_Login_Happy_ValidCredentials | Valid credentials | John Doe | ThisIsNotAPassword | Success | **HIGH** |

**Regression**: Must pass in every build. This is the baseline functionality.

---

### 2. INVALID CREDENTIALS (3 tests)
| Test Case ID | Scenario | Username | Password | Expected | Priority |
|---|---|---|---|---|---|
| TC_Login_Invalid_InvalidUser_ValidPass | Invalid user + valid password | invalid_user | ThisIsNotAPassword | Failure | **HIGH** |
| TC_Login_Invalid_ValidUser_InvalidPass | Valid user + invalid password | John Doe | wrongPassword | Failure | **HIGH** |
| TC_Login_Invalid_InvalidUser_InvalidPass | Both invalid | invalid_user | wrongPassword | Failure | **HIGH** |

**Regression**: Critical for security. All must fail with correct error message.

---

### 3. EMPTY INPUTS (3 tests)
| Test Case ID | Scenario | Username | Password | Expected | Priority |
|---|---|---|---|---|---|
| TC_Login_Empty_Username | Empty username | (empty) | (empty) | Failure | **HIGH** |
| TC_Login_Empty_Password | Empty password | John Doe | (empty) | Failure | **HIGH** |
| TC_Login_Empty_Both | Both empty | (empty) | (empty) | Failure | **HIGH** |

**Regression**: Input validation is mandatory. All must fail gracefully.

---

### 4. BOUNDARY VALUE ANALYSIS (5 tests)
| Test Case ID | Scenario | Username | Password | Expected | Priority |
|---|---|---|---|---|---|
| TC_Login_Boundary_UsernameMinLength | Username 1 char | a | ThisIsNotAPassword | Failure | MEDIUM |
| TC_Login_Boundary_PasswordMinLength | Password 1 char | John Doe | x | Failure | MEDIUM |
| TC_Login_Boundary_UsernameMaxLength | Username 255 chars | (long string) | ThisIsNotAPassword | Failure | MEDIUM |
| TC_Login_Boundary_LeadingSpaceUsername | Username with leading space | (space)John Doe | ThisIsNotAPassword | Failure | MEDIUM |
| TC_Login_Boundary_TrailingSpacePassword | Password with trailing space | John Doe | ThisIsNotAPassword(space) | Failure | MEDIUM |

**Purpose**: Ensures edge cases don't crash the application or bypass validation.

---

### 5. EQUIVALENCE PARTITIONING (4 tests)
| Test Case ID | Scenario | Username | Password | Expected | Priority |
|---|---|---|---|---|---|
| TC_Login_Equiv_AlphanumericUser | Alphanumeric username | user123 | ThisIsNotAPassword | Failure | Low |
| TC_Login_Equiv_SpecialCharUser | Special chars in username | user@#$ | ThisIsNotAPassword | Failure | Low |
| TC_Login_Equiv_NumericPassword | Numeric password | John Doe | 12345678 | Failure | Low |
| TC_Login_Equiv_SpecialCharPassword | Special chars in password | John Doe | P@ssw0rd! | Failure | Low |

**Purpose**: Verify consistent behavior across different input character types.

---

### 6. SECURITY RISKS (5 tests)
| Test Case ID | Scenario | Username | Password | Expected | Priority |
|---|---|---|---|---|---|
| TC_Login_Security_SQLInjection_Username | SQL injection in username | ' OR '1'='1 | ThisIsNotAPassword | Failure | **HIGH** |
| TC_Login_Security_SQLInjection_Password | SQL injection in password | John Doe | ' OR '1'='1 | Failure | **HIGH** |
| TC_Login_Security_XSS_Username | XSS script in username | <script>alert(1)</script> | ThisIsNotAPassword | Failure | **HIGH** |
| TC_Login_Security_XSS_Password | XSS img tag in password | John Doe | <img src=x onerror=alert(1)> | Failure | **HIGH** |
| TC_Login_Security_LongPayload | Extremely long input | (long string 200+ chars) | ThisIsNotAPassword | Failure | MEDIUM |

**Regression**: Critical security tests. Must fail safely without executing or disclosing sensitive data.

---

### 7. USABILITY & DATA VALIDATION (4 tests)
| Test Case ID | Scenario | Username | Password | Expected | Priority |
|---|---|---|---|---|---|
| TC_Login_Usability_ErrorMessageAccuracy | Error message text verification | invalid | invalid | Failure | **HIGH** |
| TC_Login_Usability_WhitespaceUsername | Username only spaces | (3 spaces) | ThisIsNotAPassword | Failure | MEDIUM |
| TC_Login_Usability_WhitespacePassword | Password only spaces | John Doe | (3 spaces) | Failure | MEDIUM |
| TC_Login_Usability_UnicodeUsername | Unicode in username | 用户名 | ThisIsNotAPassword | Failure | Low |
| TC_Login_Usability_UnicodePassword | Unicode in password | John Doe | 密码123 | Failure | Low |

**Purpose**: Verify error messages are clear, and international characters are handled safely.

---

## Regression Testing Priority

### MUST RUN IN EVERY BUILD (HIGH Priority: 12 tests)
```
1. TC_Login_Happy_ValidCredentials
2. TC_Login_Invalid_InvalidUser_ValidPass
3. TC_Login_Invalid_ValidUser_InvalidPass
4. TC_Login_Invalid_InvalidUser_InvalidPass
5. TC_Login_Empty_Username
6. TC_Login_Empty_Password
7. TC_Login_Empty_Both
8. TC_Login_Security_SQLInjection_Username
9. TC_Login_Security_SQLInjection_Password
10. TC_Login_Security_XSS_Username
11. TC_Login_Security_XSS_Password
12. TC_Login_Usability_ErrorMessageAccuracy
```

### RUN IN WEEKLY REGRESSION (MEDIUM Priority: 8 tests)
```
- All Boundary Value Analysis tests
- TC_Login_Security_LongPayload
- TC_Login_Usability_WhitespaceUsername
- TC_Login_Usability_WhitespacePassword
```

### RUN PERIODICALLY (LOW Priority: 5 tests)
```
- All Equivalence Partitioning tests
- TC_Login_Usability_UnicodeUsername
- TC_Login_Usability_UnicodePassword
```

---

## CSV Data File Structure

**Location**: `Data Files/LoginTestData.csv`

**Columns**:
- `TestCaseID`: Unique identifier for the test case
- `Category`: Test category (Happy Path, Invalid Credentials, Boundary Value, etc.)
- `Scenario`: Human-readable description of the test scenario
- `Username`: Input value for username field
- `Password`: Input value for password field
- `ExpectedOutcome`: `Success` or `Failure`
- `ErrorMessage`: Expected error text (for failure cases)
- `Priority`: `High`, `Medium`, or `Low`

**Total Test Cases**: 25

---

## Expected Error Message

All failure cases expect:
```
Login failed! Please ensure the username and password are valid.
```

This message is located in:
```xpath
//p[contains(@class,'text-danger')]
```

---

## Object Repository Paths (Reused)

- `Page_CURA Healthcare Service/a_btn-make-appointment` - Make Appointment button on home page
- `Page_CURA Healthcare Service/input_Username` - Username input field
- `Page_CURA Healthcare Service/input_Password_1` - Password input field
- `Page_CURA Healthcare Service/button_btn-login` - Login button

---

## Test Execution Notes

1. **Browser Management**: Opens once, stays open for all 25 iterations, closes at end
2. **Wait Times**: Uses 10-second wait for element visibility
3. **Failure Handling**: Continues on failure to collect all test results
4. **Comments**: Each test iteration logs TestCaseID, Category, Scenario, and Priority
5. **URL Verification**: Success case verifies URL contains "appointment"

---

## How to Add More Tests

1. Add a new row to `LoginTestData.csv` with:
   - Unique TestCaseID
   - Category name (create new or reuse existing)
   - Scenario description
   - Username and password test data
   - ExpectedOutcome (`Success` or `Failure`)
   - ErrorMessage (if Failure)
   - Priority level

2. No script changes needed—the data-driven test will automatically include the new row.

3. Re-run `TC_Login_DataDriven` to execute the new scenario.

---

## Example: Running High-Priority Tests Only

To create a high-priority-only regression suite, create a new test suite and add only test cases with `Priority=High` from the CSV. Alternatively, you could modify the script to filter by priority:

```groovy
if (priority.equals('High')) {
    // execute test
}
```
