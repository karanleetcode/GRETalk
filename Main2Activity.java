
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity {
    final DBHandler dbHandler  = new DBHandler(this);

    ImageView speaker;
    Button favlistbutton;
    TextView sentence,meaning,word,syn;
    TextToSpeech t1;
    DBHandler2 dbHandler2 = new DBHandler2(this);
    DBHandler3 dbHandler3 = new DBHandler3(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent in = getIntent();
        String tv1= in.getExtras().getString("wordname");

        favlistbutton = (Button) findViewById(R.id.favlistbutton);
        speaker  = (ImageView) findViewById(R.id.speaker);

        sentence = findViewById(R.id.sentence);
        meaning  = findViewById(R.id.meaning);
        word     = findViewById(R.id.wordName);
        syn      = findViewById(R.id.syn);

        final String[] data = dbHandler.getAllDetailsOfWord(tv1).split(":");   //getting whole data from database
        final String[] synn = data[5].split("/");

        word.setText(data[1]);   //word
        meaning.setText(Html.fromHtml("<b>"+": "+"</b>"+data[2]));   //sentence
        sentence.setText(Html.fromHtml("<b>"+" Example : "+"</b>"+data[3]));    //meaning
        syn.setText(Html.fromHtml("<b>"+"Synonyms: "+"</b>"+synn[0]+", "+synn[1]+", "+synn[2]+", "+synn[3]+","+ synn[4]+" ,"+synn[5]));


        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Timer buttonTimer = new Timer();

                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                t1.speak("The word is "+data[1]+" "+" the meaning is "+data[2]
                                        , TextToSpeech.QUEUE_FLUSH, null);
                            }
                        });
                    }
                }, 500);
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.US);
                            t1.setSpeechRate(1.4f);
                        }

                    }
                });
            }
        });
        favlistbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        if(dbHandler2.wordExist(data[1])!=0){
                            Toast.makeText(getApplicationContext(),"Word already exists in the Fav list",Toast.LENGTH_SHORT).show();
                        }else{
                            dbHandler2.addContact(new Contacts2(0,data[1],"1"));
                            dbHandler.updateStarTo1(data[1]);
                            Toast.makeText(getApplicationContext(),dbHandler.getAllDetailsOfWord(data[1]),Toast.LENGTH_SHORT).show();
                        }
            }
        });

    }
    class ParseURL81 extends AsyncTask<String, Void, Void> {
        public String s, x;
        public String tex12, tex13;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                x = strings[0];
                org.jsoup.nodes.Document doc = Jsoup.connect("http://www.thesaurus.com/browse/"+x+"?s=t").get();
                //  Elements img = doc.select("div.answer_text");
                Elements tex1 = doc.select("span.text");
                tex13 = tex1.toString();
                //tex12 = img.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @TargetApi(24)
        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            org.jsoup.nodes.Document doc2 = Jsoup.parse(tex13);
            String content45 = doc2.body().text();
            syn.setText(content45.substring(0,600).replaceAll(" ",",")+"..and many more");
            // Document doc3 = Jsoup.parse(tex12);
            // String content2 = doc3.body().text();
            //   mapText.setText(content);
            //  qAnswer.setText(" " + content45.substring(0, 1000));
            //Typeface custom61 = Typeface.createFromAsset(getAssets(), "fonts/opensan.ttf");
            //qAnswer.setTypeface(custom61);
        }
    }

    public void mt(String message)
    {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}
