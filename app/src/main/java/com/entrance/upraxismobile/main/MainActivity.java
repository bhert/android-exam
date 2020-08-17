package com.entrance.upraxismobile.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.entrance.upraxismobile.personDetails.PersonDetailsActivity;
import com.entrance.upraxismobile.R;
import com.entrance.upraxismobile.main.model.Person;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{

    @BindView(R.id.recycler_persons)
    RecyclerView recyclerPersons;

    private Unbinder unbinder;

    private RecyclerView.Adapter personsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainActivityContract.Presenter presenter;

    private List<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        unbinder = ButterKnife.bind(this);

        presenter = new MainActivityPresenter(this);
        presenter.init();

        personList = new ArrayList<>();
        recyclerPersons.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerPersons.setLayoutManager(layoutManager);
        recyclerPersons.setItemAnimator(new DefaultItemAnimator());
        personsAdapter = new RecyclerAdapterPersons(personList, person -> showPersonDetails(person));

        recyclerPersons.setAdapter(personsAdapter);

        presenter.loadUsers().observe(this, people -> {
            personList.addAll(people);
            personsAdapter.notifyDataSetChanged();
        });
    }

    private void showPersonDetails(Person person) {
        Intent intent = new Intent(this, PersonDetailsActivity.class);
        intent.putExtra(PersonDetailsActivity.EXTRA_TITLE, person);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onGetPersonsError() {
        Toast.makeText(this, getString(R.string.error_fetch_person), Toast.LENGTH_SHORT).show();
    }
}