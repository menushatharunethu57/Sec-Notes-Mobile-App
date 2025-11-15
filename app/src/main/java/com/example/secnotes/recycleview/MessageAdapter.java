package com.example.secnotes.recycleview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secnotes.DBHelper;
import com.example.secnotes.R;
import com.example.secnotes.update_page;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private Context context;
    private List<Message> messages;
    private List<Integer> ids;
    private DBHelper dbHelper;

    public MessageAdapter(Context context, List<Message> messages, List<Integer>ids) {
        this.context = context;
        this.messages = messages;
        this.ids = ids;
        this.dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.topic.setText(message.getTopic());
        holder.content.setText(message.getContent());


        final int currentID = ids.get(position);
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, update_page.class);
                i.putExtra("id",currentID);
                context.startActivity(i);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean deletednode = dbHelper.deleteData(currentID);
                if(deletednode){
                    messages.remove(position);
                    ids.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,messages.size());

                    Toast.makeText(context,"Item Deleted Successfully",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView topic, content;
        Button delete, update;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            topic = itemView.findViewById(R.id.ctopic);
            content = itemView.findViewById(R.id.data);
            delete = itemView.findViewById(R.id.del);
            update = itemView.findViewById(R.id.upd);
        }

    }
}
