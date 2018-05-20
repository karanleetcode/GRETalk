import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by win-8 on 05-02-2017.
 */

public class CustomAdapter3 extends ArrayAdapter<Contacts3> implements CompoundButton.OnCheckedChangeListener{
    private final Context context;
    private final ArrayList<Contacts3> array;
    TextView wordName;
    Switch s;
    final DBHandler3 dbHandler3;
    final DBHandler dbHandler;
    List<Contacts3> arrayList;
    public CustomAdapter3(Context context, ArrayList<Contacts3> array2) {
        super(context, -1, array2);
        this.context = context;
        this.array = array2;
        dbHandler3 = new DBHandler3(context);
        arrayList = new ArrayList<>();
        dbHandler = new DBHandler(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.list_item_example3, parent, false);
        final Contacts3 cn = array.get(position);
        wordName = (TextView) rowView.findViewById(R.id.wordName);
        wordName.setText(cn.getWordName());

        s = (Switch) rowView.findViewById(R.id.switchButton);
        s.setOnCheckedChangeListener(this);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(context,cn.getWordName(),Toast.LENGTH_LONG).show();
                Intent intent=new Intent(context,Main2Activity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("wordname",cn.getWordName());
                view.getContext().startActivity(intent);

            }
        });
        return rowView;
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        View v=(View)buttonView.getParent();
        TextView t=v.findViewById(R.id.wordName);

        String x=t.getText().toString();
        if(isChecked){

            Toast.makeText(context,x+":checked:", Toast.LENGTH_SHORT).show();
            dbHandler3.addContact(new Contacts3(0,x,"1"));
            dbHandler.updateVoiceTo1(x);
            Toast.makeText(context,"word updated", Toast.LENGTH_SHORT).show();
        }else{
            dbHandler3.deleteWord(x);
            Toast.makeText(context,"word deleted", Toast.LENGTH_SHORT).show();
            dbHandler.updateVoiceTo0(x);

        }
    }
}
