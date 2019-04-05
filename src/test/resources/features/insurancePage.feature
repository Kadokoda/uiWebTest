Feature: Travel Insurance feature

  Background:
    Given User is on Travel Insurance page

  Scenario: Challenge 1 As User click to show result with default choice by clicking on "Show my Result" button
    When User click to see the result with default choice
    Then User should see result display at least "3" card


  Scenario: Challenge 2 As a user I would like to filter my result base on conditions with
  min value handler of personal accident move right 20%
    When User click to see the result with default choice
    And User set filter, sort and detail with following criteria
      | Filter_Insurer |Filter_Personal_Accident_Percentage| Sort                | Detail_Destination| Start_Date     |
      | STARR          | 20                                |Price: Low to high   | Hong Kong         | 20/04/2019     |
    Then There're "2" cards are displaying

  Scenario Outline: Challenge 3 to make sure left menu filtering is working
    When User click to see the result with default choice
    And User set filter with "<Filter_Insurer>"
    Then User should see result display "<Result>" result
    Examples:
    | Filter_Insurer    | Result |
    | Malayan Insurance |   3    |
    | Pacific Cross     |   7   |

  Scenario Outline: Challenge 3 to make sure left menu sorting is working
    When User click to see the result with default choice
    And User set sort with "<Sort_Order>"
    Then User should see result display order with first card is "<Result_Of_First_Card>"
    Examples:
      | Sort_Order        | Result_Of_First_Card  |
      | Insurer: A to Z   |   FPG Insurance       |
      | Insurer: Z to A   |    STARR              |