Feature: Positive Login Test
  As a user
  I want to log in successfully
  So that I can access my account

  Scenario: Successful Login
    Given I am on the login page
    When I enter valid credentials
    And I click the login button
    Then I should be logged in successfully
