# Checkout Service

[![Build Status](https://travis-ci.org/killercentury/dius-checkout.svg?branch=master)](https://travis-ci.org/killercentury/dius-checkout)

## Structure

`App.java` is the main class for bootstrapping the service. All the concrete products and pricing rules are initialized in it. It also run through all 3 scenarios from instructions to demonstrate all the features.

`Checkout.java` and `PricingRule.java` are the high level interfaces for this service.

`CheckoutImpl.java` is the concrete implementation of the checkout interface.

Under the `pricingrules` package, there are 3 rules - `BulkDiscount.java`, `BundleDiscount.java` and `BuyXGetYFree`. (Most of the properties inside of them should be self explained by name.)
1. BuyXGetYFree - It should be flexible enough to handle any combination of X(Buy) and Y(GetFree).
2. BulkDiscount - It should be assigned with the minimum quantity valid for the discount, which is 5 for "buys more than 4" from the instruction.
3. BundleDiscount - It only handle one to one bundle for two items at this moment, should be flexible to adapt to changes.

`AppTest` includes all the unit tests and scenarios tests. Ideally, it should take advantage of any dependency injection lib such as Dagger or Guice to provide better test isolation.
