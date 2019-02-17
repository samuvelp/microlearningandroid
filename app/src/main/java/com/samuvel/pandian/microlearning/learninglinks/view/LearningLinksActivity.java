package com.samuvel.pandian.microlearning.learninglinks.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.samuvel.pandian.microlearning.R;
import com.samuvel.pandian.microlearning.learninglinks.presenter.LearningLinksPresenter;
import com.samuvel.pandian.microlearning.learninglinks.presenter.LearningLinksPresenterContract;

import java.util.List;

public class LearningLinksActivity extends AppCompatActivity implements LearningLinksViewContract {
    private RecyclerView mRecyclerViewLearning;
    private LearningLinksAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LearningLinksPresenterContract mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_links);
        attachView();
        getPresenter().getLearningLinks();
        setRecyclerView();
    }

    private void attachView() {
        mPresenter = new LearningLinksPresenter();
        ((LearningLinksPresenter) mPresenter).attachView(this,
                getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((LearningLinksPresenter) mPresenter).detachView();
    }

    private void setRecyclerView() {
        mRecyclerViewLearning = findViewById(R.id.recyclerViewLearning);
        mRecyclerViewLearning.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewLearning.setLayoutManager(mLayoutManager);
    }

    private LearningLinksPresenterContract getPresenter() {
        return mPresenter;
    }

    @Override
    public void populateList(List<String> urls) {
        mAdapter = new LearningLinksAdapter(urls);
        mRecyclerViewLearning.setAdapter(mAdapter);
    }

    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(), "error getting data",
                Toast.LENGTH_SHORT).show();
    }
}

