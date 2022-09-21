import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    private static final int NUMBER_OF_CALLS = 60;
    private static final int ATS_SLEEP = 1000;
    private static final int NUMBER_OF_SPECIALISTS = 3;
    private static final int SPECIALIST_SLEEP = 4000;
    private static final int START_WORKING_DAY = 3000;

    public static void main(String[] args) throws InterruptedException {

        ConcurrentLinkedQueue <String> calls = new ConcurrentLinkedQueue<>();

        new Thread(() ->
        {
            try {
                for (int j = 0; j < NUMBER_OF_CALLS; j++) {
                    calls.offer("поступивший звонок " + j);
                    System.out.println("Поступил звонок " + j);
                    Thread.sleep(ATS_SLEEP);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(START_WORKING_DAY);
        for (int i = 0; i < NUMBER_OF_SPECIALISTS; i++) {
            new Thread(() -> {
                try {
                    while (!calls.isEmpty()) {
                        System.out.println("Обработан " + calls.poll());
                        Thread.sleep(SPECIALIST_SLEEP);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }



    }
}
