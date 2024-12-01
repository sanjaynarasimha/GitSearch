//package com.example.myapplication;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.List;
//
//public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {
//    private Context context;
//    private List<Repository> repositoryList;
//
//    public RepoListAdapter(Context context, List<Repository> repositoryList) {
//        this.context = context;
//        this.repositoryList = repositoryList;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.repo_item, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        Repository repo = repositoryList.get(position);
//        holder.repoName.setText(repo.getRepoName());
//        holder.repoOwner.setText(repo.getRepoOwner());
//        holder.repoDescription.setText(repo.getRepoDescription());
//        holder.repoStars.setText(String.valueOf(repo.getRepoStars()));
//    }
//
//    @Override
//    public int getItemCount() {
//        return repositoryList.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView repoName, repoOwner, repoDescription, repoStars;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            repoName = itemView.findViewById(R.id.repo_name);
//            repoOwner = itemView.findViewById(R.id.repo_owner);
//            repoDescription = itemView.findViewById(R.id.repo_description);
//            repoStars = itemView.findViewById(R.id.repo_stars);
//        }
//    }
//}

package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    private Context context;
    private List<Repository> repositoryList;

    public RepoListAdapter(Context context, List<Repository> repositoryList) {
        this.context = context;
        this.repositoryList = repositoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the repo_item layout
        View view = LayoutInflater.from(context).inflate(R.layout.repo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Repository repo = repositoryList.get(position);

        // Bind data to the views
        holder.repoName.setText(repo.getName());
        holder.repoOwner.setText(repo.getOwner());
        holder.repoDescription.setText(repo.getDescription());
        holder.repoStars.setText(String.valueOf(repo.getStars()));
    }


    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView repoName, repoOwner, repoDescription, repoStars;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            repoName = itemView.findViewById(R.id.repo_name);
            repoOwner = itemView.findViewById(R.id.repo_owner);
            repoDescription = itemView.findViewById(R.id.repo_description);
            repoStars = itemView.findViewById(R.id.repo_stars);
        }
    }
}
