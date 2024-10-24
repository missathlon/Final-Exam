public class Counter implements AutoCloseable {
    private int count = 0;
    private boolean closed = false;

    public int getCount() {
        return count;
    }

    public void add() {
        if (closed) {
            throw new IllegalStateException("The counter is already closed!");
        }
        count++;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        if (!closed) {
            closed = true;
            System.out.println("The counter has been closed successfully.");
        } else {
            throw new IllegalStateException("The counter is already closed!");
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Current counter value:" + count;
    }
}
