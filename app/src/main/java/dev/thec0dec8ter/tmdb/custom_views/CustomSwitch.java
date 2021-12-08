package dev.thec0dec8ter.tmdb.custom_views;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import dev.thec0dec8ter.tmdb.R;

public class CustomSwitch extends FrameLayout {

    private View switchView;

    private TextView leftTrack;
    private TextView rightTrack;
    private TextView thumbText;
    private CardView thumb;

    private Animator animator;

    private OnCheckedChangeListener onCheckedChangeListener;

    public CustomSwitch(Context context) {
        super(context);
        initViews(context);
    }

    public CustomSwitch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);

    }

    public CustomSwitch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    public CustomSwitch(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(context);

    }

    private void initViews(Context context){
        switchView = LayoutInflater.from(context).inflate(R.layout.custom_switch, this, false);
        leftTrack = switchView.findViewById(R.id.left_track);
        rightTrack = switchView.findViewById(R.id.right_track);
        thumbText = switchView.findViewById(R.id.thumb_text);
        thumb = switchView.findViewById(R.id.thumb);
        this.addView(switchView);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        leftTrack.setOnClickListener(view -> {
            String thumb_text = thumbText.getText().toString();
            String left_text = leftTrack.getText().toString();
            if (!thumb_text.equals(left_text)) {
                animator = AnimatorInflater.loadAnimator(view.getContext(), R.animator.switch_slide_left);
                animator.setTarget(thumb);
                animator.start();
                thumbText.setText(leftTrack.getText());
                onCheckedChangeListener.onCheckedChanged(thumbText, false);

            }
        });

        rightTrack.setOnClickListener(view -> {
            String thumb_text = thumbText.getText().toString();
            String right_text = rightTrack.getText().toString();
            if (!thumb_text.equals(right_text)) {
                animator = AnimatorInflater.loadAnimator(view.getContext(),R.animator.switch_slide_right);
                animator.setTarget(thumb);
                animator.start();
                thumbText.setText(rightTrack.getText());
                onCheckedChangeListener.onCheckedChanged(thumbText, true);

            }
        });
    }

    public void setThumbColor(int color){
        thumb.setCardBackgroundColor(getContext().getColor(color));
        switchView.setBackgroundTintList(getContext().getColorStateList(color));
    }

    public void setTrackTextColor(int color){
        leftTrack.setTextColor(getContext().getColor(color));
        rightTrack.setTextColor(getContext().getColor(color));
    }

    public void setThumbTextColor(int color){
        thumbText.setTextColor(getContext().getColor(color));
    }


    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener){
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    public interface OnCheckedChangeListener{
        void onCheckedChanged(TextView thumbText, boolean isChecked);
    }
}
