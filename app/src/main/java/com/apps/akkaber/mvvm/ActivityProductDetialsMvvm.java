package com.apps.akkaber.mvvm;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.apps.akkaber.model.SingleDepartmentDataModel;
import com.apps.akkaber.model.SingleProductDataModel;
import com.apps.akkaber.remote.Api;
import com.apps.akkaber.tags.Tags;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ActivityProductDetialsMvvm extends AndroidViewModel {
    private static final String TAG = "ActivityProductMvvm";
    private Context context;

    private MutableLiveData<Boolean> isLoadingLivData;

    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<SingleProductDataModel> productDataModelMutableLiveData;


    public ActivityProductDetialsMvvm(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<SingleProductDataModel> getProductData() {
        if (productDataModelMutableLiveData == null) {
            productDataModelMutableLiveData = new MutableLiveData<>();

        }
        return productDataModelMutableLiveData;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        if (isLoadingLivData == null) {
            isLoadingLivData = new MutableLiveData<>();
        }
        return isLoadingLivData;
    }

    public void getProductDetials(String lang, String id, String user_id) {
        isLoadingLivData.postValue(true);
        Api.getService(Tags.base_url)
                .getSingleProduct(lang,user_id, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new SingleObserver<Response<SingleProductDataModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull Response<SingleProductDataModel> response) {

                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getStatus() == 200) {

                                productDataModelMutableLiveData.postValue(response.body());

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
