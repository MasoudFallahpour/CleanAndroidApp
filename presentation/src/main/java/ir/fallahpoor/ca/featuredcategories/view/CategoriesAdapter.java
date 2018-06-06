package ir.fallahpoor.ca.featuredcategories.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.fallahpoor.ca.R;
import ir.fallahpoor.ca.featuredcategories.model.CategoryModel;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    private List<CategoryModel> categoryModelList;
    private LayoutInflater layoutInflater;

    public CategoriesAdapter(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.list_item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryModel categoryModel = categoryModelList.get(position);
        holder.categoryNameTextView.setText(categoryModel.getName());
        holder.categoryDescriptionTextView.setText(categoryModel.getDescription());

    }

    @Override
    public int getItemCount() {
        return (categoryModelList != null) ? categoryModelList.size() : 0;
    }

    void setCategoryModelList(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
        notifyDataSetChanged();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.category_name_text_view)
        TextView categoryNameTextView;
        @BindView(R.id.category_description_text_view)
        TextView categoryDescriptionTextView;

        CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
