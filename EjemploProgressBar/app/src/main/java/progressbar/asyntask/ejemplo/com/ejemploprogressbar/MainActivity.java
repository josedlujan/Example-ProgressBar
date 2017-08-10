package progressbar.asyntask.ejemplo.com.ejemploprogressbar;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ProgressBar progressBar;
    Button boton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.texto);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setMax(100);
        progressBar.setVisibility(View.GONE);
        boton = (Button) findViewById(R.id.boton);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyAsyncTask().execute(100);
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);

            }
        });

    }


    public class MyAsyncTask extends AsyncTask<Integer, Integer, String>{

        @Override
        protected void onPreExecute() {
            textView.setText("Starting");
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.GONE);
            textView.setText(s);
            boton.setText("Restart");
        }

        @Override
        protected String doInBackground(Integer... params) {
                int max = 100;
                for (int i = 1; i <max; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return "Terminado";
        }



        @Override
        protected void onProgressUpdate(Integer... values) {
            textView.setText("Running..."+values[0]);
            progressBar.setProgress(values[0]);
        }
    }
}
