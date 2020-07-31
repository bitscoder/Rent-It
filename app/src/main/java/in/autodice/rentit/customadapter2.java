package in.autodice.rentit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.rentit.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class customadapter2 extends ArrayAdapter<Product> {
    private ArrayList<Product> products;
    Context mContext;
    String type;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        ImageView imageView;
    }

    public customadapter2(ArrayList<Product> data, Context context) {
        super(context, R.layout.imageinhome, data);
        this.products = data;
        this.mContext=context;

    }

    public customadapter2(String str, ArrayList<Product> data, Context context) {
        super(context, R.layout.imageinhome, data);
        this.products = data;
        this.mContext=context;
        this.type=str;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Product dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.imageinhome, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name_screen);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_screen);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtName.setText(dataModel.getText());
        Picasso.get().load(dataModel.getImage()).into(viewHolder.imageView);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("home"))
                {
                    Intent intent = new Intent(mContext, Productdetails.class);
                    intent.putExtra("value1", dataModel.getId());
                    mContext.startActivity(intent);
                }
                else if (type.equals("prodsold"))
                {
                    Intent intent = new Intent(mContext, XYZ.class);
                    intent.putExtra("value2", dataModel.getId());
                    mContext.startActivity(intent);
                }
                else if (type.equals("prodbuyed"))
                {
                    Intent intent = new Intent(mContext, QWE.class);
                    intent.putExtra("value2", dataModel.getId());
                    mContext.startActivity(intent);
                }
            }
        });
        return convertView;
    }
}
