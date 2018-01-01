package ir.fallahpoor.ca;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ir.fallahpoor.ca.domain.executor.PostExecutionThread;

public class UiThread implements PostExecutionThread {

    @Inject
    UiThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }

}
