package nl.hu.team.ntrapplication.attachmentFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nl.hu.team.ntrapplication.R;
import nl.hu.team.ntrapplication.objects.Question;

/**
 * Created by Milamber on 1-4-2015.
 */
public class InfoscreenFragment extends Fragment{

    private TextView name, desription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_infoscreen, container, false);
        name = (TextView) root.findViewById(R.id.infoscreenName);
        desription = (TextView) root.findViewById(R.id.infoscreenDescription);
        Bundle bundle = this.getArguments();
        Question question = bundle.getParcelable("question");
//        name.setText(question.getName());
        desription.setText(question.getDescription());
        return root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
