package com.apps.akkaber.mvvm;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.apps.akkaber.model.DepartmentDataModel;
import com.apps.akkaber.model.DepartmentModel;
import com.apps.akkaber.model.NotificationModel;
import com.apps.akkaber.model.SingleDepartmentDataModel;
import com.apps.akkaber.model.UserModel;
import com.apps.akkaber.remote.Api;
import com.apps.akkaber.tags.Tags;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ActivityCategoryDetialsMvvm extends AndroidViewModel {
    private static final String TAG = "ActivityCategoryMvvm";
    private Context context;

    private MutableLiveData<Boolean> isLoadingLivData;

    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<SingleDepartmentDataModel> departmentLivData;



    public ActivityCategoryDetialsMvvm(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<SingleDepartmentDataModel> getCategoryData() {
        if (departmentLivData == null) {
            departmentLivData = new MutableLiveData<>();

        }
        return departmentLivData;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        if (isLoadingLivData == null) {
            isLoadingLivData = new MutableLiveData<>();
        }
        return isLoadingLivData;
    }

    public void getDepartmentDetials(String lang, String id) {
        isLoadingLivData.postValue(true);
        Api.getService(Tags.base_url)
                .getSingleDepartment(lang, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new SingleObserver<Response<SingleDepartmentDataModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull Response<SingleDepartmentDataModel> response) {
                        isLoadingLivData.postValue(false);

                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getStatus() == 200) {

                                departmentLivData.postValue(response.body());

                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        isLoadingLivData.postValue(false);
                        Log.e(TAG, "onError: ", e);
                    }
                });

    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();

    }

}
