package com.company.demo.web.toolkit.ui.slider;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;
import elemental.json.JsonArray;

@JavaScript({"slider-connector.js", "jquery-ui.min.js"})
@StyleSheet({"jquery-ui.min.css"})
public class SliderServerComponent extends AbstractJavaScriptComponent {

    public interface ValueChangeListener {
        void valueChanged(double[] newValue);
    }

    private ValueChangeListener listener;

    public SliderServerComponent() {
        addFunction("valueChanged", arguments -> {
            JsonArray array = arguments.getArray(0);
            double[] values = new double[2];
            values[0] = array.getNumber(0);
            values[1] = array.getNumber(1);

            getState(false).values = values;

            listener.valueChanged(values);
        });
    }

    public void setValue(double[] value) {
        getState().values = value;
    }

    public double[] getValue() {
        return getState().values;
    }

    public double getMinValue() {
        return getState().minValue;
    }

    public void setMinValue(double minValue) {
        getState().minValue = minValue;
    }

    public double getMaxValue() {
        return getState().maxValue;
    }

    public void setMaxValue(double maxValue) {
        getState().maxValue = maxValue;
    }

    @Override
    protected SliderState getState(boolean markAsDirty) {
        return (SliderState) super.getState(markAsDirty);
    }

    @Override
    protected SliderState getState() {
        return (SliderState) super.getState();
    }

    public ValueChangeListener getListener() {
        return listener;
    }

    public void setListener(ValueChangeListener listener) {
        this.listener = listener;
    }

    // Call JS Component from Server with parameters
    public void showNotification(String greeting) {
        callFunction("showNotification", greeting);
    }
}