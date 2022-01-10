package com.apps.akkaber.mvvm;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.apps.akkaber.R;
import com.apps.akkaber.model.UserModel;
import com.apps.akkaber.remote.Api;
import com.apps.akkaber.share.Common;
import com.apps.akkaber.tags.Tags;
import com.apps.akkaber.uis.activity_verification_code.VerificationCodeActivity;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ActivityWalletMvvm extends AndroidViewModel {
    private Context context;

    public MutableLiveData<UserModel> userModelMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoadingLiveData;

    private CompositeDisposable disposable = new CompositeDisposable();

    public ActivityWalletMvvm(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();


    }
    public MutableLiveData<Boolean> getIsLoading() {
        if (isLoadingLiveData == null) {
            isLoadingLiveData = new MutableLiveData<>();
        }
        return isLoadingLiveData;
    }



    public void getProfile(String user_id) {
        isLoadingLiveData.postValue(true);
        Api.getService(Tags.base_url).getProfile(user_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).subscribe(new SingleObserver<Response<UserModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull Response<UserModel> userModelResponse) {
               isLoadingLiveData.postValue(false);

                if (userModelResponse.isSuccessful()) {
                   // Log.e("status", userModelResponse.body().getStatus() + "");
                    if (userModelResponse.body().getStatus() == 200) {

                        userModelMutableLiveData.postValue(userModelResponse.body());
                    }
                } else {

                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
              isLoadingLiveData.postValue(false);

            }
        });
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
