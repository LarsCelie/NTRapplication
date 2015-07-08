package nl.hu.team.ntrapplication.optionFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.objects.Question;

/**
 * Created by Milamber on 1-4-2015.
 */
public class DateQuestionFragment extends AnswerOption{

    private TextView name, description;
    private DatePicker datePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_date_question, container, false);
        name = (TextView) root.findViewById(R.id.dateQuestionName);
        description = (TextView) root.findViewById(R.id.dateQuestionDescription);
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

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public boolean setValue() {
        return false;
    }
}
