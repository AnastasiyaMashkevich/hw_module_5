Feature: Yandex additional check

  Background:
    When  user loged in

  Scenario: Move email to draft folder
    Given opens sent folder
    When  drag sent email to draft folder
    Then email does not display within folder
    And   opens draft folder
    Then  email displays within folder

  Scenario: Delete Draft Email
    Given opens draft folder
    When  user does context click on any email
    And click Delete
    Then email does not display within folder