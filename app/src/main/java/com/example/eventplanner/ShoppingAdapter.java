package com.example.eventplanner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eventplanner.Database.DBHelper;

import java.util.List;

public class ShoppingAdapter extends ArrayAdapter<ShoppingLists> {
    private  Context context;
    private int resource;
    List<ShoppingLists> shoppingLists;

    DBHelper dbShopping;

    public ShoppingAdapter(@NonNull Context context, int resource, List<ShoppingLists> shoppingLists) {
        super(context, resource, shoppingLists);
        this.context=context;
        this.resource=resource;
        this.shoppingLists=shoppingLists;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row= inflater.inflate(resource,parent,false);

        TextView shoppingName = row.findViewById(R.id.idEditSName);
        TextView qty = row.findViewById((R.id.idEditSQty));
        TextView price = row.findViewById((R.id.idEditSPrice));
        ImageButton edit = row.findViewById(R.id.idEditSBtn);
        ImageButton delete = row.findViewById(R.id.idDeleteSBtn);

        final ShoppingLists shoppingList= shoppingLists.get(position);

        shoppingName.setText(shoppingList.getShoppingName());
        qty.setText(shoppingList.getQty());
        price.setText(shoppingList.getPrice());

        //delete.setTag(position);
       delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbShopping = new DBHelper(context);
                //Task task = tasks.get(position);
                System.out.println("zxzxzxzxzxz"+ shoppingList.getId());
                dbShopping.deleteShopping(shoppingList.getId());

                Intent myIntent = new Intent(context,ShoppingDetails.class);

                context.startActivity(myIntent);

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context , EditShoppingList.class);
                intent.putExtra("ids",String.valueOf(shoppingList.getId()));
                intent.putExtra("title",String.valueOf((shoppingList.getShoppingName())));
                System.out.println("IDDDDDDDDDD" + shoppingList.getId());
                context.startActivity(intent);

            }
        });

        return row;

    }
}
