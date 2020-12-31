package com.example.android.langapp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    int mColorResId;

    public WordAdapter(Activity context, ArrayList<Word> words, int ColorResId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.

        super(context, 0, words);
        mColorResId = ColorResId;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.eng_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        defaultTextView.setText(currentWord.getDefaultTranslation());


        ImageView imageView = (ImageView) listItemView.findViewById(R.id.img_ic);
        if (currentWord.hasImage()) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(currentWord.getImageResourceId());
        } else
            imageView.setVisibility(View.GONE);         //if set to invisible image area will take space and blank space will comw.

        ImageView imageView1=(ImageView) listItemView.findViewById(R.id.play_button) ;
        imageView1.setImageResource(currentWord.getIcon());

        //set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        //find the color that resourceId maps to
        int color = ContextCompat.getColor(getContext(), mColorResId);
        //set the bg color to the text container view
        textContainer.setBackgroundColor(color);



        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
