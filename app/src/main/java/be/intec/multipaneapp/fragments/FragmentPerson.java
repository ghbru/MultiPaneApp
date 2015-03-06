package be.intec.multipaneapp.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.intec.multipaneapp.R;
import be.intec.multipaneapp.helper.PersonAdapter;
import be.intec.multipaneapp.model.Person;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPerson extends Fragment {

    private ListView mLvPersons;
    private PersonAdapter adapter;
    private List<Person> persons;
    private OnPersonSelectedListener listener;
    private Person person;

    public interface OnPersonSelectedListener{
        public void showPerson(String person);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnPersonSelectedListener){
            listener = (OnPersonSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString() + " must implement the FragmentPerson.OnPersonSelected Listener");
        }
    }

    public FragmentPerson() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        persons = new ArrayList<>();
        persons.add(new Person(15, "Ann"));
        persons.add(new Person(23, "Indy"));
        persons.add(new Person(19, "Wendy"));
        persons.add(new Person(12, "Lara"));
        persons.add(new Person(28, "VJ"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        mLvPersons = (ListView) view.findViewById(R.id.listView);
        adapter = new PersonAdapter(persons, getActivity());
        mLvPersons.setAdapter(adapter);

        mLvPersons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                person = (Person) parent.getItemAtPosition(position);
                listener.showPerson(person.toString());
            }
        });

        return view;
    }


}
