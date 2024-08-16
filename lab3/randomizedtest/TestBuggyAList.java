package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void TestThreeAddThreeRemove() {
        AListNoResizing<Integer> correctAList = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();
        for(int i = 4; i <= 6; i += 1) {
            correctAList.addLast(i);
            buggyAList.addLast(i);
        }
        for(int n = 0; n < 3; n += 1) {
            assertEquals(correctAList.removeLast(), buggyAList.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BL = new BuggyAList<>();

        int N = 2000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int sizeB = BL.size();
                assertEquals(size, sizeB);
            } else if(operationNumber == 2) {
                //getLast
                if(L.size() > 0) {
                    int last = L.getLast();
                    int lastB = BL.getLast();
                    assertEquals(last, lastB);
                }
            }else if(operationNumber == 3) {
                if(L.size() > 0) {
                    int last = L.removeLast();
                    int lastB = BL.removeLast();
                    assertEquals(last, lastB);
                }
            }
        }
    }
}
