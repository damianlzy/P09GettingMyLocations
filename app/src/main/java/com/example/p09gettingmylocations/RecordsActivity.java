package com.example.p09gettingmylocations;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class RecordsActivity extends AppCompatActivity {

    TextView tvNumber;
    ListView lv;
    Button btnRefresh;
    ArrayAdapter<String> adapter;
    ArrayList<String>  data = new ArrayList<String>();
    String folderLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        tvNumber = (TextView)findViewById(R.id.tvNumber);
        lv = (ListView)findViewById(R.id.lv);
        btnRefresh = (Button)findViewById(R.id.btnRefresh);



        Intent i = getIntent();
        folderLocation = i.getStringExtra("folderLocation");

        File targetFile = new File(folderLocation, "p09.txt");

        if (targetFile.exists() == true){
            data.clear();
            try {
                Log.d("location",folderLocation);
                FileReader reader = new FileReader(targetFile);
                BufferedReader br = new BufferedReader(reader);
                String line = br.readLine();
                while (line != null){
                    data.add(line);
                    line = br.readLine();
                }
                br.close();
                reader.close();

                adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, data);

                lv.setAdapter(adapter);

                tvNumber.setText(data.size()+"");



            } catch (Exception e) {
                Toast.makeText(RecordsActivity.this, "Failed to read in RecordsActivity!",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File targetFile = new File(folderLocation, "p09.txt");



                if (targetFile.exists() == true){
                    data.clear();
                    try {
                        Log.d("location",folderLocation);
                        FileReader reader = new FileReader(targetFile);
                        BufferedReader br = new BufferedReader(reader);
                        String line = br.readLine();
                        while (line != null){
                            data.add(line);
                            line = br.readLine();
                        }
                        br.close();
                        reader.close();

                        tvNumber.setText(data.size()+"");
                        adapter.notifyDataSetChanged();




                    } catch (Exception e) {
                        Toast.makeText(RecordsActivity.this, "Failed to read!",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }
            }
        });

    }
}
