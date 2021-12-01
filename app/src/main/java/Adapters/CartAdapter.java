package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fashionicon.R;

import java.util.ArrayList;

import Helpers.DatabaseHelper;
import Models.CartItem;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartItemViewHolder> {
    private ArrayList<CartItem>cartItems;
    public  DatabaseHelper dbHelper;
    public CartAdapter(ArrayList<CartItem> cartItems, Context context){
        this.cartItems = cartItems;
        dbHelper=new DatabaseHelper(context);
    }

    @Override
    public int getItemCount()
    {
        return cartItems.size();
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_template,parent,false);
        return new CartItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        holder.bind(cartItems.get(position));
        CartItem item = cartItems.get(holder.getAdapterPosition());

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(cartItems.get(position).getUrl(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.Url);

        holder.removeItemBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isDeleted= dbHelper.deleteCartItem(item.getId());
                if(isDeleted){
                    //cartItems.remove(holder.getAdapterPosition());
                    holder.bind(cartItems.remove(holder.getAdapterPosition()));
                    System.out.println(item.getName()+" has been removed from cart");
                    String message="Item Has Been Removed ";
                    Toast.makeText(v.getContext(), message,Toast.LENGTH_SHORT).show();

                }
                else {
                    System.out.println("Failed to remove " + item.getName());

                    String message = "Failed to remove Item ";
                    Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public class CartItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        DatabaseHelper _dbHelper;
        ImageView Url;
        TextView Name, Quantity, Amount;
        Button increaseBtn, decreaseBtn, removeItemBTN;
        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            _dbHelper=new DatabaseHelper(itemView.getContext());
            Name=itemView.findViewById(R.id.name);
            Url = itemView.findViewById(R.id.image);
            Quantity=itemView.findViewById(R.id.quantity);
            Amount=itemView.findViewById(R.id.amount);
            removeItemBTN=itemView.findViewById(R.id.button_remove);

        }
        public void bind(CartItem cartItem){
            Name.setText(""+cartItem.getName());
           Glide.with(itemView).load(cartItem.getUrl()).into(Url);
            Quantity.setText(""+cartItem.getQuantity());
            Amount.setText(cartItem.getAmount()+" KSH");
            removeItemBTN.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            notifyDataSetChanged();
        }
    }

}
