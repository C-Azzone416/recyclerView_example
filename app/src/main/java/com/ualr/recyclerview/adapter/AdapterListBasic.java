package com.ualr.recyclerview.adapter;

import android.app.Person;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ualr.recyclerview.R;
import com.ualr.recyclerview.model.Item;
import com.ualr.recyclerview.model.People;
import com.ualr.recyclerview.model.SectionHeader;
import com.ualr.recyclerview.utils.Tools;

import java.util.List;

/**
 * Created by irconde on 2019-09-25.
 */

// TODO 05: We have to replace any reference to People class with a reference to Item class
public class AdapterListBasic extends RecyclerView.Adapter{

    // TODO 09: We'll define two constant values that represent the two types of items that we have in our recyclerView

    private static final int PERSON_VIEW = 0;
    private static final int HEADER_VIEW = 1;

    private List<Item> mItems;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, People obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListBasic(Context context, List<Item> items) {
        this.mItems = items;
        this.mContext = context;
    }

    // TODO 08: Somehow, we need to know if an item in the list is a person or just the header of a section.
    //  We must implement the method getItemViewType, that returns the view type of a item at position

    // TODO 09: We'll define two constant values that represent the two types of items that we have in our recyclerView

    @Override
    public int getItemViewType(int position) {
        return this.mItems.get(position).isSection()? HEADER_VIEW : PERSON_VIEW;
    }

    // TODO 10: Modify onCreateViewHolder. We have to create the proper view holder based on the type of item

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder vh = null;
        View itemView = null;

        switch (viewType) {
            case (HEADER_VIEW) :
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section, parent, false);
                vh = new SectionHeaderViewHolder(itemView);
                break;
            case (PERSON_VIEW):
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people_chat, parent, false);
                vh = new PersonViewHolder(itemView);
                break;
        }
        return vh;
    }

    // TODO 11: Modify onBindViewHolder. We have populate each item type with the corresponding data

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Item item = mItems.get(position);
        if (holder instanceof PersonViewHolder) {
            PersonViewHolder viewHolder = (PersonViewHolder) holder;
            People person = (People) item;
            viewHolder.name.setText(person.getName());
            Tools.displayImageRound(mContext, viewHolder.image, person.getImage());
        } else {
            // Instance of SectionHeaderViewHolder
            SectionHeaderViewHolder viewHolder = (SectionHeaderViewHolder) holder;
            SectionHeader section = (SectionHeader) item;
            viewHolder.label.setText(section.getLabel());
        }
    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public View lyt_parent;

        /**
         * PersonViewHolder constructor
         * Retrieves references to the ImageView and the TextView inside the the view provided as input parameter
         * The adapter later retrieves view references from these properties when it updates the child views with new data
         *
         * @param v parent item view reference
         */
        public PersonViewHolder(@NonNull View v) {
            super(v);
            image = v.findViewById(R.id.image);
            name = v.findViewById(R.id.name);
            lyt_parent = v.findViewById(R.id.lyt_parent);
            lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(view, (People) mItems.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }

    // TODO 06: We have to define a specific ViewHolder for the section header views
    // TODO 07: We have to define a specific layout for the section header views

    public class SectionHeaderViewHolder extends RecyclerView.ViewHolder {

        public TextView label;

        public SectionHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            this.label = itemView.findViewById(R.id.title_section);
        }
    }

}
