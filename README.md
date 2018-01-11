Solidity contract are in the /resources directory.

Trader
======

The site is quite simple. First any anonymous user can create a contract which is going to create to values:

*   advance amount - which needs to be paid before the sold product is created/done.
*   realization amount - which needs to be paid after the product is created/done.

Total amount of the contract is (advance amount + realization amount).

A seller goes into add a trade link where he defines the advance amount and the realization amount for the contract. The contract is deployed into the Ethereum blockchain. On the page he can see a status of the payment (which are sent from events on the blockchain and updated by WebSocket).

A buyer gets a link from seller to the contract. There he can create another sub-contract on the Ethereum blockchain and after this he can see his sub-contract view. The sub-account is used to transfer funds from buyer's casual Ethereum account and he can use them to pay out the main contract. On the view page of sub-account the user can see:

*   the current amount which is on the account - updated from WebSocket and send from the blockchain
*   pay button - which allows to send any amount to the main contract
*   pay advance button - which sends the remaining amount to fulfill the advance amount. When the advance is already paid - it is going to return money on the sub-account
*   pay realization button - which sends the remaining amount to fulfill the realization amount. When the realization is paid - it is going to return money to the sub-account.

Things which needs to be done:
======
*   Remove all constants from the development environment 
*   Add a proper Dockerfile
*   Finish supporting blockchain events with Akka's Actors
*   Add seller's parts