package nl.hu.team.ntrapplication.optionFragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.objects.Option;
import nl.hu.team.ntrapplication.objects.Question;

/**
 * Created by Milamber on 1-4-2015.
 */
public class MultipleSelectQuestionFragment extends Fragment {

    private TextView name, description;
    private DatePicker datePicker;
    private MyCustomAdapter dataAdapter;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_multiple_select, container, false);
        name = (TextView) root.findViewById(R.id.multipleSelectQuestionName);
        description = (TextView) root.findViewById(R.id.multipleSelectQuestionDescription);
        Bundle bundle = this.getArguments();
        Question question = bundle.getParcelable("question");
//        name.setText(question.getName());
        description.setText(question.getDescription());
        dataAdapter = new MyCustomAdapter(getActivity(),R.layout.layout_multiselect,question.getOptions());
        listView = (ListView) root.findViewById(R.id.multipleSelectOptions);
        listView.setAdapter(dataAdapter);
        return root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    private class MyCustomAdapter extends ArrayAdapter<Option> {
        private ArrayList<Option> options;
        public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<Option> options) {
            super(context,textViewResourceId,options);
            this.options = new ArrayList<>();
            this.options.addAll(options);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if(convertView == null) {
                LayoutInflater vi = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.layout_multiselect, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);
                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        Option option = (Option) cb.getTag();
                        option.setSelected(cb.isChecked());
                    }
                });
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Option option = options.get(position);
            holder.code.setText(" ("+ option.getVALUE() +")");
            holder.name.setText(option.getCONTENT());
            holder.name.setChecked(option.isSelected());
            holder.name.setTag(option);
            return convertView;
        }
    }
}
