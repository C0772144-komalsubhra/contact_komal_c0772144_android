package com.lambton.contact_komal_c0772144_android;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

public class PersonActivity extends AppCompatActivity {
    DatabaseHelper mDataBase;

    List<Contact> persons;
    List<Contact> searchList = new ArrayList<>();
    SwipeMenuListView listView;
    EditText searchText;
    PersonAdapter personAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        listView = findViewById(R.id.listView);
        persons = new ArrayList<>();
        searchText = findViewById(R.id.searchText);
        mDataBase = new DatabaseHelper(this);
        loadPersons();
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String searchText = s.toString();
                searchList.clear();
                if(!searchText.isEmpty()){

                    for (Contact person:persons) {
                        if(person.getFirstName().contains(searchText)){
                            searchList.add(person);
                        }
                    }
                } else{
                    searchList.addAll(persons);

                }

                personAdapter = new PersonAdapter(PersonActivity.this , R.layout.list_layout , searchList , mDataBase);
                listView.setAdapter(personAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem editItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                editItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                editItem.setWidth((250));

                // set item title fontsize
                editItem.setTitleSize(18);
                // set item title font color
                editItem.setTitleColor(Color.WHITE);
                // add to menu

                editItem.setIcon(R.drawable.ic_edit);
                menu.addMenuItem(editItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth((250));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);

            }
        };

        listView.setMenuCreator(creator);



        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // update

                        Toast.makeText(PersonActivity.this, "update clicked", Toast.LENGTH_SHORT).show();
                        Contact person1 = persons.get(position);
                        personAdapter.updatePerson(person1);
                        persons.clear();
//                        personList.addAll((Collection<? extends PersonClass>) mDatabase.getAllPersons());
                        personAdapter.notifyDataSetChanged();
                        listView.invalidateViews();
                        listView.refreshDrawableState();

                        break;

                    case 1:
                        // delete
                        Toast.makeText(PersonActivity.this, "delete clicked", Toast.LENGTH_SHORT).show();
                        Contact person2 = persons.get(position);
                        int id2 = person2.getId();
                        if(mDataBase.deletePerson(id2))
                            persons.remove(position);
                        personAdapter.notifyDataSetChanged();

                        break;
                }


                return true;
            }
        });

    }


    private void loadPersons() {

        Cursor cursor = mDataBase.getAllPersons();
        if(cursor.moveToFirst()){
            do {
                persons.add(new Contact(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                ));


            }while (cursor.moveToNext());
            cursor.close();

            personAdapter = new PersonAdapter(this, R.layout.list_layout, persons, mDataBase);
            listView.setAdapter(personAdapter);

        }
    }
}
