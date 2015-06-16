package com.didiweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class didiWeatherOpenHelper extends SQLiteOpenHelper {

	/*
	 * Provice�������
	 */
	private static final String CREATE_PROVICE = "create table provice("
			+ "id integer primary key autoincrement," 
			+ "provice_name text,"
			+ "provice_code text)";
	
	/*
	 * City�������
	 */
	private static final String CREATE_CITY ="create table city("
			+"id integer primary key autoincrement,"
			+"city_name text,"
			+"city_code text," 
			+"provice_id integer)";
	
	/*
	 * County�������
	 */
	private static final String CREATE_COUNTY ="create table county"
			+"id integer primary key autoincrement,"
			+"county_name text,"
			+"county_code text,"
			+"city_id integer)";

	// SQLiteOpenHelper��֮������ṩһ�����췽��,why?Ϊandroid�Զ��������ݿ��ṩ����
	public didiWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);//version���������ݿ�İ汾,�ɳ���Ա��ʵ����didiWeatherOpenHelper��ʱ��ָ��
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_PROVICE);//����Provice��
		db.execSQL(CREATE_CITY);//����City��
		db.execSQL(CREATE_COUNTY);//����County��
		//�����������ݿ�ʱ�Żᱻ����,ִ�б�ĳ�ʼ������;android���ݹ��췽���Զ��������ݿ�֮��,���Ż�ص�onCreate()
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
