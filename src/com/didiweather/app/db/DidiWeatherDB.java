package com.didiweather.app.db;

import java.util.ArrayList;
import java.util.List;

import com.didiweather.app.model.City;
import com.didiweather.app.model.County;
import com.didiweather.app.model.Provice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * ���ݿ⹤����,��װһЩ���õ����ݿ����,����������CRUD����
 * @author creazylee
 * didiWeatherDB�����ʹ�õ���ģʽ,���幹�췽��˽��,�ṩһ������Ĺ������ʷ���
 */
public class DidiWeatherDB {
	
	private static final String DB_NAME ="didi_weather";//�Զ������ݿ�����
	private static final int VERSION = 1;//�Զ������ݿ�汾��
	private SQLiteDatabase db;//���ݿ����(��Ӧ��DB_NAME���ݿ�)
	private static DidiWeatherDB didiWeatherDB;

	private DidiWeatherDB(Context context){
		didiWeatherOpenHelper dbHelper = new didiWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();//�����ݿ�����,�������ݿ��Ӧ�Ķ���db,�����ڲ������ݿ��ʵ��
	}
	
	/***
	 * �����ṩһ��DidiWeatherDB���ʵ������
	 * @param context
	 * @return DidiWeatherDBʵ������
	 */
	public static DidiWeatherDB getInstance(Context context){
		if(didiWeatherDB == null){
			didiWeatherDB = new DidiWeatherDB(context);
		}
		return didiWeatherDB;
	}
	
	//�����ṩ�������ݿ�ķ���,����ʹ�õ���SQLiteDatabase�����insert,,delete,update,query����
	/***
	 * ��Proviceʵ�����ݴ洢�����ݿ���
	 * @param provice
	 */
	public void saveProvice(Provice provice){
		ContentValues proviceValues = new ContentValues();
		proviceValues.put("provice_name", provice.getProviceName());//��һ����Ϊ�����е�����
		proviceValues.put("provice_code", provice.getProviceCode());
		db.insert("provice", null, proviceValues);//��һ������Ϊ�������ݵ����ݱ�ı���,�ڶ�������Ϊǿ�в���nullֵ�������е�����,����������ΪҪ���������
		//insert()��ȫ����ʹ��db.execSQL(String sql)�ֶ���дsql���滻���;
		//long rowId = db.insert("provice", null, proviceValues);//���Է�������Ӽ�¼���к�,���к�������id�޹�
		
	}
	
	/***
	 * �����ݿ��ж�ȡȫ������ʡ�ݵ���Ϣ
	 * @return
	 */
	public List<Provice> loadProvices(){
		
		List<Provice> provices = new ArrayList<Provice>();
		Provice provice = new Provice();
		Cursor cursor = db.query("provice", null, null, null, null, null, null, null);//��ѯ���м�¼
		while(cursor.moveToNext()){
			provice.setId(cursor.getInt(cursor.getColumnIndex("id")));//ͨ���е������ҵ��е�����,��ͨ���е������ҵ��е�ֵ
			provice.setProviceName(cursor.getString(cursor.getColumnIndex("provice_name")));
			provice.setProviceCode(cursor.getString(cursor.getColumnIndex("provice_code")));
			provices.add(provice);
		}
		
		return provices;
	}
	
	/***
	 * ��Cityʵ�����ݴ洢�����ݿ���
	 * @param city
	 */
	public void saveCity(City city){
		ContentValues cityValues = new ContentValues();
		cityValues.put("city_name", city.getCityName());
		cityValues.put("city_code", city.getCityCode());
		cityValues.put("provice_id", city.getProviceId());
		db.insert("city", null, cityValues);
	}
	
	/***
	 * �����ݿ��ȡĳʡ���µ����г�����Ϣ
	 * @param proviceId ʡ��id
	 * @return
	 */
	public List<City> loadCities(int proviceId){
		List<City> citys = new ArrayList<City>();
		City city = new City();
		//Cursor cursor = db.query("city", null, null, null, null, null, null, null);
		Cursor cursor = db.query("city", null, "provice_id = ?", new String[]{String.valueOf(proviceId)}, null, null, null, null);//���Ӵ��в�ѯ�����Ĳ�ѯ
		while(cursor.moveToNext()){
			city.setId(cursor.getInt(cursor.getColumnIndex("id")));
			city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
			city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
			city.setProviceId(cursor.getInt(cursor.getColumnIndex("provice_id")));
			citys.add(city);
		}
		return citys;
	}
	
	/***
	 * ��Countyʵ������ �洢�����ݿ���
	 * @param county
	 */
	public void saveCounty(County county){
		ContentValues countyValues = new ContentValues();
		countyValues.put("county_name", county.getCountyName());
		countyValues.put("county_code", county.getCountyCode());
		countyValues.put("city_id", county.getCityId());
		db.insert("county", null, countyValues);
		
	}
	
	/***
	 * �����ݿ��ȡĳ�����µ���������Ϣ
	 * @param cityId ����id
	 * @return
	 */
	public List<County> loadCounties(int cityId){
		List<County> countys = new ArrayList<County>();
		County county = new County();
		//Cursor cursor = db.query("county", null, null, null, null, null, null, null);
		Cursor cursor = db.query("county", null, "city_id = ?",new String[]{String.valueOf(cityId)},null,null, null, null);
		while(cursor.moveToNext()){
			county.setId(cursor.getInt(cursor.getColumnIndex("id")));
			county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
			county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
			county.setCityId(cursor.getInt(cursor.getColumnIndex("city_id")));
			countys.add(county);
		}
		return countys;
	}
	

}
