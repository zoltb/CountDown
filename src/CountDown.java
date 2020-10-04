import java.util.ArrayList;

public class CountDown {

    public static void main(String[] args) {

       CountDownClock clock = new CountDownClock(20);
       ArrayList<Runnable> events = new ArrayList<Runnable>();

       int startOfTheInterruptEvent = (int)(Math.random()*10);

        System.out.println("Risk at " + startOfTheInterruptEvent + " sec!");

        events.add(new LaunchEvent(16,"Flood the pad!", clock, false));
       events.add(new LaunchEvent(6,"Start engines!", clock, false));
       events.add(new LaunchEvent(0,"Lift off!", clock, false));
       events.add(new LaunchEvent(startOfTheInterruptEvent,"Bird there!", clock, true));

       clock.start();

       for (Runnable e : events) {
           new Thread(e).start();
       }
    }
}
