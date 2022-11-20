package fr.univpau.todolist.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;

import fr.univpau.todolist.R;
import fr.univpau.todolist.model.Task;

public class TaskAdapter extends ArrayAdapter<Task> implements View.OnClickListener {

    public TaskAdapter(@NonNull Context context, @NonNull ArrayList<Task> task) {
        super(context, 0, task);
    }

    /*
    * Pour améliorer les performances, nous devons modifier l'adaptateur personnalisé en appliquant le modèle ViewHolder
    * qui accélère considérablement la population de ListView en mettant en cache les recherches de vue pour un
    * chargement d'élément plus fluide et plus rapide
    */

    private static class ViewHolder {
        TextView mTitle;
        TextView mDate;
        TextView mTime;
        CheckBox mChecked;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Task mTask = getItem(position);

        // Vérifiez si une vue existante est réutilisée, sinon gonflez la vue
        // afficher le cache de recherche stocké dans la balise
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_to_do_task, parent, false);

            // Vue de recherche pour la population de données
            viewHolder.mTitle = convertView.findViewById(R.id.textTitle);
            viewHolder.mDate = convertView.findViewById(R.id.textDate);
            viewHolder.mTime = convertView.findViewById(R.id.textTime);
            viewHolder.mChecked = convertView.findViewById(R.id.Checkbox);

            // Mettez en cache l'objet viewHolder dans la nouvelle vue
            convertView.setTag(viewHolder);
        } else {
            // La vue est en cours de recyclage, récupérez l'objet viewHolder à partir de la balise
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Remplir les données dans la vue modèle à l'aide de l'objet de données
        viewHolder.mTitle.setText(mTask.getTitle());
        viewHolder.mDate.setText(mTask.getDate());
        viewHolder.mTime.setText(mTask.getTime());
        viewHolder.mChecked.setChecked(mTask.isChecked());

        viewHolder.mChecked.setOnClickListener(this);
        viewHolder.mChecked.setTag(position);

        // Renvoyer la vue terminée pour le rendre à l'écran
        return convertView;
    }

    @Override
    public void onClick(View view) {
        int position = (Integer) view.getTag();
        Task task = getItem(position);
        task.setChecked(!task.isChecked());
    }

    public void ViderListe(Context context, TaskAdapter adapter) {
        adapter.clear();
        Toast.makeText(context,
                "La liste à été vidé !",
                Toast.LENGTH_SHORT).show();
    }

    public Collection<Task> Retirer(Collection<Task> collectionsTaskAdapter) {
        Collection<Task> taskCollection = new ArrayList<>();
        for (Task task : collectionsTaskAdapter) {
            if (!task.isChecked()) {
                taskCollection.add(task);
            }
        }
        return taskCollection;
    }
}
