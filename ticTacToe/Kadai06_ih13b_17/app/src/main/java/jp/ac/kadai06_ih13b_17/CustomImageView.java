package jp.ac.kadai06_ih13b_17;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class CustomImageView extends AppCompatImageView {
    private int resId;

    public CustomImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);

        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }
}

