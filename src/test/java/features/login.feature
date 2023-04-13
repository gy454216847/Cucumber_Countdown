Feature: Login

  @LoginRightAccount
  Scenario Outline: Login with right account
    Given I am on the Countdown page
    When I click Sign in link
    And I input right "<username>" and "<passwords>"
    And I click login button
    Then I can login my account
    Examples:
      | username         | passwords |
      | 454216847@qq.com | gy1988711 |

  @LoginWrongPassword
  Scenario Outline: Login with wrong username and wrong passwords
    Given I am on the Countdown page
    When I click Sign in link
    And I input wrong "<username>" and wrong "<passwords>"
    And I click login button
    Then I can see the "<labelalter>"
    Examples:
      | username         | passwords | labelalter |
      | 454216847@qq.com | 123456    | An invalid email and/or password has been entered. Please try again. Please note, passwords are case-sensitive. |

  @LoginEmptyUsernamePasswords
  Scenario: Login with empty username and passwords
    Given I am on the Countdown page
    When I click Sign in link
    And I  do not input username and passwords
    Then I can see “This field is required"

  @Logout
  Scenario Outline: Logout
    Given I am on the Countdown page
    When I click Sign in link
    And I input right "<username>" and "<passwords>"
    And I click login button
    Then I can login my account
    When I click Sign out link
    Then I can logout my account
    Examples:
      | username         | passwords |
      | 454216847@qq.com | gy1988711 |