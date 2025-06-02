package com.example.catsgallery.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.catsgallery.data.ApiFactory;
import com.example.catsgallery.data.Cat;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MutableLiveData<Cat> cat = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isError = new MutableLiveData<>();

    public LiveData<Cat> getCat() {
        return cat;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<Boolean> getIsError() {
        return isError;
    }

    public void loadCat() {
        Disposable disposable = getCatsRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> {
                    isLoading.setValue(true);
                    isError.setValue(false);
                })
                .subscribe(
                        cat -> {
                            this.cat.setValue(cat);
                            isLoading.setValue(false);
                        },
                        error -> {
                            isLoading.setValue(false);
                            isError.setValue(true);
                        }
                );
        compositeDisposable.add(disposable);
    }

    private Single<Cat> getCatsRx() {
        return ApiFactory.getApiService().getCat();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
