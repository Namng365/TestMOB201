package vn.edu.poly.testmob201.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.testmob201.Constant;
import vn.edu.poly.testmob201.database.DatabaseHelper;
import vn.edu.poly.testmob201.model.Score;



public class ScoreDAO implements Constant {

    private DatabaseHelper databaseHelper;


    public ScoreDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;

    }


    public long insertScore(Score score) {

        // xin quyen ghi!!!
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        //  Tao doi tuong de truyen du lieu la ContentValues
        ContentValues contentValues = new ContentValues();

        // Dua gia tri tuong ung tu User vao ContentValues

        contentValues.put(TB_SCORE_ID, score.id);

        contentValues.put(TB_SCORE_CLASS, score.classId);

        contentValues.put(TB_SCORE_MATH, score.math);
        contentValues.put(TB_SCORE_VANTH, score.vanth);


        // Viet cau lenh insert vao DB

        long result = sqLiteDatabase.insert(TABLE_SCORE, null, contentValues);

        Log.e("insertScore", "ID = " + score.id);

        sqLiteDatabase.close();

        return result;
    }

    public long updateScore(Score score) {

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        //  Tao doi tuong de truyen du lieu la ContentValues
        ContentValues contentValues = new ContentValues();

        // Dua gia tri tuong ung tu User vao ContentValues
        contentValues.put(TB_SCORE_ID, score.id);

        contentValues.put(TB_SCORE_CLASS, score.classId);

        contentValues.put(TB_SCORE_MATH, score.math);
        contentValues.put(TB_SCORE_VANTH, score.vanth);


        // updating row
        long result =  db.update(TABLE_SCORE, contentValues, TB_SCORE_ID + " = ?",
                new String[]{String.valueOf(score.id)});


        return result;
    }


    public long deleteScore(String id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long result = db.delete(TABLE_SCORE, TB_SCORE_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return result;
    }


    public List<Score> getAllScores() {
        List<Score> scores = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        // viet cau lenh truy can toan bo danh sach user;
        String SELECT_ALL_TYPE_BOOKS = "SELECT * FROM " + TABLE_SCORE;
        // cursor la doi tuong de chua ket qua truy van
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_TYPE_BOOKS, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(TB_SCORE_ID));
                String class1 = cursor.getString(cursor.getColumnIndex(TB_SCORE_CLASS));
                String math = cursor.getString(cursor.getColumnIndex(TB_SCORE_MATH));
                String vanth = cursor.getString(cursor.getColumnIndex(TB_SCORE_VANTH));


                Score score = new Score();
                score.id = id;
                score.classId = class1;
                score.math = math;
                score.vanth = vanth;

                scores.add(score);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return scores;
    }

    public Score getScoreByID(String id) {
        Score score = null;
        // xin quyen ghi!!!
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        // Tao cau lenh query voi Cursor
        Cursor cursor = sqLiteDatabase.query
                (TABLE_SCORE, new String[]{TB_SCORE_ID, TB_SCORE_CLASS, TB_SCORE_MATH, TB_SCORE_VANTH},
                        TB_SCORE_ID + "=?", new String[]{id},
                        null, null, null);

        // kiem tra xem cursor !=null va co chua gia tri
        if (cursor != null && cursor.moveToFirst()) {

            String id1 = cursor.getString(cursor.getColumnIndex(TB_SCORE_ID));
            String class1 = cursor.getString(cursor.getColumnIndex(TB_SCORE_CLASS));
            String math = cursor.getString(cursor.getColumnIndex(TB_SCORE_MATH));
            String vanth = cursor.getString(cursor.getColumnIndex(TB_SCORE_VANTH));


            Score score1 = new Score();
            score.id = id1;
            score.classId = class1;
            score.math = math;
            score.vanth = vanth;

        }

        cursor.close();
        return score;

    }


}
