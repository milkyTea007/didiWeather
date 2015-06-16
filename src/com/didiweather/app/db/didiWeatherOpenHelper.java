package com.didiweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class didiWeatherOpenHelper extends SQLiteOpenHelper {

	/*
	 * Provice表建表语句
	 */
	private static final String CREATE_PROVICE = "create table provice("
			+ "id integer primary key autoincrement," 
			+ "provice_name text,"
			+ "provice_code text)";
	
	/*
	 * City表建表语句
	 */
	private static final String CREATE_CITY ="create table city("
			+"id integer primary key autoincrement,"
			+"city_name text,"
			+"city_code text," 
			+"provice_id integer)";
	
	/*
	 * County表建表语句
	 */
	private static final String CREATE_COUNTY ="create table county"
			+"id integer primary key autoincrement,"
			+"county_name text,"
			+"county_code text,"
			+"city_id integer)";

	// SQLiteOpenHelper的之类必须提供一个构造方法,why?为android自动创建数据库提供方法
	public didiWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);//version代表创建数据库的版本,由程序员在实例化didiWeatherOpenHelper的时候指定
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_PROVICE);//创建Provice表
		db.execSQL(CREATE_CITY);//创建City表
		db.execSQL(CREATE_COUNTY);//创建County表
		//初次生成数据库时才会被调用,执行表的初始化工作;android根据构造方法自动生成数据库之后,接着会回调onCreate()
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
