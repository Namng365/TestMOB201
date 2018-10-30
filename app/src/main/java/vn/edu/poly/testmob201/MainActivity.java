package vn.edu.poly.testmob201;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.testmob201.adapter.AdapterScore;
import vn.edu.poly.testmob201.database.DatabaseHelper;
import vn.edu.poly.testmob201.model.Score;
import vn.edu.poly.testmob201.sqlitedao.ScoreDAO;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcVew;
    private AdapterScore adapterScore;
    private List<Score> scores;
    private DatabaseHelper databaseHelper;
    private ScoreDAO scoreDAO;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        scoreDAO = new ScoreDAO(databaseHelper);
        scores = new ArrayList<>();
        scores = scoreDAO.getAllScores();
        rcVew = findViewById(R.id.rcView);
        adapterScore = new AdapterScore(this, scores, scoreDAO);
        rcVew.setAdapter(adapterScore);
        linearLayoutManager = new LinearLayoutManager(this);
        rcVew.setLayoutManager(linearLayoutManager);
        final EditText edtMaSv;
        final EditText edtMaLop;
        final EditText edtToan;
        final EditText edtVan;
        Button btnAdd;

        edtMaSv = findViewById(R.id.edtMaSv);
        edtMaLop = findViewById(R.id.edtMaLop);
        edtToan = findViewById(R.id.edtToan);
        edtVan = findViewById(R.id.edtVan);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("btnAddType", "btnAddType");

                Score score = scoreDAO.getScoreByID(edtMaSv.getText().toString().trim());

                if (score == null) {
                    score = new Score();
                    String id = edtMaSv.getText().toString().trim();
                    String class1 = edtMaLop.getText().toString().trim();
                    String math = edtToan.getText().toString().trim();
                    String vanth = edtVan.getText().toString().trim();

                    score.id = id;
                    score.classId = class1;
                    score.math = math;
                    score.vanth = vanth;
                    adapterScore.notifyDataSetChanged();


                    if (TextUtils.isEmpty(id)|| TextUtils.isEmpty(class1) || TextUtils.isEmpty(math) || TextUtils.isEmpty(vanth)) {
                        Toast.makeText(MainActivity.this, "Khong duoc bo trong!", Toast.LENGTH_SHORT).show();

                    }else if (id.length()>10||class1.length()>10||math.length()>10||vanth.length()>10){
                        Toast.makeText(MainActivity.this, "Khong duoc nhap qua gioi han", Toast.LENGTH_SHORT).show();

                    } else {
                        scores.add(score);
//                        rcVew.setAdapter(adapterFood);
                        adapterScore.notifyDataSetChanged();

//                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
                    }

            }
            }
        });
    }

    public void pH(View view) {

    }
}
