package com.entrance.upraxismobile.personDetails;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.entrance.upraxismobile.R;
import com.entrance.upraxismobile.main.model.Person;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PersonDetailsActivity extends AppCompatActivity implements PersonDetailsContract.View {

    @BindView(R.id.tv_firstName)
    TextView tvFirstName;
    @BindView(R.id.tv_lastName)
    TextView tvLastName;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_emailAddress)
    TextView tvEmailAddress;
    @BindView(R.id.tv_mobileNumber)
    TextView tvMobileNumber;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_contactPerson)
    TextView tvContactPerson;
    @BindView(R.id.tv_contactPersonsMobileNumber)
    TextView tvContactPersonsEmailAddress;
    Unbinder unbinder;

    public static final String EXTRA_TITLE = "EXTRA_PERSON_DETAILS";
    private PersonDetailsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);
        initView();
    }

    private void initView() {
        unbinder = ButterKnife.bind(this);
        presenter = new PersonDetailsPresenter(this);

        Person person = getIntent().getParcelableExtra(EXTRA_TITLE);
        setPersonDetails(person);
    }

    private void setPersonDetails(Person person) {
        tvFirstName.setText(person.getFirstName());
        tvLastName.setText(person.getLastName());
        tvBirthday.setText(person.getBirthday());
        tvAge.setText(presenter.getAge(person.getBirthday()));
        tvAddress.setText(person.getAddress());
        tvEmailAddress.setText(person.getEmailAddress());
        tvMobileNumber.setText(person.getMobileNumber());
        tvContactPerson.setText(person.getContactPerson());
        tvContactPersonsEmailAddress.setText(person.getContactPersonsMobileNumber());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onGetAgeError() {
        tvAge.setText("N/A");
    }
}