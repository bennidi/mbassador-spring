package org.mbassy.spring;

/**
 * @author bennidi
 * Date: 11/12/12
 */
public class Transaction {

    public static int OrIfNotTransationAvailable = 10;

    public enum Phase {
        Commit, Completion;
    }

    public enum Status {
        Committed, RolledBack, Unknown, Any;
    }


    public abstract static class GenericTransaction<T extends GenericTransaction<T>> {

        private Phase phase;

        private Status status;

        private boolean publishWithoutActiveTransaction = false;

        public GenericTransaction(Phase phase, Status status) {
            this.phase = phase;
            this.status = status;
        }

        public Phase getPhase() {
            return phase;
        }

        public Status getStatus() {
            return status;
        }

        protected boolean publishWithoutActiveTransaction(){
            return publishWithoutActiveTransaction;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            GenericTransaction that = (GenericTransaction) o;

            if (publishWithoutActiveTransaction != that.publishWithoutActiveTransaction) return false;
            if (phase != that.phase) return false;
            if (status != that.status) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = phase != null ? phase.hashCode() : 0;
            result = 31 * result + (status != null ? status.hashCode() : 0);
            result = 31 * result + (publishWithoutActiveTransaction ? 1 : 0);
            return result;
        }

        public T OrIfNoTransactionAvailable(){
            publishWithoutActiveTransaction = true;
            return (T)this;
        }
    }

    public static class UnparametrizedTransaction extends GenericTransaction<UnparametrizedTransaction>{

        private Phase phase;


        public UnparametrizedTransaction(Phase phase) {
            super(phase, null);
        }
    }

    public static class ParametrizedTransaction extends GenericTransaction<ParametrizedTransaction>{

        public ParametrizedTransaction(Phase phase, Status status) {
            super(phase,status);
        }

    }

    public static UnparametrizedTransaction Commit() {
        return new UnparametrizedTransaction(Phase.Commit);
    }

    public static UnparametrizedTransaction Completion() {
        return new UnparametrizedTransaction(Phase.Completion);
    }

    public static CompletedTransaction Completed() {
        return new CompletedTransaction();
    }


    public static class CompletedTransaction {

        public ParametrizedTransaction withStatus(Status status) {
            return new ParametrizedTransaction(Phase.Completion, status);
        }

        public ParametrizedTransaction withRollBack() {
            return withStatus(Status.RolledBack);
        }

        public ParametrizedTransaction successfully() {
            return withStatus(Status.Committed);
        }

        public ParametrizedTransaction withAnyStatus() {
            return withStatus(Status.Any);
        }
    }

}
