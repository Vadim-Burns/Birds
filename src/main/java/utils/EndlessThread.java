package utils;

public class EndlessThread extends Thread {
    private final int mills;
    private final boolean debugMode;

    public EndlessThread(int mills, Runnable runnable) {
        this(mills, runnable, true);
    }

    public EndlessThread(int mills, Runnable runnable, boolean debugMode) {
        super(runnable);
        this.mills = mills;
        this.debugMode = debugMode;
    }

    @Override
    public void run() {
        while (true) {
            try {
                super.run();
                Thread.sleep(mills);
            } catch (InterruptedException e) {
                if (debugMode) e.printStackTrace();
                break;
            }
        }
    }
}
