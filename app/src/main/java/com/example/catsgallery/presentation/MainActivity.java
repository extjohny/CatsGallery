package com.example.catsgallery.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.catsgallery.R;

public class MainActivity extends AppCompatActivity {

    private ImageView catImageView;
    private ProgressBar progressBar;
    private Button nextCatButton;
    private TextView errorTextView;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModelObservers();
        nextCatButton.setOnClickListener(view -> viewModel.loadCat());
    }

    private void viewModelObservers() {
        viewModel.loadCat();
        viewModel.getIsError().observe(this, b -> {
            if (b) {
                catImageView.setVisibility(View.GONE);
                nextCatButton.setText(R.string.button_error);
                errorTextView.setVisibility(View.VISIBLE);
            } else {
                catImageView.setVisibility(View.VISIBLE);
                nextCatButton.setText(R.string.next);
                errorTextView.setVisibility(View.GONE);
            }
        });
        viewModel.getCat().observe(
                this,
                cat ->
                        Glide.with(this)
                                .load(cat.getUrl())
                                .into(catImageView));
        viewModel.getIsLoading().observe(
                this,
                loading ->
                {
                    if (loading) progressBar.setVisibility(View.VISIBLE);
                    else progressBar.setVisibility(View.GONE);
                });
    }

    private void initViews() {
        catImageView = findViewById(R.id.imageViewCat);
        progressBar = findViewById(R.id.progressBar);
        nextCatButton = findViewById(R.id.buttonNextCat);
        errorTextView = findViewById(R.id.textViewNetworkError);
    }
}