package edu.bzu.hotelproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder>{
    private Context context;
    private List<Reservation> items;

    public BookingAdapter(Context context, List<Reservation> items){
        this.context = context;
        this.items = items;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_booking,
                parent,
                false);

        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Reservation item = items.get(position);
        CardView myCardView = holder.cardView;

        int id = item.getRoomId();
        TextView CardBookingName = (TextView) myCardView.findViewById(R.id.CardBookingName);
        TextView CardStart = (TextView) myCardView.findViewById(R.id.CardStart);
        TextView CardEnd = (TextView) myCardView.findViewById(R.id.CardEnd);
        TextView CardTotalPrice = (TextView) myCardView.findViewById(R.id.CardTotalPrice);
        Button room = (Button) myCardView.findViewById(R.id.room);

        CardBookingName.setText(item.getRoomName());
        CardStart.setText(item.getStartDate());
        CardEnd.setText(item.getEndDate());
        CardTotalPrice.setText(String.valueOf(item.getPrice()));

        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // todo delete booking
                String url = Constants.URL_DELETE_RESERVATION;
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.DELETE,
                        url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Toast.makeText(context, "Canceled",Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context, error.toString(),
                                        Toast.LENGTH_SHORT).show();
                                Log.d("Error_json", error.toString());
                            }
                        });

                requestQueue.add(jsonArrayRequest);

                Intent intent = new Intent(context, UserBookingActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        });

        myCardView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Create a dialog and find views inside the dialog layout
                Dialog myDialog = new Dialog(context);
                myDialog.setContentView(R.layout.booking_dialog_card);

                TextView name = (TextView) myCardView.findViewById(R.id.CardRoomName);
                TextView floor = (TextView) myCardView.findViewById(R.id.CardRoomFloor);
                TextView bed = (TextView) myCardView.findViewById(R.id.CardRoomBed);
                TextView size = (TextView) myCardView.findViewById(R.id.CardRoomSize);
                TextView price = (TextView) myCardView.findViewById(R.id.CardRoomPrice);

                String url = Constants.URL_GET_ROOM + id;
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                        url,null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        name.setText(jsonObject.getString("roomName"));
                                        floor.setText("floor : "+jsonObject.getString("floorNum"));
                                        bed.setText(jsonObject.getString("bedNum")+" beds");
                                        size.setText("room size: "+jsonObject.getString("size")+" ft\u00B2");
                                        price.setText(jsonObject.getString("price"));
                                    }catch(JSONException exception){
                                        Log.d("Error", exception.toString());
                                    }
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(context, error.toString(),
                                        Toast.LENGTH_SHORT).show();
                                Log.d("Error_json", error.toString());
                            }
                        });
                requestQueue.add(jsonArrayRequest);
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

