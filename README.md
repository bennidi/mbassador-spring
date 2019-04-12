mbassador-spring
================
[![Build Status](https://travis-ci.org/bennidi/mbassador-spring.svg?branch=master)](https://travis-ci.org/bennidi/mbassador-spring)

CDI-like transactional events in Spring!

Use MBassador in Spring environment. Supports conditional message dispatch (e.g. after or before {commit|rollback}) based
on Springs TransactionSynchronization.

This project is currently in beta but is planned to be continuously improved to create a stable release. The functionality
to synchronize the message dispatch with spring managed transactions is implemented completely but still lacks a great deal of testing.

For the declaration of transactional conditions on the message listeners a solution is still to be found. Most likely, a specific
class that wraps the message can be used (similar to EnvelopedMessage in standard mbassador).

Please download and test this mbassador extension for your use cases and provide me with feedback and test cases. I think that declarative transactional
event listeners would be a great addition to the spring ecosystem (of which I am a great fan).


<h2>Usage</h2>

```java
// post the object only if a transaction exists and completes with rollback
bus.post(new Object()).after(Transaction.Completed().withRollBack());

// post the object only if a transaction exists and commits succesfully
bus.post(new Object()).after(Transaction.Completed().successfully());


// post the object only if a transaction exists but regardless of its outcome
bus.post(new Object()).after(Transaction.Completed().withAnyStatus());
bus.post(new Object()).after(Transaction.Completion()); // same same

// post the object according to the specified transactinoal condition or immediately if none is present
bus.post(new Object()).after(Transaction.Completion().OrIfNoTransactionAvailable());
bus.post(new Object()).after(Transaction.Completed().withRollBack().OrIfNoTransactionAvailable());
bus.post(new Object()).after(Transaction.Completed().successfully().OrIfNoTransactionAvailable());
bus.post(new Object()).after(Transaction.Completed().withAnyStatus().OrIfNoTransactionAvailable());
bus.post(new Object()).after(Transaction.Completion().OrIfNoTransactionAvailable()); // same same


// post the object before transaction completes (regardless of status)
bus.post(new Object()).before(Transaction.Completion());
// or immediately if none is present
bus.post(new Object()).before(Transaction.Completion().OrIfNoTransactionAvailable());

// compile error: the outcome is only available after completion so its not possible to refer
// to any transaction specific outcome when scheduling a message to be published in "Before"-Phase
//bus.post(new Object()).before(Transaction.Completed().successfully());
```
