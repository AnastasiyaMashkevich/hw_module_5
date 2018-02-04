Feature: Yandex base check

  Background:
    When  user loged in

  Scenario Outline: Create draft email
    Given user opens new form for email
    When   enters Body as "<body>", Subject as "<subject>", Addressee as "<addressee>" of email
    And  clicks close email
    And   opens draft folder
    Then  created deaft email displays within draft email list
    Examples:
      | body | subject | addressee |
      | body1 | subject1 | module05@yandex.ru |

  Scenario: Email verification
    Given opens draft folder
    When  clicks on nearly created email
    Then  filled fields are displayed as
    """{"body":"body1","subject":"subject1","addressee":"module05@yandex.ru"}"""

  Scenario: Sending email
    When clicks on send button
    And  opens draft folder
    Then  sent email is absent
    When opens sent folder
    Then sent email is present