package com.entrance.upraxismobile.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.entrance.upraxismobile.R;
import com.entrance.upraxismobile.main.model.Person;

import java.util.List;

public class RecyclerAdapterPersons extends RecyclerView.Adapter<RecyclerAdapterPersons.ViewHolder> {
    private List<Person> personList;

    private OnPersonClickListener onPersonClickListener;

    public RecyclerAdapterPersons(List<Person> personList, OnPersonClickListener onPersonClickListener) {
        this.personList = personList;
        this.onPersonClickListener = onPersonClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_person, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(personList.get(position));
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public CardView cardViewContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.tv_name);
            cardViewContainer = itemView.findViewById(R.id.cv_person);
        }

        public void bind(final Person person) {
            textViewName.setText(person.getFirstName() + " " + person.getLastName());
            cardViewContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPersonClickListener.onClick(person);
                }
            });
        }
    }


    public interface OnPersonClickListener {
        void onClick(Person person);
    }
}
