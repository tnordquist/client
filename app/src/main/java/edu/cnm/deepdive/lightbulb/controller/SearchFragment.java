package edu.cnm.deepdive.lightbulb.controller;

import android.app.DownloadManager.Query;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.lightbulb.R;
import edu.cnm.deepdive.lightbulb.model.Comment;
import edu.cnm.deepdive.lightbulb.view.CommentRecyclerAdapter;
import edu.cnm.deepdive.lightbulb.viewmodel.MainViewModel;
import java.util.List;

public class SearchFragment extends Fragment implements OnQueryTextListener {

  private MainViewModel viewModel;
  private RecyclerView searchList;
  private SearchView filter;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_search, container, false);
    searchList = root.findViewById(R.id.search_list);
    filter = root.findViewById(R.id.filter);
    root.findViewById(R.id.start_conversation).setOnClickListener((v) -> {
      // TODO Invoke method to start a new conversation.
    });
     return root;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel.getSearchComments().observe(getActivity(), (comments) -> {
      CommentRecyclerAdapter adapter = new CommentRecyclerAdapter(getContext(), comments, (pos, comment) -> {
       //TODO Display individual comment.
      });
      searchList.setAdapter(adapter);
    });
  }

  @Override
  public boolean onQueryTextSubmit(String query) {
    if (query.trim().length() >= 3) {
      viewModel.setSearchFilter(query.trim());
      return true;
    }
    return false;
  }

  @Override
  public boolean onQueryTextChange(String newText) {
    return false;
  }
}