package com.example.noteapp2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {

    ItemClicked itemClicked;

    interface ItemClicked{
        void onItemClicked(Note note);
    }

    public NoteAdapter(ItemClicked itemClicked) {
        super(DIFF_CALLBACK);
        this.itemClicked = itemClicked;
    }

    static DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority() &&
                    oldItem.getTitle().equals(newItem.getTitle());
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        holder.tv_title.setText(getItem(position).getTitle());
        holder.tv_description.setText(getItem(position).getDescription());
        holder.tv_priority.setText(String.valueOf(getItem(position).getPriority()));
    }

    public Note getNoteAtPosition(int position){
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        TextView tv_title, tv_description, tv_priority;

        public NoteHolder(@NonNull final View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_priority = itemView.findViewById(R.id.tv_priority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClicked.onItemClicked(getItem(getAdapterPosition()));
                }
            });
        }
    }
}
