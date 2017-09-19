package com.veryworks.android.basiclist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 리스트 사용하기
 */
public class MainActivity extends AppCompatActivity {

    List<String> data = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 데이터를 정의 (100개의 가상 값을 담는다)
        for(int i=0 ;i <100;i++){
            data.add("임시값 "+i );
        }

        // 2. 데이터와 리스트뷰를 연결하는 아답터를 생성
        CustomAdapter adapter = new CustomAdapter(this, data);
        // 3. 아답터와 리스트뷰를 연결
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}

// 기본 아답터 클래스를 상속받아서 구현
class CustomAdapter extends BaseAdapter {
    // 데이터 저장소를 아답터 내부에 두는것이 컨트롤 하기 편하다
    List<String> data;
    Context context;

    // 생성자
    public CustomAdapter(Context context, List<String> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() { // 현재 데이터의 총 개수
        return data.size();
    }

    // 현재 뿌려질 데이터를 리턴해준다
    @Override
    public Object getItem(int position) { //<- 호출되는 목록아이템의 위치
        return data.get(position);
    }

    // 뷰의 아이디를 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 목록에 나타나는 아이템 하나하나를 그려준다.
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        // 레이아웃 인플레이터로 xml 파일을 View 객체로 변환
        View itemView = LayoutInflater
                .from(context)
                .inflate(R.layout.list_item, null);

        // 뷰안에 있는 텍스트뷰 위젯에 값을 입력한다.
        TextView textView = (TextView)itemView.findViewById(R.id.textView);
        textView.setText(data.get(position));

        return itemView;
    }
}