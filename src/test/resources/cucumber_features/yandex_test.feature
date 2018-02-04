Feature: Yandex base check

  Background:
    When  user loged in

  Scenario: Create draft email
    Given user opens new form for email
    When   enters body, subject, addressee of email
    And  clicks close email
    And   opens draft folder
    Then  created deaft email displays within draft email list

  Scenario: Email verification
    Given opens draft folder
    When  clicks on nearly created email
    Then  filled fields are displayed

  Scenario: Sending email
    When clicks on send button
    And  opens draft folder
    Then  sent email is absent
    When opens sent folder
    Then sent email is present