package com.veryworks.android.basiclist;

import android.content.Context;
import android.content.Intent;
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
        Holder holder = null;

        System.out.println("call getView()========"+position);

        if(view == null) { // 아이템 view 를 재사용하기 위해서 null 체크를 해준다.
            view = LayoutInflater
                    .from(context)
                    .inflate(R.layout.list_item, null);
            // 아이템이 최초 호출될 경우는 Holder 에 위젯들을 담고,
            holder = new Holder(view);
            // 홀더를 View 에 붙여놓는다
            view.setTag(holder);
        }else{
            // View 에 붙어 있는 홀더를 가져온다.
            holder = (Holder) view.getTag();
        }

        holder.textView.setText(data.get(position));

        return view;
    }

    class Holder {
        TextView textView;

        public Holder(View view){
            textView = view.findViewById(R.id.textView);
            init();
        }

        public void init(){
            textView.setOnClickListener(new View.OnClickListener() {
                // 화면에 보여지는 View 는
                // 기본적으로 자신이 속한 컴포넌트의 컨텍스트를 그대로 가지고 있다.
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra("valueKey", textView.getText());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}