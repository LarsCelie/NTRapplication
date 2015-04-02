package nl.hu.team.ntrapplication.optionFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.objects.Question;

/**
 * Created by Lars on 4/2/2015.
 */
public class OpenQuestionFragment extends Fragment {

    private TextView name, description;
    private DatePicker datePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_open_question, container, false);
        name = (TextView) root.findViewById(R.id.openQuestionName);
        description = (TextView) root.findViewById(R.id.openQuestionDescription);

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