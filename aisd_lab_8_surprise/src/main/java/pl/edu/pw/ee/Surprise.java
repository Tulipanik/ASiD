package pl.edu.pw.ee;

import java.util.ArrayList;

public class Surprise {

    private ArrayList<String> foundQueues;

    public int findNumOf(int[] queue) {

        foundQueues = new ArrayList<>();
        String potentialQueue;
        final int THE_BIGGEST = 1000000000;
        final int QUEUE_LENGTH = 3;
        int sum = 0;

        if (queue.length < QUEUE_LENGTH) {

            return sum;
        }

        for (int i = 0; i < queue.length - 2; i++) {
            for (int j = i + 1; j < queue.length - 1; j++) {
                for (int k = j + 1; k < queue.length; k++) {

                    String strI = String.valueOf(queue[i]);
                    String strJ = String.valueOf(queue[j]);
                    String strK = String.valueOf(queue[k]);

                    potentialQueue = strI + ',' + strJ + ',' + strK;

                    if (validate(potentialQueue)) {
                        sum += 1;

                        if (sum >= THE_BIGGEST) {
                            sum = 0;
                        }
                    }
                }
            }
        }
        return sum;

    }

    private boolean validate(String potentialQueue) {
        if (foundQueues.indexOf(potentialQueue) == -1) {

            foundQueues.add(potentialQueue);
            return true;
        }

        return false;
    }
}
