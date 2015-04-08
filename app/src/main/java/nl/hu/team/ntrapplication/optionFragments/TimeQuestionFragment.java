package nl.hu.team.ntrapplication.optionFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.objects.Question;

/**
 * Created by Milamber on 1-4-2015.
 */
public class TimeQuestionFragment extends Fragment{
    private TextView name, description;
    private TimePicker timePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_time_question, container, false);
        name = (TextView) root.findViewById(R.id.timeQuestionName);
        description = (TextView) root.findViewById(R.id.timeQuestionDescription);
        timePicker = (TimePicker) root.findViewById(R.id.timeQuestionPicker);
        timePicker.setIs24HourView(true);
        Bundle bundle = this.getArguments();
        Question question = bundle.getParcelable("question");
//        name.setText(question.getName());
        description.setText(question.getDescription());
        return root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
