package eu.telecomnancy.amio.ui.main.mote.information;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import eu.telecomnancy.amio.MainActivity;
import eu.telecomnancy.amio.R;
import eu.telecomnancy.amio.iotlab.models.Mote;
import eu.telecomnancy.amio.persistence.IotLabDatabaseProvider;
import eu.telecomnancy.amio.persistence.daos.MoteDao;
import eu.telecomnancy.amio.persistence.daos.RecordDao;
import eu.telecomnancy.amio.persistence.entities.RecordAndMote;
import eu.telecomnancy.amio.persistence.utils.IoTLabPersistenceUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoteInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoteInformationFragment extends Fragment {

    /**
     * The fragment initialization parameter key
     */
    private static final String ARG_PARAM1 = "mote";

    /**
     * Mote displayed in this frame
     */
    private Mote _Mote;

    private LineChart chart;
    private int mSize = 120;

    public MoteInformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mote the mote to display information
     * @return A new instance of fragment MoteInformationFragment.
     */
    public static MoteInformationFragment newInstance(Mote mote) {
        MoteInformationFragment fragment = new MoteInformationFragment();

        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, mote);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            _Mote = (Mote) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity())
                .getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mote_information, container, false);
        TextView moteIdTv = view.findViewById(R.id.moteInformationID);
        moteIdTv.setText(_Mote.getName());

        EditText motePreferredName = view.findViewById(R.id.motePreferredName);
        motePreferredName.setText(_Mote.getPreferredName());

        motePreferredName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MoteDao moteDao = IotLabDatabaseProvider.getOrCreateInstance(getContext()).moteDao();
                IoTLabPersistenceUtils.changeMotePreferredName(_Mote, moteDao, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) { }

        });

        updateLineChart(view);

        return view;
    }

    @SuppressLint("StaticFieldLeak")
    private void updateLineChart(View view) {


        {   // // Chart Style // //
            chart = view.findViewById(R.id.record_chart);

            // background color
            chart.setBackgroundColor(Color.WHITE);

            // disable description text
            chart.getDescription().setEnabled(false);

            // enable touch gestures
            chart.setTouchEnabled(true);

            // set listeners
            chart.setDrawGridBackground(false);

            // enable scaling and dragging
            chart.setDragEnabled(true);
            chart.setScaleEnabled(true);
            chart.setScaleXEnabled(true);
            chart.setScaleYEnabled(true);

            // force pinch zoom along both axis
            chart.setPinchZoom(true);
        }

        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getAxisLabel(float value, AxisBase axis) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis((long) value);
                    LocalTime time = LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
                    return time.toString();
                }
            });
        }

        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = chart.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            chart.getAxisRight().setEnabled(false);

            // axis range
            //yAxis.setAxisMaximum(500f);
            yAxis.setAxisMinimum(0f);
        }

        {   // // Create Limit Lines // //
            LimitLine ll1 = new LimitLine(200f, "Lightened room");
            ll1.setLineWidth(4f);
            ll1.enableDashedLine(10f, 10f, 0f);
            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            ll1.setTextSize(10f);


            // draw limit lines behind data instead of on top
            yAxis.setDrawLimitLinesBehindData(true);
            xAxis.setDrawLimitLinesBehindData(true);

            // add limit lines
            yAxis.addLimitLine(ll1);
        }

        try {
            updateChartData(new AsyncTask<Void, Void, List<RecordAndMote>>() {
                @Override
                protected List<RecordAndMote> doInBackground(Void... voids) {
                    MoteDao moteDao = IotLabDatabaseProvider.getOrCreateInstance(getContext()).moteDao();
                    RecordDao recordDao = IotLabDatabaseProvider.getOrCreateInstance(getContext()).recordDao();
                    long moteId = moteDao.getByName(_Mote.getName()).moteId;
                    return recordDao.getLatestRecordAndMotePairById(moteId, mSize);
                }
            }.execute().get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void updateChartData(List<RecordAndMote> records) {
        List<Entry> data = new ArrayList<>();
        records.sort((recordAndMote, t1) -> (int) (recordAndMote.record.retrievedAt - t1.record.retrievedAt));
        records.forEach(recordAndMote -> data.add(new Entry(recordAndMote.record.retrievedAt, recordAndMote.record.brightness)));
        LineDataSet set1;
        // create a dataset and give it a type
        set1 = new LineDataSet(data, "Brightness");

        set1.setDrawIcons(false);

        // draw dashed line
        set1.enableDashedLine(10f, 5f, 0f);

        // black lines and points
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);

        // line thickness and point size
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);

        // draw points as solid circles
        set1.setDrawCircleHole(false);

        // customize legend entry
        set1.setFormLineWidth(1f);
        set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        set1.setFormSize(15.f);

        // text size of values
        set1.setValueTextSize(9f);

        // draw selection line as dashed
        set1.enableDashedHighlightLine(10f, 5f, 0f);

        // set the filled area
        set1.setDrawFilled(true);
        set1.setFillFormatter(new IFillFormatter() {
            @Override
            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                return chart.getAxisLeft().getAxisMinimum();
            }
        });
        // set color of filled area
        if (Utils.getSDKInt() >= 18) {
            // drawables only supported on api level 18 and above
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.fade_purple);
            set1.setFillDrawable(drawable);
        } else {
            set1.setFillColor(Color.BLACK);
        }

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1); // add the data sets

        // create a data object with the data sets
        LineData ld = new LineData(dataSets);

        // set data
        chart.setData(ld);

        // draw points over time
        chart.animateX(30 * records.size());

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // draw legend entries as lines
        l.setForm(Legend.LegendForm.LINE);
    }

    @Override
    public void onStop() {
        ((MainActivity) getActivity())
                .getSupportActionBar()
                .setDisplayHomeAsUpEnabled(false);

        super.onStop();
    }

    private class ChartRecordsTask extends AsyncTask<Void, Void, List<RecordAndMote>> {

        @Override
        protected List<RecordAndMote> doInBackground(Void... voids) {
            MoteDao moteDao = IotLabDatabaseProvider.getOrCreateInstance(getContext()).moteDao();
            RecordDao recordDao = IotLabDatabaseProvider.getOrCreateInstance(getContext()).recordDao();
            long moteId = moteDao.getByName(_Mote.getName()).moteId;
            return recordDao.getLatestRecordAndMotePairById(moteId, mSize);
        }
    }
}
