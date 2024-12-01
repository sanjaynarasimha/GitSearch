package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RepoDetailsActivity extends AppCompatActivity {
    private TextView repoName, repoDescription, repoStars;
    private String repoLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_details);

        repoName = findViewById(R.id.repo_name);
        repoDescription = findViewById(R.id.repo_description);
        repoStars = findViewById(R.id.repo_stars);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("repo")) {
            Repository repo = (Repository) intent.getSerializableExtra("repo");

            if (repo != null) {
                repoName.setText(repo.getName());
                repoDescription.setText(repo.getDescription());
                repoStars.setText(String.valueOf(repo.getStars()));

                repoLink = "https://github.com/" + repo.getOwner() + "/" + repo.getName();
            }
        }
    }
}
