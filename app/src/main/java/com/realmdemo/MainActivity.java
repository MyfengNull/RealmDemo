package com.realmdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.realmdemo.bean.User;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by yangshirong on 2016/10/20 9:56.
 * 邮箱 ysr200808@163.com
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private Button btn_Add;
    private Button btn_Query;
    private Button btn_Update;
    private Button btn_Delete;
    private RealmConfiguration realmConfiguration;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_Add = (Button) findViewById(R.id.btn_Add);
        btn_Query = (Button) findViewById(R.id.btn_Query);
        btn_Update = (Button) findViewById(R.id.btn_Update);
        btn_Delete = (Button) findViewById(R.id.btn_Delete);

        btn_Add.setOnClickListener(this);
        btn_Query.setOnClickListener(this);
        btn_Update.setOnClickListener(this);
        btn_Delete.setOnClickListener(this);
        context = this;

        realmConfiguration = new RealmConfiguration
                .Builder(this)
                .build();

        realm = Realm.getInstance(realmConfiguration);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Add:
                testAdd(realm);
                break;
            case R.id.btn_Query:
                testQuery();
                break;
            case R.id.btn_Update:
                testUpdate();
                break;
            case R.id.btn_Delete:
                testDelete();
                break;
        }
    }
    //删除数据
    private void testDelete() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).equalTo("name", "user1").findFirst();
                if (null!=user) {
                    user.deleteFromRealm();
                }
                Toast.makeText(context, "删除成功", Toast.LENGTH_LONG).show();
            }
        });
    }

    //更新数据
    private void testUpdate() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).equalTo("name", "用户9").findFirst();
                if (null != user) {
                    user.setName("Feng");
                    user.setAge(20);
                }
                Toast.makeText(context, "更新成功", Toast.LENGTH_LONG).show();
            }
        });
    }

    //查询数据
    private void testQuery() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                List<User> users = realm.where(User.class).findAll();
                for (User user : users
                        ) {
                    Toast.makeText(context, "id:" + user.getId() + " name:" + user.getName() + " age:" + user.getAge(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //添加数据
    private void testAdd(Realm realm) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (int i = 0; i < 10; i++) {
                    User user = realm.createObject(User.class);
                    user.setName("用户" + i);
                    user.setAge(10 + i);
                    user.setId(UUID.randomUUID().toString());
                }
                Toast.makeText(context, "10条数据添加成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
