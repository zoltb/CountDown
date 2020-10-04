public class LaunchEvent implements Runnable {

    private int start;
    private String message;
    private TimeMonitor tm;
    private boolean isInterrupting;

    public LaunchEvent(int start, String message, TimeMonitor monitor, boolean isInterrupting) {
        this.start = start;
        this.message = message;
        this.tm = monitor;
        this.isInterrupting = isInterrupting;
    }

    public void run() {
        boolean eventDone = false;
        boolean aborted = false;
        while (!eventDone) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                aborted = true;
            }

            int a = tm.getTime();
            if (tm.getTime() <= start) {

                System.out.println(this.message);
                eventDone = true;
                //this will abort
                if (isInterrupting) {
                    tm.abortCountDown();
                    System.out.println("Aborting because: " + this.message);
                    break;
                }
            }
            if (Thread.interrupted()) {
                tm.abortCountDown();
                System.out.println("ABORTED!!!");
                aborted = true;

            }
            if (aborted) {
                System.out.println(this.message + " is aborted");
                break;
            }
        }
    }
}
