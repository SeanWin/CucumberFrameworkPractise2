Feature: Search and place the order for products

  @PlaceOrder
  Scenario Outline: Search experience in both home and offers page

    Given User is on GreenCart Landing page
    When user searched with Shortname <Name> and extracted actual name of product
    And added "3" items of the selected product to cart
    Then user proceeds to checkout and validate the <Name> items in checkout page
    And verify user has ability to enter promo code and place the order

    Examples:
      | Name |
      | Tom  |
      #| Beet |