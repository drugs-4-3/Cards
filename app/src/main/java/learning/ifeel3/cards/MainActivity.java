package learning.ifeel3.cards;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    ArrayList<ImageView> cardsList = new ArrayList<>(5);
    TypedArray cardImages;
    final Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardsList.add((ImageView) findViewById(R.id.card1));
        cardsList.add((ImageView) findViewById(R.id.card2));
        cardsList.add((ImageView) findViewById(R.id.card3));
        cardsList.add((ImageView) findViewById(R.id.card4));
        cardsList.add((ImageView) findViewById(R.id.card5));

        cardImages = getResources().obtainTypedArray(R.array.cards_array);
    }

    private void shuffle() {
        int [] arr = generateNonRepeatingNumbers(5, 52);
        int index = 0;
        for (ImageView card: cardsList) {
//            int rndInt = random.nextInt(cardImages.length());
            int rndInt = arr[index];
            Drawable drawable = cardImages.getDrawable(rndInt);
            card.setImageDrawable(drawable);
            index++;
        }
    }

    public void shuffleCards(View view) {
        shuffle();
    }

    private int[] generateNonRepeatingNumbers(int howMany, int range) {
        if (howMany <= range && howMany > 0) {
            int [] arr = new int[range];
            for(int i = 0; i < range; i++) {
                arr[i] = i;
            }
            arr = shuffleArray(arr);
            int [] result = new int[howMany];
            for (int i = 0; i < howMany; i++) {
                result[i] = arr[i];
            }
            return result;
        } else {
            return null;
        }
    }

    private int[] shuffleArray(int[] arr) {
        // fisher-yates algorithm for shuffling arrays
        int length = arr.length;
        for (int i = length - 1; i > 0; i--) {
            int j = random.nextInt(i);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }
}
