mbassador-spring
================

Use MBassador in Spring environment. Supports conditional event dispatch (e.g. after or before {commit|rollback}).
See MBassador for more extensive documentation. This project is still in beta but will be continuously improved
to create a stable release. Any feedback from the spring community is very welcome.

<h2>Usage</h2>

       TransactionalEventBus bus = new TransactionalEventBus();

       // post the object only if a transaction exists and completes with rollback
       bus.post(new Object()).after(Transaction.Completed().withRollBack());

       // post the object only if a transaction exists and commits succesfully
       bus.post(new Object()).after(Transaction.Completed().successfully());

       // post the object before transaction completes (regardless of status) or immediately
       // if none is present
       bus.post(new Object()).before(Transaction.Completion().OrIfNoTransactionAvailable());