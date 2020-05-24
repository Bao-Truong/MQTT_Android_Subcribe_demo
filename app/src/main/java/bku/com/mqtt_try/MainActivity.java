package bku.com.mqtt_try;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

import helpers.ChartHelper;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import helpers.MQTTHelper;

public class MainActivity extends AppCompatActivity {
        ChartHelper mChart;
        LineChart chart;

        MQTTHelper mqttHelper;

        TextView dataReceived;

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            dataReceived = (TextView) findViewById(R.id.dataReceived);
            chart = (LineChart) findViewById(R.id.chart);
            mChart = new ChartHelper(chart);
            startMqtt();
        }

    private void startMqtt() {
        mqttHelper = new MQTTHelper(getApplicationContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {

            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                mChart.addEntry(Float.valueOf(mqttMessage.toString()));
                Log.w("Debug", mqttMessage.toString());
                dataReceived.setText(mqttMessage.toString());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }
}
