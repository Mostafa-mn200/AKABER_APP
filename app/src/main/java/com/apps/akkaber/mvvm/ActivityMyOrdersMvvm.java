package com.apps.akkaber.mvvm;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.apps.akkaber.model.NotificationModel;
import com.apps.akkaber.model.UserModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class ActivityMyOrdersMvvm extends AndroidViewModel {
    private static final String TAG = "ActivitymyordersMvvm";
    private Context context;


    private MutableLiveData<Boolean> isLoadingLivData;

    private CompositeDisposable disposable = new CompositeDisposable();


    public ActivityMyOrdersMvvm(@NonNull Application application) {
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
