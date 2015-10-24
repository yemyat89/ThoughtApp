package com.yemyatthein.thoughts.customs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.yemyatthein.thoughts.R;
import com.yemyatthein.thoughts.activities.ReadActivity;

public class SearchResultAdapter extends BaseAdapter{
    Context context;

    private static LayoutInflater inflater=null;
    public SearchResultAdapter(Context context) {
        this.context=context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.search_result_item, null);
        View textView1 = rowView.findViewById(R.id.text_thought_content_search_result_item);

        textView1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReadActivity.class);

                Bundle bundle = new Bundle();
                bundle.putInt(ReadActivity.FRAGMENT_TARGET_KEY,
                        ReadActivity.READ_FRAGMENT_TARGET_VIEW);

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return rowView;
    }

}