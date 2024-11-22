# language: en

@all @req001
Feature: Create Category

  Scenario: Create category with success
    Given i have a category with name
      | categoryName | <Name> |
    When i create the category
      | categoryName | <Name> |
    Then the category should be created with success

    Examples:
      | Name |
      | Game |
      | Food |

  Scenario: Create category with invalid name
    Given i have a category with name
      | categoryName | <Name> |
    When i create the category
      | categoryName | <Name> |
    Then the category should not be created
      | categoryName | <Name> |

    Examples:
      | Name |
      |      |

  Scenario: Update category with success
    Given i have a category with name
      | categoryName | <NewName> |
      | id           | <Id>      |
    When i update the category
      | categoryName | <NewName> |
      | id           | <Id>      |
    Then the category should be updated with success
      | id           | <Id>      |

    Examples:
      | Id | NewName |
      | 1  | Game |
      | 2  | Food |