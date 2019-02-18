package com.samuvel.pandian.microlearning.subscription.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.samuvel.pandian.microlearning.R;
import com.samuvel.pandian.microlearning.Utils.Preferences;
import com.samuvel.pandian.microlearning.learninglinks.view.LearningLinksActivity;
import com.samuvel.pandian.microlearning.subscription.presenter.SubscribePresenter;
import com.samuvel.pandian.microlearning.subscription.presenter.SubscribePresenterContract;

public class SubscribeActivity extends AppCompatActivity implements SubscribeViewContract,
        View.OnClickListener {
    private SubscribePresenterContract mSubscribePresenterContract;
    private EditText mTopicEditText;
    private Button mSubscribeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
        mSubscribePresenterContract = new SubscribePresenter();
        ((SubscribePresenter) mSubscribePresenterContract)
                .attachView(getApplicationContext(), this);
        initView();
        setClickListener();
    }

    private void initView() {
        mTopicEditText = findViewById(R.id.topic_edit_text);
        mSubscribeButton = findViewById(R.id.subscribe_button);
    }

    private void setClickListener() {
        mSubscribeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.subscribe_button:
                validateTopicAndSubscribe();
                break;
        }
    }

    private void validateTopicAndSubscribe() {
        final String topic = mTopicEditText.getText().toString().trim();
        if (!topic.isEmpty()) {
            getDeviceToken().addOnSuccessListener(new OnSuccessListener<String>() {
                @Override
                public void onSuccess(String s) {
                    String deviceToken = s;
                    String userId = getUserId();
                    if (!deviceToken.isEmpty() && !userId.isEmpty())
                        getPresenter().subscribe(deviceToken, userId, topic);
                    else
                        showToast("something went wrong!");
                }
            });
        } else {
            showToast("We don't know what's inside your mind :(");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((SubscribePresenter) mSubscribePresenterContract).detachView();
    }

    @Override
    public void showTopicError() {

    }

    @Override
    public void onSubscribed() {
        showToast("Subscribed!");
        startActivity(new Intent(this, LearningLinksActivity.class));
        Preferences.saveSubscribedTopic(mTopicEditText.getText().toString().trim(), getApplicationContext());
    }

    private Task<String> getDeviceToken() {
        final TaskCompletionSource<String> tcs = new TaskCompletionSource<>();
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this,
                new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        String newToken = instanceIdResult.getToken();
                        tcs.setResult(newToken);
                    }
                });
        return tcs.getTask();
    }

    private String getUserId() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        return user.getUid();
    }

    private SubscribePresenterContract getPresenter() {
        if (mSubscribePresenterContract == null) {
            mSubscribePresenterContract = new SubscribePresenter();
        }
        return mSubscribePresenterContract;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getSubscribedTopic(getApplicationContext()) != null) {
            startActivity(new Intent(this, LearningLinksActivity.class));
        }
    }
}
