package Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fashionicon.R;

import java.util.ArrayList;

import Helpers.DatabaseHelper;
import Models.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ProductAdapter.OnProductItemClickListener onProductItemClickListener;
    ArrayList<Product> products;
DatabaseHelper databaseHelper;
    public ProductAdapter(ArrayList<Product> products, Context context, OnProductItemClickListener onProductItemClickListener) {
        this.products = products;
        databaseHelper= new DatabaseHelper(context);
        this.onProductItemClickListener=onProductItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_template, parent, false);

        return new ViewHolder(inflate, onProductItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product prod= products.get(position);
        holder.title.setText(prod.getProdname());
        holder.fee.setText(""+prod.getPrice());

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(products.get(position).getImageUrl(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res= databaseHelper.getCartItem(prod.getId());
                System.out.println(res.toString());
                if (res.getCount()==0){
                    boolean isAddedToCart= databaseHelper.insertCartItem(prod.getId(),prod.getProdname(),prod.getImageUrl(),"1",""+prod.price,""+prod.getPrice());
                    if (isAddedToCart){
                        String message=prod.getProdname()+" Has Been Added To Cart";
                        Toast.makeText(v.getContext(), message,Toast.LENGTH_LONG).show();
                    }
                    else {
                        String message="Failed To Add Item To Cart";
                    }
                }
                else{
                    String message=prod.getProdname()+" is already added";
                    Toast.makeText(v.getContext(), message,Toast.LENGTH_LONG).show();
                }
            }

        });

    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnProductItemClickListener onProductItemClickListener;
        TextView title, fee;
        ImageView pic;
        TextView addBtn;


        public ViewHolder(@NonNull View itemView, OnProductItemClickListener onProductItemClickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.prodname);
            fee = itemView.findViewById(R.id.prod_price);
            pic = itemView.findViewById(R.id.prod_image);
            addBtn = itemView.findViewById(R.id.addproduct);
            this.onProductItemClickListener=onProductItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onProductItemClickListener.onProductItemClick(products.get(getAdapterPosition()), getAdapterPosition());
        }
    }

    public interface OnProductItemClickListener {
        void onProductItemClick(Product product, int position);
    }
}