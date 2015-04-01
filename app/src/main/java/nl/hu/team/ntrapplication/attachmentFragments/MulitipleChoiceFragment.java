package nl.hu.team.ntrapplication.attachmentFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.objects.Option;
import nl.hu.team.ntrapplication.objects.Question;

/**
 * Created by jiry on 1-4-2015.
 */
public class MulitipleChoiceFragment extends Fragment {
    private TextView name, description;
    private RadioGroup radioGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_multiple_choice_question, container, false);
        name = (TextView) root.findViewById(R.id.multiple_choice_QuestionName);
        description = (TextView) root.findViewById(R.id.multiple_choice_QuestionDescription);
        radioGroup = (RadioGroup) root.findViewById(R.id.radio_group);

        LinearLayout.LayoutParams layoutParams = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT);

        System.out.println("<(-_-)>");

        Bundle bundle = this.getArguments();
        Question question = bundle.getParcelable("question");
        ArrayList<Option> options = question.getOptions();
        for (Option o : options) {
            RadioButton radioButton = new RadioButton(getActivity());
            radioButton.setText(o.getCONTENT());
            radioButton.setId(o.getID());
            radioGroup.addView(radioButton, layoutParams);
        }

//      name.setText(question.getName());
        description.setText(question.getDescription());
        return root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
