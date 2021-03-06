package com.james.status.data.preference;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.james.status.R;
import com.james.status.data.PreferenceData;

public class BooleanPreferenceData extends BasePreferenceData<Boolean> {

    public boolean value;

    public BooleanPreferenceData(Context context, Identifier identifier, boolean defaultValue, OnPreferenceChangeListener<Boolean> listener) {
        super(context, identifier, listener);

        com.james.status.data.PreferenceData preference = identifier.getPreference();
        value = preference != null ? preference.getBooleanValue(context, defaultValue) : defaultValue;
    }

    @Override
    public ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_preference_boolean, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        SwitchCompat titleView = (SwitchCompat) holder.v.findViewById(R.id.title);

        titleView.setOnCheckedChangeListener(null);
        titleView.setChecked(value);
        titleView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                value = b;

                PreferenceData preference = getIdentifier().getPreference();
                if (preference != null)
                    preference.setValue(getContext(), b);

                onPreferenceChange(b);
            }
        });
    }
}
