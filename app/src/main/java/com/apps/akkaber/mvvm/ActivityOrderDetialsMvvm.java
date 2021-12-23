package com.apps.akkaber.mvvm;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.disposables.CompositeDisposable;

public class ActivityOrderDetialsMvvm extends AndroidViewModel {
    private static final String TAG = "ActivityorderDetialsMvvm";
    private Context context;


    private MutableLiveData<Boolean> isLoadingLivData;

    private CompositeDisposable disposable = new CompositeDisposable();


    public ActivityOrderDetialsMvvm(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }




    public MutableLiveData<Boolean> getIsLoading() {
        if (isLoadingLivData == null) {
            isLoadingLivData = new MutableLiveData<>();
        }
        return isLoadingLivData;
    }



    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();

    }

}
