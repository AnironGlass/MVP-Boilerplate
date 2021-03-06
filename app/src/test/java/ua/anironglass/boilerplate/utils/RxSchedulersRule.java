package ua.anironglass.boilerplate.utils;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

/**
 * This rule registers SchedulerHooks for RxJava and RxAndroid to ensure that subscriptions
 * always subscribeOn and observeOn Schedulers.immediate().
 * Warning, this rule will reset RxAndroidPlugins and RxJavaPlugins before and after each test so
 * if the application code uses RxJava plugins this may affect the behaviour of the testing method.
 */
public final class RxSchedulersRule implements TestRule {

    private final ImmediateRxAndroidSchedulersHook mRxAndroidSchedulersHook =
            new ImmediateRxAndroidSchedulersHook();

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxJavaHooks.reset();
                RxAndroidPlugins.getInstance().reset();

                RxJavaHooks.setOnIOScheduler(scheduler -> Schedulers.immediate());
                RxJavaHooks.setOnNewThreadScheduler(scheduler -> Schedulers.immediate());
                RxJavaHooks.setOnComputationScheduler(scheduler -> Schedulers.immediate());
                RxAndroidPlugins.getInstance().registerSchedulersHook(mRxAndroidSchedulersHook);

                base.evaluate();

                RxJavaHooks.reset();
                RxAndroidPlugins.getInstance().reset();
            }
        };
    }


    private class ImmediateRxAndroidSchedulersHook extends RxAndroidSchedulersHook {

        @Override
        public Scheduler getMainThreadScheduler() {
            return Schedulers.immediate();
        }

    }

}