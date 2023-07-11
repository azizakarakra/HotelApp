package edu.bzu.hotelproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder>{
    private Context context;
    private List<Room> items;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public RoomAdapter(Context context, List<Room> items){
        this.context = context;
        this.items = items;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_room,
                parent,
                false);

        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Room item = items.get(position);
        CardView myCardView = holder.cardView;

        prefs= PreferenceManager.getDefaultSharedPreferences(context);
        editor = prefs.edit();

        TextView name = (TextView) myCardView.findViewById(R.id.CardRoomName);
        TextView floor = (TextView) myCardView.findViewById(R.id.CardRoomFloor);
        TextView bed = (TextView) myCardView.findViewById(R.id.CardRoomBed);
        TextView size = (TextView) myCardView.findViewById(R.id.CardRoomSize);
        TextView price = (TextView) myCardView.findViewById(R.id.CardRoomPrice);
        Button room = (Button) myCardView.findViewById(R.id.room);

        name.setText(item.getRoomName());
        floor.setText("floor : "+String.valueOf(item.getFloorNumber()));
        bed.setText(String.valueOf(item.getNumberOfBeds())+" beds");
        size.setText("room size: "+String.valueOf(item.getSize())+" ft\u00B2");
        price.setText(String.valueOf(item.getPrice()));
        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("selectedRoomID", item.getRoomID());
                editor.putString("selectedRoom", item.getRoomName());
                editor.putFloat("selectedRoomPrice",(float) item.getPrice());
                Intent intent = new Intent(context, FillInfoActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        });

        myCardView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Create a dialog and find views inside the dialog layout
                Dialog myDialog = new Dialog(context);
                myDialog.setContentView(R.layout.room_dialog_card);
                myDialog.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView cardView){
            super(cardView);
            this.cardView = cardView;
        }

    }
}

