package one.shimmer.com.shimmer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;


public class MainActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener
{

    private SeekBar baseAlpha, shimmerSpeed, shimmerWidth;
    private ShimmerFrameLayout shimmerView;
    private TextView base, speed, angle;

    private static int SHIMMER_MAX_DURATION = 4000;
    private static int SHIMMER_MAX_BASE_ALPHA = 100;
    private static int SHIMMER_MAX_WIDTH = 200; //This is the drop off value


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        shimmerView.useDefaults();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        shimmerView.startShimmerAnimation();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        shimmerView.stopShimmerAnimation();
    }

    public void initializeViews()
    {
        baseAlpha = (SeekBar)findViewById(R.id.baseAlpha);
        shimmerSpeed = (SeekBar)findViewById(R.id.shimmerSpeed);
        shimmerWidth = (SeekBar)findViewById(R.id.shimmerWidth);

        baseAlpha.setMax(SHIMMER_MAX_BASE_ALPHA);
        shimmerSpeed.setMax(SHIMMER_MAX_DURATION);
        shimmerWidth.setMax(SHIMMER_MAX_WIDTH);

        baseAlpha.setOnSeekBarChangeListener(this);
        shimmerSpeed.setOnSeekBarChangeListener(this);
        shimmerWidth.setOnSeekBarChangeListener(this);

        shimmerView = (ShimmerFrameLayout)findViewById(R.id.shimmerView);

        base = (TextView)findViewById(R.id.textView2);
        speed = (TextView)findViewById(R.id.textView3);
        angle = (TextView)findViewById(R.id.textView4);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
        boolean isPlaying = shimmerView.isAnimationStarted();

        switch (seekBar.getId())
        {
            case R.id.baseAlpha:
                shimmerView.setBaseAlpha(progress/100f);
                //base.setText(""+progress);
                break;
            case R.id.shimmerSpeed:
                shimmerView.setDuration(SHIMMER_MAX_DURATION - progress);
                //speed.setText(""+progress);
                break;
            case R.id.shimmerWidth:
                //shimmerView.setTilt(progress);
                shimmerView.setDropoff(progress/Float.valueOf(SHIMMER_MAX_WIDTH));

                //angle.setText(""+progress);
                break;
        }
        if(isPlaying)
        {
            shimmerView.stopShimmerAnimation();
            shimmerView.startShimmerAnimation();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {

    }
}
