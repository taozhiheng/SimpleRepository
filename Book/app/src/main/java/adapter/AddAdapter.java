package adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hustunique.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data.Book;
import util.Constant;

/**
 * Created by taozhiheng on 15-7-5.
 * AddActivity query result data adapter
 *
 */
public class AddAdapter extends RecyclerView.Adapter<AddAdapter.MyViewHolder> {

    private List<Book> mList;

    private AddOnItemClickListener mOnItemClickListener;

    private Context mContext;

    public AddAdapter(Context context, List<Book> list)
    {
        this.mContext = context;
        this.mList = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_item, parent, false);
        return new MyViewHolder(view, R.id.add_item_icon,
                R.id.add_item_name, R.id.add_item_author, R.id.add_item_press,R.id.add_item_num,
                R.id.add_item_flag );
    }

    @Override
    public void onBindViewHolder(AddAdapter.MyViewHolder holder, int position) {
        Book book = mList.get(position);
        holder.mFlag.setTag(position);
        holder.mFlag.setOnClickListener(mOnClickListener);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(mOnClickListener);
        String url = book.getUrl();
        if(url != null)
            Picasso.with(mContext).load(Uri.parse(url)).into(holder.mIcon);
        holder.mName.setText(book.getName());
        holder.mAuthor.setText(book.getAuthor());
        holder.mPress.setText(book.getPress());
        holder.mNum.setText(book.getFinishNum()+"/"+book.getChapterNum()+"章    "+book.getWordNum()+" K字");
        if(book.getType() == Constant.TYPE_NOW)
        {
            holder.mFlag.setSelected(true);
        }
        else
        {
            holder.mFlag.setSelected(false);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mOnItemClickListener != null)
            {
                int position = (Integer)v.getTag();
                if(v instanceof ImageView)
                    mOnItemClickListener.onItemFlagClick((ImageView) v, position);
                else
                    mOnItemClickListener.onItemClick(position);
            }
        }
    };

    static class MyViewHolder extends RecyclerView.ViewHolder
    {

        private ImageView mIcon;
        private TextView mName;
        private TextView mAuthor;
        private TextView mPress;
        private TextView mNum;
        private ImageView mFlag;
        public MyViewHolder(View view,int iconRes,
                            int nameRes, int authorRes, int pressRes, int numRes,
                            int flagRes)
        {
            super(view);
            this.mIcon = (ImageView) view.findViewById(iconRes);
            this.mName = (TextView) view.findViewById(nameRes);
            this.mAuthor = (TextView) view.findViewById(authorRes);
            this.mPress = (TextView) view.findViewById(pressRes);
            this.mNum = (TextView) view.findViewById(numRes);
            this.mFlag = (ImageView) view.findViewById(flagRes);
        }
    }

    public void setOnItemClickListener(AddOnItemClickListener listener)
    {
        this.mOnItemClickListener = listener;
    }


    public interface AddOnItemClickListener
    {
        /**
         * when the icon at the right of the whole item view is clicked,the method will be called
         * */
        void onItemFlagClick(ImageView imageView, int position);

        /**
         * when the whole item view is clicked,the method will be called
         * */
        void onItemClick(int position);
    }

}
