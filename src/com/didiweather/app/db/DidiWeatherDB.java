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
 * 数据库工具类,封装一些常用的数据库操作,包括常见的CRUD操作
 * @author creazylee
 * didiWeatherDB这个类使用单例模式,定义构造方法私有,提供一个对外的公共访问方法
 */
public class DidiWeatherDB {
	
	private static final String DB_NAME ="didi_weather";//自定义数据库名称
	private static final int VERSION = 1;//自定义数据库版本号
	private SQLiteDatabase db;//数据库对象(对应于DB_NAME数据库)
	private static DidiWeatherDB didiWeatherDB;

	private DidiWeatherDB(Context context){
		didiWeatherOpenHelper dbHelper = new didiWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();//打开数据库连接,返回数据库对应的对象db,即用于操作数据库的实例
	}
	
	/***
	 * 对外提供一个DidiWeatherDB类的实例对象
	 * @param context
	 * @return DidiWeatherDB实例对象
	 */
	public static DidiWeatherDB getInstance(Context context){
		if(didiWeatherDB == null){
			didiWeatherDB = new DidiWeatherDB(context);
		}
		return didiWeatherDB;
	}
	
	//下面提供操作数据库的方法,这里使用的是SQLiteDatabase对象的insert,,delete,update,query方法
	/***
	 * 将Provice实体数据存储到数据库中
	 * @param provice
	 */
	public void saveProvice(Provice provice){
		ContentValues proviceValues = new ContentValues();
		proviceValues.put("provice_name", provice.getProviceName());//第一参数为数据列的列名
		proviceValues.put("provice_code", provice.getProviceCode());
		db.insert("provice", null, proviceValues);//第一个参数为插入数据的数据表的表名,第二个参数为强行插入null值的数据列的列名,第三个参数为要插入的数据
		//insert()完全可以使用db.execSQL(String sql)手动书写sql来替换完成;
		//long rowId = db.insert("provice", null, proviceValues);//可以返回新添加记录的行号,此行号与主键id无关
		
	}
	
	/***
	 * 从数据库中读取全国所有省份的信息
	 * @return
	 */
	public List<Provice> loadProvices(){
		
		List<Provice> provices = new ArrayList<Provice>();
		Provice provice = new Provice();
		Cursor cursor = db.query("provice", null, null, null, null, null, null, null);//查询所有记录
		while(cursor.moveToNext()){
			provice.setId(cursor.getInt(cursor.getColumnIndex("id")));//通过列的名称找到列的索引,再通过列的索引找到列的值
			provice.setProviceName(cursor.getString(cursor.getColumnIndex("provice_name")));
			provice.setProviceCode(cursor.getString(cursor.getColumnIndex("provice_code")));
			provices.add(provice);
		}
		
		return provices;
	}
	
	/***
	 * 将City实体数据存储到数据库中
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
	 * 从数据库读取某省份下的所有城市信息
	 * @param proviceId 省份id
	 * @return
	 */
	public List<City> loadCities(int proviceId){
		List<City> citys = new ArrayList<City>();
		City city = new City();
		//Cursor cursor = db.query("city", null, null, null, null, null, null, null);
		Cursor cursor = db.query("city", null, "provice_id = ?", new String[]{String.valueOf(proviceId)}, null, null, null, null);//增加带有查询条件的查询
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
	 * 将County实体数据 存储到数据库中
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
	 * 从数据库读取某城市下的所有县信息
	 * @param cityId 城市id
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
