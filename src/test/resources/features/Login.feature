@login
Feature: Check Login Feature

  @test
  Scenario: Perform Login functionality on Naukri
    Given User signs in to the portal
    And User Validates the home page
    And User selects the view section
    And User Updates the status for today

  @test1
  Scenario: validate login
    Given Login to portal
    And User signs in to the portal