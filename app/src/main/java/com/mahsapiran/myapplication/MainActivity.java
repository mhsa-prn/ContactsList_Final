package com.mahsapiran.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvTel;

    private EditText etName;
    private EditText etTel;


    private Button btnRegister;

    private DatabaseReference dbContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName=findViewById(R.id.tvName);
        tvTel=findViewById(R.id.tvTel);
        etName=findViewById(R.id.etName);
        etTel=findViewById(R.id.etTel);
        btnRegister=findViewById(R.id.btnRegister);

        dbContacts= FirebaseDatabase.getInstance().getReference();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=etName.getText().toString();
                String tel=etTel.getText().toString();

                registerContact(name,tel);
            }

        });
    }
    public void registerContact(final String name, final String tel){
        HashMap<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("tel",tel);

        dbContacts.child("Contacts").child(tel).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Contact Saved",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this,"Contact Not Saved",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
