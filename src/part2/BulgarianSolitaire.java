// Rafayel Keshishyan 
// 07 Bulgarian Solitaire (Part II) 
// 6/9/2023 
// CS/IS 112

package part2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BulgarianSolitaire {
    ArrayList<Integer> cardPiles = new ArrayList<Integer>();
    Random randomGenerator = new Random();
    boolean continueGenerating = true;
    boolean continuePlaying = true;

    public static void main(String[] args) {
        BulgarianSolitaire game = new BulgarianSolitaire();
    }

    public BulgarianSolitaire() {
        System.out.println("Started");
        int totalCards = 0;
        while (continueGenerating) {
            if (totalCards < 45) {
                cardPiles.add(randomGenerator.nextInt(46 - totalCards));
            } else {
                continueGenerating = false;
            }
            totalCards = 0;
            for (int i = 0; i < cardPiles.size(); i++) {
                totalCards += cardPiles.get(i);
            }
            removeZeros(cardPiles);
            System.out.println(cardPiles);
        }
        System.out.println("Starting Values Are Generated");
        while (continuePlaying) {
            solitaireStep();
            System.out.println(cardPiles);
            if (checkCards()) {
                continuePlaying = false;
            }
        }
        Collections.sort(cardPiles);
        System.out.println("Game Over!!!");
        System.out.println(cardPiles);
    }

    public void removeZeros(ArrayList<Integer> list) {
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j) == 0) {
                list.remove(j);
                j--;
            }
        }
    }

    public void solitaireStep() {
        int cardsRemoved = 0;
        for (int i = 0; i < cardPiles.size(); i++) {
            int value = cardPiles.get(i);
            cardPiles.set(i, value - 1);
            cardsRemoved++;
        }
        removeZeros(cardPiles);
        cardPiles.add(cardsRemoved);
    }

    public boolean checkCards() {
        ArrayList<Integer> expectedCardPiles = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++) {
            expectedCardPiles.add(i);
        }
        ArrayList<Integer> sortedCardPiles = new ArrayList<Integer>(cardPiles);
        Collections.sort(sortedCardPiles);
        boolean isSameConfiguration = true;
        if (sortedCardPiles.size() != expectedCardPiles.size()) {
            isSameConfiguration = false;
        }
        for (int i = 0; i < sortedCardPiles.size(); i++) {
            if (sortedCardPiles.size() == expectedCardPiles.size()) {
                if (sortedCardPiles.get(i) != expectedCardPiles.get(i)) {
                    isSameConfiguration = false;
                }
            }
        }
        return isSameConfiguration;
    }
}