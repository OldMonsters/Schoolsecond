package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
        System.out.println("11111111111111");

    }

    // 创建用户表
    String sqlTeacher = "create table Teacher(teacherNo  varchar(20) not null primary key,"
            +"teacherName varchar(20) not null,"
            + "departmentName varchar(30) not null,"
            + "phoneNumber1 varchar(20) not null,"
            + "phoneNumber2 varchar(20) null,"
            + "weChat varchar(30) null,"
            + "emailAddress varchar(30) null,"
            + "remarks varchar(50) null,"
            + "photo varchar(255)  null)";

    //创建机构表
    String sqlDepartment = "create table Department(departmentName varchar(30) not null primary key,"
            + "constraint departmentFK foreign key (departmentName) references Teacher(departmentName))";


    @Override
    public void onCreate(SQLiteDatabase arg0) {

        // TODO Auto-generated method stub
        arg0.execSQL(sqlTeacher);

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }
}
