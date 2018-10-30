package vn.edu.poly.testmob201;

public interface Constant {


    String TABLE_SCORE = "Diem";

    String TB_SCORE_ID = "MaSv";
    String TB_SCORE_CLASS = "MaLop";
    String TB_SCORE_MATH = "DiemToan";
    String TB_SCORE_VANTH = "DiemVan";


    String CREATE_TABLE_SCORE = "CREATE TABLE " + TABLE_SCORE + "(" +
            "" + TB_SCORE_ID + " CHAR(5) PRIMARY KEY NOT NULL," +
            "" + TB_SCORE_CLASS + " NVARCHAR(10) NOT NULL," +
            "" + TB_SCORE_MATH + " NVARCHAR(10)," +
            "" + TB_SCORE_VANTH + " NVARCHAR(10) )";
}