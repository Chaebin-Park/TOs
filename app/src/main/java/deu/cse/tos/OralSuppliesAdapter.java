package deu.cse.tos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OralSuppliesAdapter extends RecyclerView.Adapter<OralSuppliesAdapter.ItemViewHolder> {
    private ArrayList<OralSupplies> items;
    private Context context;
    private View.OnClickListener onClickItem;

    public OralSuppliesAdapter(Context context, ArrayList<OralSupplies> items, View.OnClickListener onClickItem) {
        this.context = context;
        this.items = items;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cardlist_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        OralSupplies oralSupplies = items.get(position);
        holder.list_recommended_date.setText(oralSupplies.getRecommendedDate());
        holder.list_item_name.setText(oralSupplies.getItemName());
        holder.list_remaining_date.setText(Integer.toString(oralSupplies.getRemainingDate()));
        holder.imageButton.setTag(position);
        holder.imageButton.setOnClickListener(onClickItem);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(OralSupplies oralSupplies) {
        items.add(oralSupplies);
        notifyDataSetChanged();
    }

    public OralSupplies getItem(int positon) {
        return items.get(positon);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView list_remaining_date, list_item_name, list_recommended_date;
        private ImageButton imageButton;
        public ItemViewHolder(View itemView) {
            super(itemView);
            list_remaining_date = itemView.findViewById(R.id.tv_remaining_date);
            list_item_name = itemView.findViewById(R.id.tv_item_name);
            list_recommended_date = itemView.findViewById(R.id.tv_recomended_date);
            imageButton = itemView.findViewById(R.id.imageButton);
        }
    }
}