package com.example.twoactivities;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public static final String EXTRA_MESSAGE = "com.example.twoactivities.extra.MESSAGE";

    private EditText mMessageEditText;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "start");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "pause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "destroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "stop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "resume");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "----");
        Log.d(LOG_TAG, "onCreate");

        mMessageEditText = findViewById(R.id.editText_main);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);

        if (savedInstanceState != null) {
            // bundleクラスに保存したreply_visibleを取得する
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");

            // trueなら返信テキストを表示する
            if (isVisible) {
                Log.d(LOG_TAG, "実行されてる？");
                // ヘッダーテキストを可視化する
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                // 返信メッセージを取得する
                mReplyTextView.setText(savedInstanceState.getString("reply_text"));
                // 返信メッセージを可視化する
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button Clicked!");
        // 画面遷移のためにintentクラスを作成する
        Intent intent = new Intent(this, SecondActivity.class);
        // 入力された値を取得する
        String message = mMessageEditText.getText().toString();
        // Intentにデータを追加する
        intent.putExtra(EXTRA_MESSAGE, message);
        // 遷移後、戻ったときにtextViewに文字が残るのでクリアする
        mMessageEditText.getText().clear();
        //　画面遷移する
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // ヘッダーテキスト（返信がありました）が表示されているか
        if (mReplyHeadTextView.getVisibility() == View.VISIBLE) {
            Log.d(LOG_TAG, "Bundleに保存するぞ！！");
            // Bundleにデータ保存する
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text", mReplyTextView.getText().toString());
        } else {
            Log.d(LOG_TAG, "Bundle動いてないぞ！！");
        }
    }
}
