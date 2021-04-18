package utils;

public class EndlessThread extends Thread {
    private final int mills;

    public EndlessThread(int mills, Runnable runnable) {
        super(runnable);
        this.mills = mills;
    }

    @Override
    public void run() {
        while (true) {
            try {
                super.run();
                Thread.sleep(mills);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
