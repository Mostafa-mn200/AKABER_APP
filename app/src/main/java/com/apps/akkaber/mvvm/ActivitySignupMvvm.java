package com.apps.akkaber.mvvm;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.apps.akkaber.R;
import com.apps.akkaber.model.SignUpModel;
import com.apps.akkaber.model.UserModel;
import com.apps.akkaber.remote.Api;
import com.apps.akkaber.share.Common;
import com.apps.akkaber.tags.Tags;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class ActivitySignupMvvm extends AndroidViewModel {
    private Context context;
    public MutableLiveData<UserModel> userModelMutableLiveData = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    public ActivitySignupMvvm(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();


    }

    public void signupWithOutImage(Context context, SignUpModel model, String phone_code, String phone) {
       // Log.e("Dlfllfl",phone_code+" "+phone);
        ProgressDialog dialog = Common.createProgressDialog(context, context.getResources().getString(R.string.wait));
        dialog.setCancelable(false);
        dialog.show();
        Api.getService(Tags.base_url).signUp( model.getFirst_name() ,  model.getSeconed_name(), phone_code.replace("+", ""), phone, model.getCode()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).subscribe(new SingleObserver<Response<UserModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull Response<UserModel> userModelResponse) {
                dialog.dismiss();
                if (userModelResponse.isSuccessful()) {
                    if (userModelResponse.body().getStatus() == 200) {

                        userModelMutableLiveData.postValue(userModelResponse.body());
                    } else if (userModelResponse.body().getStatus() == 400) {
                        Toast.makeText(context, context.getResources().getString(R.string.user_found), Toast.LENGTH_LONG).show();
                    }
                } else {

                }

            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                dialog.dismiss();

            }
        });
    }

    public void signupWithImage(Context context, SignUpModel model, String phone_code, String phone, Uri uri) {
        ProgressDialog dialog = Common.createProgressDialog(context, context.getResources().getString(R.string.wait));
        dialog.setCancelable(false);
        dialog.show();
        RequestBody name_part = Common.getRequestBodyText(model.getFirst_name() );
        RequestBody seconded_name_part = Common.getRequestBodyText(model.getSeconed_name());

        RequestBody code_part = Common.getRequestBodyText(model.getCode()+"");
        RequestBody phone_part = Common.getRequestBodyText(phone);
        RequestBody phone_code_part = Common.getRequestBodyText(phone_code.replace("+", ""));


        MultipartBody.Part image = Common.getMultiPart(context, uri, "photo");


        Api.getService(Tags.base_url).signUpwithImage( name_part,seconded_name_part, phone_code_part, phone_part, code_part, image).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io()).subscribe(new Observer<Response<UserModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onNext(@NonNull Response<UserModel> userModelResponse) {
                dialog.dismiss();
                if (userModelResponse.isSuccessful()) {
                    if (userModelResponse.body().getStatus() == 200) {

                        userModelMutableLiveData.postValue(userModelResponse.body());
                    } else if (userModelResponse.body().getStatus() == 400) {
                        Toast.makeText(context, context.getResources().getString(R.string.user_found), Toast.LENGTH_LONG).show();
                    }
                } else {

                }
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                dialog.dismiss();
            }

            @Override
            public void onComplete() {
                dialog.dismiss();
            }
        });
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
