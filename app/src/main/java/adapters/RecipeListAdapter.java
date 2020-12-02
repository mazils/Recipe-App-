package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvm.R;

import java.util.List;

import remoteDataSource.Recipe;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    List<Recipe> recipeList;
    public RecipeListAdapter(List<Recipe> listItems)
    {
        this.recipeList = listItems;
    }


    @NonNull
    @Override
    public RecipeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipelistitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.ViewHolder holder, int position) {
        holder.recipeTitle.setText(recipeList.get(position).getTitle());
        /**Glide implementation*/
        Glide.with(holder.recipeImageView.getContext()).load(recipeList.get(position).getImage()).into(holder.recipeImageView);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recipeImageView;
        TextView recipeTitle;
        ImageView likeImageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeImageView = itemView.findViewById(R.id.recipeImageView);
            recipeTitle  = itemView.findViewById(R.id.recipeDescription);
            likeImageView = itemView.findViewById(R.id.likeImageView);
        }
    }
}
