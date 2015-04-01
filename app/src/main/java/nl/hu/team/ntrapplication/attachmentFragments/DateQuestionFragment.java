package nl.hu.team.ntrapplication.attachmentFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import nl.hu.team.ntrapplication.R;

/**
 * Created by Milamber on 1-4-2015.
 */
public class DateQuestionFragment extends Fragment {

    private TextView name, description;
    private DatePicker datePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_date_question, container, false);
        name = (TextView) root.findViewById(R.id.dateQuestionName);
        description = (TextView) root.findViewById(R.id.dateQuestionDescription);
        return root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
