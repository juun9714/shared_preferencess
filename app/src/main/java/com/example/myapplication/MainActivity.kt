package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        //해당 액티비티가 처음 실행될 때, 한번만 수행하는 곳 (초기화의 역할을 함 : 최초 값들을 적어주는 것)
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        //함수를 하나 만들 것임
        //TODO: 저장된 데이터를 로드

        loadData() //저장되어 있던 editText 값을 setText해주는 역할

    }

    //private은 있어도 없어도 된다. (접근지시자)
    private fun loadData() {

        val pref = getSharedPreferences("pref",0)
        binding.etHello.setText(pref.getString("name",""))
        //load할 땐 수정모드 켤 필요 없음 pref에서 바로 getString!!
        //1번째 인자 : saveData에서 저장된 key값,
        //2번째 인자 : key값의 데이터가 존재하지 않을 경우의 대체값을 제공
    }

    private fun saveData() {
        val pref = getSharedPreferences("pref",0)
        /*
        app의 내부 폴더 경로에 pref라는 이름으로 저장할거다. (0은 저장 옵션)
        sharedPreferences 기능을 사용할 거다. pref라는 변수를 활용해서
        app 내에서 switch를 on/off 시킬 때 사용. (ex. push 알림 설정)
        app의 중요한 data를 저장하기 위해서 사용한다기보단
        app 내에서 기억하지 못해도 큰 문제가 되지는 않는 data를 다룰 때 사용
        */

        val edit = pref.edit() //수정모드 
        edit.putString("name",binding.etHello.text.toString())
        //1번째 인자 : key값, 2번째 인자 : 실제 담아둘 값
        //꾸러미 안에 String 형태를 담는 행위
        edit.apply()//값을 저장 완료
    }

    override fun onDestroy() {
        //ctrl+o 에서 찾을 수 있다.
        //해당 액티비티가 종료되는 시점에서 호출된다.
        //앱이 종료될 때 해줘야 할 일
        super.onDestroy()
        saveData() //editText 값을 저장하는 역할
    }
}