package com.example.dialog.library.live;

import androidx.lifecycle.ViewModel;

/**
 * viewModel
 */
public class DemoViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DemoData mDemoData = new DemoData();

    public DemoData getDemoData() {
        return mDemoData;
    }
}
