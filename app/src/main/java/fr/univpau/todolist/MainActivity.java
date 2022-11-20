package fr.univpau.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import fr.univpau.todolist.model.Task;
import fr.univpau.todolist.presenter.TaskAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;
    private TaskAdapter adapter;
    private Collection<Task> collectionsTaskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.TextAjouterToDo);
        ImageButton mImageButton = findViewById(R.id.AjouterTacheMain);

        //mImageButton.setClickable(false);
        mImageButton.setOnClickListener(this);

        // Construire la source de données
        ArrayList<Task> arrayOfTask = new ArrayList<>();
        // Créer l'adaptateur pour convertir le tableau en vues
        adapter = new TaskAdapter(this, arrayOfTask);
        // Attachez l'adaptateur à un ListView
        ListView listView = findViewById(R.id.ListView);
        listView.setAdapter(adapter);

        collectionsTaskAdapter = new ArrayList<>();

        Task mTask = new Task(false, "ACHETER DU PAIN", "2019-11-27", "14:18:00");
        adapter.add(mTask);
        collectionsTaskAdapter.add(mTask);
        mTask = new Task(false, "Aller en cours", "2019-11-27", "14:18:07");
        adapter.add(mTask);
        collectionsTaskAdapter.add(mTask);
        mTask = new Task(false, "SORTIR LE CHIEN", "2019-11-27", "14:18:16");
        adapter.add(mTask);
        collectionsTaskAdapter.add(mTask);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.vider_la_liste: adapter.ViderListe(getApplicationContext(), adapter); break;
            case R.id.retirer:
                collectionsTaskAdapter = adapter.Retirer(collectionsTaskAdapter);
                this.miseAjour(collectionsTaskAdapter);
                break;
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void miseAjour(Collection<Task> collectionsTask) {
        adapter.clear();
        for (Task task : collectionsTask) {
            adapter.add(task);
        }
    }

    @Override
    public void onClick(View view) {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat fDate = new SimpleDateFormat("y-MM-dd", Locale.getDefault());
        SimpleDateFormat fTime = new SimpleDateFormat("h:mm:ss", Locale.getDefault());
        System.out.println(fDate.format(currentTime));

        Task mTask = new Task(false, mTextView.getText().toString(), fDate.format(currentTime), fTime.format(currentTime));
        adapter.add(mTask);
        collectionsTaskAdapter.add(mTask);
    }
}