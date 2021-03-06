package com.apps.akkaber.mvvm;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.apps.akkaber.model.CartDataModel;
import com.apps.akkaber.model.ItemCartModel;
import com.apps.akkaber.model.ProductModel;
import com.apps.akkaber.model.SingleDepartmentDataModel;
import com.apps.akkaber.model.SingleProductDataModel;
import com.apps.akkaber.model.StatusResponse;
import com.apps.akkaber.preferences.Preferences;
import com.apps.akkaber.remote.Api;
import com.apps.akkaber.tags.Tags;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ActivityProductDetialsMvvm extends AndroidViewModel {
    private static final String TAG = "ActivityProductMvvm";
    private Context context;
    private List<ItemCartModel> cartModelList;
    private CartDataModel cartDataModel;
    private Preferences preferences;
    private MutableLiveData<Boolean> isLoadingLivData;
    private MutableLiveData<Boolean> addremove;

    private MutableLiveData<Integer> amount;

    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<SingleProductDataModel> productDataModelMutableLiveData;



    public ActivityProductDetialsMvvm(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Integer> getAmount() {
        if (amount == null) {
            amount = new MutableLiveData<>();

        }
        return amount;
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

    public MutableLiveData<Boolean> getFav() {
        if (addremove == null) {
            addremove = new MutableLiveData<>();
        }
        return addremove;
    }

    public void getProductDetials(String lang, String id, String user_id) {
        isLoadingLivData.postValue(true);
        Api.getService(Tags.base_url)
                .getSingleProduct(lang, user_id, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new SingleObserver<Response<SingleProductDataModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull Response<SingleProductDataModel> response) {
                        isLoadingLivData.postValue(false);

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

    public void addRemoveFavourite(String id, String user_id) {
        Api.getService(Tags.base_url)
                .addRemoveFav(user_id, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new SingleObserver<Response<StatusResponse>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull Response<StatusResponse> response) {

                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body().getStatus() == 200) {

                                addremove.postValue(true);

                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }
                });

    }

    public void add_to_cart(ProductModel productModel, String desc, int amount, double wayprice, double wrapprice, double total, double price, Context context) {
        preferences = Preferences.getInstance();
        cartDataModel = preferences.getCartData(context);

        if (cartDataModel == null) {

            cartModelList = new ArrayList<>();
            cartDataModel = new CartDataModel();
            cartDataModel.setDetails(cartModelList);
        } else {
            if (cartDataModel.getDetails() == null) {
                cartModelList = new ArrayList<>();

            } else {
                cartModelList = cartDataModel.getDetails();

            }

        }
        int pos = isProductItemSelected(productModel.getId(), desc);

        if (pos == -1) {
            ItemCartModel itemCartModel = new ItemCartModel();
            itemCartModel.setProduct_id(productModel.getId());
            itemCartModel.setOrder_desc(desc);
            itemCartModel.setQty(amount);
            itemCartModel.setTotal_price(total);
            itemCartModel.setWay_price(wayprice);
            itemCartModel.setWrap_price(wrapprice);
            itemCartModel.setProduct_price(price);
            itemCartModel.setImage(productModel.getPhoto());
            itemCartModel.setTitle(productModel.getTitle());
            cartModelList.add(itemCartModel);

        } else {
            ItemCartModel itemCartModel = cartModelList.get(pos);
            itemCartModel.setQty(itemCartModel.getQty() + amount);
            itemCartModel.setTotal_price(itemCartModel.getTotal_price() + price);
            cartModelList.set(pos, itemCartModel);
        }
        if (cartDataModel == null) {
            cartDataModel = new CartDataModel();
        }

        cartDataModel.setDetails(cartModelList);

        calculateTotalCost();
    }

    private void calculateTotalCost() {
        double total = 0.0;
        for (ItemCartModel cartModel : cartModelList) {
            total += cartModel.getTotal_price();
        }
        cartDataModel.setSub_total(total);
        cartDataModel.setShipping(0);
        cartDataModel.setTotal(total);
        preferences.createUpdateCartData(context, cartDataModel);
        amount.postValue(cartModelList.size());
    }


    public int isProductItemSelected(String product_id, String desc) {

        int pos = -1;

        cartDataModel = preferences.getCartData(context);
        if (cartDataModel != null && cartDataModel.getDetails() != null) {
            cartModelList = cartDataModel.getDetails();
            for (int index = 0; index < cartModelList.size(); index++) {
                ItemCartModel cartModel = cartModelList.get(index);
                if (product_id.equals(cartModel.getProduct_id()) && desc.equals(cartModel.getOrder_desc())) {
                    pos = index;
                    return pos;
                }
            }
        }


        return pos;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();

    }

}
